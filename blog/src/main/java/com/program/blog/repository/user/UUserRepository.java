package com.program.blog.repository.user;

import com.program.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UUserRepository {
    User selectUserInfoById(Long id);
}
