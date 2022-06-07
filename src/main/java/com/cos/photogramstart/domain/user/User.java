package com.cos.photogramstart.domain.user;

import com.cos.photogramstart.domain.image.Image;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Builder //빌더패턴 적용
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity //DB에 테이블 생성
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//번호 증가 전략이 데이터베이스를 따라간다
    private int id;


    // unique = true 유니크 설정 한개
    @Column(length = 20, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    private String website;
    private String bio; //자기소개
    @Column(nullable = false)
    private String email;
    private String phone;
    private String gender;

    //나는 연관관계의 주인이 아니다, 그러므로 테이블에 컬럼을 만들지마
    //user를 select 할떄 해당 user id 로 등록된 image 를 다 가져오시오
    // Lazy 일때는 user 를 select 할때 해당 user id로 등록된 image 가져 오지마 - 대신 getImages()함수의 image들이 호출될때 가져와
    // Eager 는 일때는 user 를 select 할때 해당 user id로 등록된 image를 전부 join 해서 가져와
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"user"})
    private List<Image> images;//양방향 맵핑


//    private String postImageUrl;

    private String profileImageUrl; //사진
    private String role; //권한

    private LocalDateTime createDate;

    @PrePersist //디비에 insert 되기 직전에 실행
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }

}