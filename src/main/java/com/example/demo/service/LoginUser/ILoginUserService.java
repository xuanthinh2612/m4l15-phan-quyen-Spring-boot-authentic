package com.example.demo.service.LoginUser;

import com.example.demo.model.LoginUser;

public interface ILoginUserService {
    LoginUser getLoginUsersByUsername(String name);
    LoginUser getCurrentUser();
}
