package com.cos.photogramstart.domain.image;

import com.cos.photogramstart.domain.likes.Likes;
import com.cos.photogramstart.domain.user.User;
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
public class Image { // 한명의 유저는 여러개의이미지를 등록할수있고, 하나의 이미지는 동시에 등록안됨
    //N,1


    @Id //기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY)//번호 증가 전략이 데이터베이스를 따라간다
    private int id;

    private String caption;//사진 캡션
    private String postImageUrl;// 사진을 전송받아 그 사진을 서버에 특정 폴더에 저장 -db에 그 저장된 경로를 intert


    @JsonIgnoreProperties({"images"})
    @JoinColumn(name="userId") //user는 오브젝트 타입으로 컬럼에 들어갈때 포인트로 들어간다, 그때 이름 정해줘야함

    @ManyToOne(fetch = FetchType.EAGER) // 이미지를 select하면 조인해서 User정보를 같이 들고옴
    private User user; //1,1

    //이미지 좋아요
    @JsonIgnoreProperties({"image"})
    @OneToMany(mappedBy = "image")
    private List<Likes> likes;

    //댓글


    private LocalDateTime createDate;

    @Transient //DB에 칼럼 안만들어짐
    private boolean likeState;

    @Transient
    private int likeCount;

    @PrePersist //디비에 insert 되기 직전에 실행
    public void createDate() {

        this.createDate = LocalDateTime.now();
    }

}
