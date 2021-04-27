package com.program.blog.service.admin;

import com.program.blog.entity.User;

public interface LoginService {
    User checkUser(String username, String password);
}
