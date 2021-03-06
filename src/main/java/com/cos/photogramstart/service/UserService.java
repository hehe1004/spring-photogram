package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.web.dto.user.UserProfileDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final SubscribeRepository subscribeRepository;


    @Transactional(readOnly = true)
    public UserProfileDto 회원프로필(int pageUserId, int pricipalId) {

        //아이디에 따른 구독, 등록 버튼
        UserProfileDto dto = new UserProfileDto();


        //select * from image where userId = :userId;

        User userEntity = userRepository.findById(pageUserId).orElseThrow(() -> {
            throw new CustomException("해당 프로필 페이지는 없는 페이지");

        });

        dto.setUser(userEntity);
        dto.setPageOwnerState(pageUserId==pricipalId);
        dto.setImageCount(userEntity.getImages().size());

        int subscribeState= subscribeRepository.mSubscribeState(pricipalId, pageUserId);
        int subscribeCount = subscribeRepository.mSubscribeCount(pageUserId);
        dto.setSubscribeState(subscribeState ==1);
        //subscribeState 가 1과 같으면 true

        dto.setSubscribeCount(subscribeCount);



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
