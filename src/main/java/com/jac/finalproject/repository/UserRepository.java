package com.jac.finalproject.repository;

import com.jac.finalproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User, Long> {


    @Query(value = "SELECT * FROM jac_user WHERE user_name = :username", nativeQuery = true)
    User findByUserName(String username);
}
