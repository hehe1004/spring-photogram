package com.cos.photogramstart.web.api;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.CMRespDto;
import com.cos.photogramstart.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PutMapping("/api/user/{id}")
    public CMRespDto<?> update(@PathVariable int id, UserUpdateDto userUpdateDto ) {
        System.out.println("여기는 안오?");
        System.out.println(userUpdateDto);
        System.out.println(id);
        User userEntity = userService.회원수정(id, userUpdateDto.toEntity());
        System.out.println(userUpdateDto.toEntity().getId());

        return new CMRespDto<>(1, "회원수정완료", userEntity);

    }

//    @PutMapping("/api/user/{id}")
//    public String update(@PathVariable int id, UserUpdateDto userUpdateDto) {
//
//
//        System.out.println(userUpdateDto);
//        return "ok";
//
//    }

}
