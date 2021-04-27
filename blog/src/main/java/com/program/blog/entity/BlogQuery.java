package com.program.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//管理员查询对象封装
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogQuery {
    private String blogTitle;
    private Long typeId;
    private boolean recommend;
}
