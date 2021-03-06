package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableWebSecurity //해달 파일로 시큐리티 활성화
@Configuration //ioc
public class SecurityConfig extends WebSecurityConfigurerAdapter {




    //비밀번호 암호화
    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


    //필터?
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super 삭제 - 기존 시큐리티가 가지고 있는 기능이 다 비활성화됨
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/", "/user/**", "/image/**", "subscribe/**", "/comment/**","/api/**").authenticated().anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/auth/signin")//GET
                .loginProcessingUrl("/auth/signin")//POST -> 스프링 시큐리티가 로그인 프로세스 진행
                .defaultSuccessUrl("/");
        //로그인
    }
}
