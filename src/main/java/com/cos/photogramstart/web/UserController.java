package com.cos.photogramstart.web;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.UserService;
import com.cos.photogramstart.web.dto.user.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    @GetMapping("/user/{id}")
//    public String profile(@PathVariable int id) {
//        System.out.println("---------------UserController-------------- ");
//        System.out.println(id);
//        System.out.println("---------------UserController-------------- ");
//        return "user/profile";
//    }


    @GetMapping("/user/{pageUserId}")
    public String profile(@PathVariable int pageUserId, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
//        User userEntity = userService.회원프로필(pageUserId, principalDetails.getUser().getId());
//        model.addAttribute("user", userEntity);
        UserProfileDto dto = userService.회원프로필(pageUserId, principalDetails.getUser().getId());
        model.addAttribute("dto", dto);

        System.out.println("---------------UserController-------------- ");
        System.out.println(pageUserId);
        System.out.println("---------------UserController-------------- ");
        return "user/profile";
    }

    @GetMapping("/user/{id}/update")
//    public String update(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
      public String update(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        System.out.println("---------------UserController2-------------- ");
        //추천 @AuthenticationPrincipal PrincipalDetails principalDetails
//        System.out.println("세션정보" + principalDetails.getUser());
        System.out.println("---------------UserController2-------------- ");

//        극협

//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println("직접찾은 세션 정보"+auth.getPrincipal());
//
//        PrincipalDetails mPrincipalDetails = (PrincipalDetails) auth.getPrincipal();
//        System.out.println("직접찾은 세션 정보2="+mPrincipalDetails.getUser());
//
//        model.addAttribute("principal", principalDetails.getUser());

        return "user/update";
    }
}
