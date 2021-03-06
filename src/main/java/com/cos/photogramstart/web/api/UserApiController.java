package com.cos.photogramstart.web.api;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.CMRespDto;
import com.cos.photogramstart.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PutMapping("/api/user/{id}")
    public CMRespDto<?> update(@PathVariable int id,
                               @Validated UserUpdateDto userUpdateDto,
                               BindingResult bindingResult,//꼭 벨리드가 적혀있는 다음에 적어야횜
                               @AuthenticationPrincipal PrincipalDetails principalDetails) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
                System.out.println("========UserApiController============");
//                System.out.println(error.getDefaultMessage());
                System.out.println("========UserApiController============");
            }

//            return "오류남";
//            throw new RuntimeException("유효성 검사 실패");
            throw new CustomValidationApiException("유효성 검사 실패", errorMap);
        }else{

            System.out.println("----------------UserApiController-------------------");
            System.out.println(userUpdateDto);
            System.out.println(id);



            System.out.println(userUpdateDto.toEntity().getId());
            System.out.println("----------------UserApiController-------------------");

            User userEntity = userService.회원수정(id, userUpdateDto.toEntity());
            //세션 정보 변경 해야 실시간 반영
            principalDetails.setUser(userEntity);

            return new CMRespDto<>(1, "회원수정완료", userEntity); //응답시에 userEntity 의 모든getter 함수가 호 출되고 JSON 으로 파싱하여 응답

        }
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



