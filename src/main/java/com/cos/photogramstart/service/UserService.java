package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;



    @Transactional
    public User 회원수정(int id, User user) {

        System.out.println("여기는?");
        //1. 영속화
        User userEntity = userRepository.findById(id).get();//무조건 찾았다. 걱정마 get() 2. 못찾앗더 익셉션 발동시킬게 OrElseThrow()
        System.out.println("여기는?2");
        //2. 영속화 오브젝트를 수정 - 더티체킹(업테이트 완료)
        userEntity.setName(user.getName());
        userEntity.setPassword(user.getPassword());
        userEntity.setBio(user.getBio());
        userEntity.setWebsite(user.getWebsite());
        userEntity.setPhone(user.getPhone());
        userEntity.setGender(user.getGender());

        return userEntity;
    }
}
