package com.cos.photogramstart.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {


    @Modifying //INSERT, DELETE, UPDATE 를 네이티브 쿼리로 작성하려면 해당 어노테이션 필요
    @Query(value = "INSERT INTO subscribe(fromUserId, toUserId, createDate) VALUES(:fromUserId, :toUserId, now())",nativeQuery = true)
    void mSubscribe(int fromUserId, int toUserId); //성공시 1(변경된 행의 개수가 리턴됨), 실패시 -1 그래서 int
    // :  는 변수를 void mSubscribe(int fromUserId, int toUserId); 변수를 넣겠다는 말임

    @Modifying
    @Query(value = "DELETE FROM subscribe WHERE fromUserId=:fromUserId AND toUserId=:toUserId",nativeQuery = true)
    void mUnSubscribe(int fromUserId, int toUserId);

    //셀렉트만 하기 떄문에 @Modifying 필요없음
    //구독상태
    @Query(value ="SELECT COUNT(*) FROM subscribe WHERE fromUserId=:principalId AND toUserId=:pageUserId", nativeQuery = true)
    int mSubscribeState(int principalId, int pageUserId);
    //구독 숫자
    @Query(value ="SELECT COUNT(*) FROM subscribe WHERE fromUserId=:pageUserId", nativeQuery = true)
    int mSubscribeCount(int pageUserId);


}
