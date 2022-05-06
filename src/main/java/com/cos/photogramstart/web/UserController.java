package com.cos.photogramstart.web;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {

    @GetMapping("/user/{id}")
    public String profile(@PathVariable int id){
        System.out.println(id);
        return "user/profile";
    }


    @GetMapping("/user/{id}/update")
    public String update(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {


        System.out.println("여기까지오니 ");
        //추천 @AuthenticationPrincipal PrincipalDetails principalDetails
        System.out.println("세션정보" + principalDetails.getUser());


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