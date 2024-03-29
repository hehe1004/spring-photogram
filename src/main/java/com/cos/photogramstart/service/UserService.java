package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.web.dto.user.UserProfileDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final SubscribeRepository subscribeRepository;


    @Value("${file.path}")
    private String uploadFolder;// = "C:/workspace/springbootwork/upload/"; yml 에 적용 안하면 직접 적어야되서 불편

    @Transactional
    public User 회원프로필사진변경(int principalId, MultipartFile profileImageFile) {
        UUID uuid = UUID.randomUUID(); // uuid
        String imageFileName = uuid+"_"+profileImageFile.getOriginalFilename(); // 1.jpg
        System.out.println("이미지 파일이름 : "+imageFileName);

        Path imageFilePath = Paths.get(uploadFolder+imageFileName);

        // 통신, I/O -> 예외가 발생할 수 있다.
        try {
            Files.write(imageFilePath, profileImageFile.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        User userEntity = userRepository.findById(principalId).orElseThrow(()->{
            // throw -> return 으로 변경
            return new CustomApiException("유저를 찾을 수 없습니다.");
        });
        userEntity.setProfileImageUrl(imageFileName);

        return userEntity;
    } // 더티체킹으로 업데이트 됨.

    @Transactional(readOnly = true)
    public UserProfileDto 회원프로필(int pageUserId, int principalId) {

        //아이디에 따른 구독, 등록 버튼
        UserProfileDto dto = new UserProfileDto();


        //select * from image where userId = :userId;

        User userEntity = userRepository.findById(pageUserId).orElseThrow(() -> {
            throw new CustomException("해당 프로필 페이지는 없는 페이지");

        });

        dto.setUser(userEntity);
        dto.setPageOwnerState(pageUserId==principalId);
        dto.setImageCount(userEntity.getImages().size());

        int subscribeState= subscribeRepository.mSubscribeState(principalId, pageUserId);
        int subscribeCount = subscribeRepository.mSubscribeCount(pageUserId);
        dto.setSubscribeState(subscribeState ==1);
        //subscribeState 가 1과 같으면 true
        dto.setSubscribeCount(subscribeCount);

        userEntity.getImages().forEach(image -> {
            image.setLikeCount(image.getLikes().size());

        });




        System.out.println("===================");
//        userEntity.getImages().get(0);
        return dto;


    }

    @Transactional
    public User 회원수정(int id, User user) {



        //1. 영속화
//        User userEntity = userRepository.findById().get();//무조건 찾았다. 걱정마 get() 2. 못찾앗더 익셉션 발동시킬게 OrElseThrow()

//        User userEntity = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
//
//            @Override
//            public IllegalArgumentException get() {
//                return new IllegalArgumentException("찾을수 없는 id입니다.");
//            }
//        });

        User userEntity = userRepository.findById(id).orElseThrow(() -> {return new CustomValidationApiException("찾을수 없는 id입니다.");

        });



        //2. 영속화 오브젝트를 수정 - 더티체킹(업테이트 완료)
        userEntity.setName(user.getName());


//        userEntity.setPassword(user.getPassword());
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        userEntity.setPassword(encPassword);

        userEntity.setBio(user.getBio());
        userEntity.setWebsite(user.getWebsite());
        userEntity.setPhone(user.getPhone());
        userEntity.setGender(user.getGender());

        return userEntity;
    }


}
