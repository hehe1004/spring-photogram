package com.cos.photogramstart.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {



    @Modifying //INSERT, DELETE, UPDATE 를 네이티브 쿼리로 작성하려면 해당 어노테이션 필요
    @Query(value = "INSERT INTO Subscribe(fromUserId, toUserId, createDate) VALUES(:fromUserId, toUserId, now())",nativeQuery = true)
    void mSubscribe(int fromUserId, int toUserId); //성공시 1(변경된 행의 개수가 리턴됨), 실패시 -1 그래서 int


    @Modifying
    @Query(value = "DELETE FROM Subscribe WHERE fromUserId=:fromUserId AND toUserId =:toUserId",nativeQuery = true)
    void munSubscribe(int fromUserId, int toUserId);




}
