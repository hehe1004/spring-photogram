package com.cos.photogramstart.domain.likes;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder //빌더패턴 적용
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity //DB에 테이블 생성
//유니크 설정 두개
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "likes_uk",
                        columnNames = {"imageId", "userId"}


                )
        }
)
public class Likes { //N

    @Id //기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY)//번호 증가 전략이 데이터베이스를 따라간다
    private int id;

    @JoinColumn(name="imageId")
    @ManyToOne
    private Image image; //1


    //오류가 터지고 나서 잡아봅시다.
    @JoinColumn(name="userId")
    @ManyToOne
    private User user; //1

    private LocalDateTime createDate;

    @PrePersist //디비에 insert 되기 직전에 실행
    public void createDate() {

        this.createDate = LocalDateTime.now();
    }
}
