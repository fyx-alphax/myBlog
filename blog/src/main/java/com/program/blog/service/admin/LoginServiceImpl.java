package com.program.blog.service.admin;

import com.program.blog.entity.User;
import com.program.blog.repository.admin.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class LoginServiceImpl implements LoginService{
    final UserRepository userRepository;

    public LoginServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User checkUser(String username, String password) {
        return userRepository.findUserByUsernameAndPassword(username, DigestUtils.md5DigestAsHex(password.getBytes()));
    }
}
