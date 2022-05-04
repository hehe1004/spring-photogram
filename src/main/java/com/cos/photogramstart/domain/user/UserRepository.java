package com.cos.photogramstart.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

//어노테이션이 없어도JpaRepository를 상속하면 ioc 등록이 자동으로된다.
public interface UserRepository extends JpaRepository<User, Integer> {

    //JPA 네임드 쿼리리 간단한거라서 써짐
    User findByUsername(String username);
}
