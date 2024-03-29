package com.cos.photogramstart.web;


import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.ImageService;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class imageController {

    private final ImageService imageService;

    @GetMapping(value = {"/", "/image/story"})
    public String story() {
        return "image/story";
    }

    @GetMapping("/image/popular")
    public String popular(Model model)
    {

        List<Image> images = imageService.인기사진();

        model.addAttribute("images",images);
        return "image/popular";
    }

    @GetMapping("/image/upload")
    public String upload() {
        return "image/upload";
    }

    @PostMapping("/image")
    public String imageUpload(ImageUploadDto imageUploadDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        if (imageUploadDto.getFile().isEmpty()) {
            throw new CustomValidationException("이미지가 첨부 안되었습니다.", null);
        }
        //서비스 호출
        imageService.사진업로드(imageUploadDto, principalDetails);

        return "redirect:/user/" + principalDetails.getUser().getId();

    }

}

