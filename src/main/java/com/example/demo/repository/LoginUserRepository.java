package com.example.demo.repository;

import com.example.demo.model.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginUserRepository extends JpaRepository<LoginUser,Long>  {

        LoginUser getLoginUsersByUsername(String name);
}
