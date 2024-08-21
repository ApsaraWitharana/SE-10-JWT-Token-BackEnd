package com.example.se10jwtmy.repository;

import com.example.se10jwtmy.entitiy.User;
import org.springframework.data.jpa.repository.JpaRepository;
//06
public interface UserRepository extends JpaRepository<User,String> {

    User findByEmail(String userName);

    boolean existsByEmail(String userName);

    int deleteByEmail (String userName);

}
