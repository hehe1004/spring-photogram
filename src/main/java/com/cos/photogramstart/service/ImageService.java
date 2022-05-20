package com.cos.photogramstart.service;


import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor//di
@Service
public class ImageService {


    private final ImageRepository imageRepository;


    @Value("${file.path}")
    private String uploadFolder;// = "C:/workspace/springbootwork/upload/"; yml 에 적용 안하면 직접 적어야되서 불편

    public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {


        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid + "_" + imageUploadDto.getFile().getOriginalFilename();//1.jpg
        System.out.println("이미지 파일 이름" + imageFileName);

        Path imageFilePath = Paths.get(uploadFolder + imageFileName);

        //통신이없거나, I/O 가 일어날때 예외가 발생할수있다. 그래서 TRY-CATCH 함
        try {
            Files.write(imageFilePath, imageUploadDto.getFile().getBytes());


        } catch (Exception e) {
            e.printStackTrace();


        }
        //image 테이블에 저장
        Image image = imageUploadDto.ToEntity(imageFileName, principalDetails.getUser());// 이미지 파일 이름82f2d75a-ab64-4824-8720-e0c66663e90b_반사광3.png
        Image imageEntity = imageRepository.save(image);
        System.out.println("---------ImageService------------");
        System.out.println(imageEntity);
        System.out.println("---------ImageService------------");

    }
}
