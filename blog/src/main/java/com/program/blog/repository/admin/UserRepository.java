package com.program.blog.repository.admin;

import com.program.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserRepository {
    User findUserByUsernameAndPassword(@Param("username") String username,@Param("password") String password);
}
