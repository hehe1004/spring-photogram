package com.cos.photogramstart.web;


import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

@Slf4j

@RequiredArgsConstructor //fianl 필드 DI
@Controller//IOC에 등록이 됫다는 의미 이자, 파일을 리털하는 컨트롤러
public class AuthController {

    private final AuthService authService;

//    public AuthController(AuthService authService) {
//        this.authService = authService;
//    }

    @GetMapping("/auth/signin")
    public String signinForm(){
        return "auth/signin";
    }


    @GetMapping("/auth/signup")
    public String signupForm(){
        return "auth/signup";
    }

    //회원가입버트 -> /authsignup->/auth/signin
    @PostMapping("/auth/signup")
    public  String signup(@Validated SignupDto signupDto, BindingResult bindingResult) {

        // 반환타입 string 이지만 앞에 @ResponseBody 가 붙어있으면 데이터 반환
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
                System.out.println("====================");
                System.out.println(error.getDefaultMessage());
                System.out.println("====================");
            }

//            return "오류남";
//            throw new RuntimeException("유효성 검사 실패");
            throw new CustomValidationException("유효성 검사 실패", errorMap);
        }else{
            //User <- signupDto
            User user = signupDto.toEntity();
            User userEntity = authService.회원가입(user);
            System.out.println(userEntity);

            return "auth/signin";
        }



//
//
//        //User <- signupDto
//        User user = signupDto.toEntity();
//        log.info(user.toString());
////        System.out.println(signupDto.toString());
////        log.info("info ={}",signupDto.toString());
//        User userEntity = authService.회원가입(user);
//        System.out.println(userEntity);
//
//        return "auth/signin";
//
    }


}
