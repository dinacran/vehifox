package com.boot.SpringBoot.repository;

import org.springframework.stereotype.Repository;
import com.boot.SpringBoot.model.UserDetail;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface UserRepo extends JpaRepository<UserDetail, Integer> {

    UserDetail findByUsername(String username);

}
