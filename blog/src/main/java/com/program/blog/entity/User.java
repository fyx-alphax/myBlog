package com.program.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String nickname;
    private String username;
    private String password;
    private String avatar;
    private Integer userType;
    private Date createTime;
    private String email;
    private List<Blog> blogList;
}
