package com.cos.photogramstart.domain.subscribe;

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
                        name = "subscribe_uk",
                        columnNames = {"fromUserId", "toUserId"}


                )
        }
)
public class Subscribe {

    @Id //기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY)//번호 증가 전략이 데이터베이스를 따라간다
    private int id;


    @JoinColumn(name = "fromUserId") //컬럼명 지정
    @ManyToOne
    private User fromUser;

    @JoinColumn(name = "toUserId")
    @ManyToOne
    private User toUser;


    private LocalDateTime createDate;

    @PrePersist //디비에 insert 되기 직전에 실행
    public void createDate() {

        this.createDate = LocalDateTime.now();
    }

}


