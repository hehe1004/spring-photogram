package com.cos.photogramstart.web.dto.auth;


import com.cos.photogramstart.domain.user.User;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

//DTO 통신할때 필요한 오브젝트를 담아두는곳 (가입정보)

@Data
public class SignupDto {
    @Max(20)
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
    @NotBlank
    private String name;

    
    //빌더 패턴 적용
    public User toEntity(){

        return User.builder().username(username).password(password).email(email).name(name).build();


    }

}
