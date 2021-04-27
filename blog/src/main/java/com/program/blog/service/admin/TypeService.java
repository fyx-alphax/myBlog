package com.program.blog.service.admin;

import com.github.pagehelper.PageInfo;
import com.program.blog.entity.Type;

import java.util.List;

public interface TypeService {
    Type addType(String typeName);
    Type getType(Long id);
    Type getType(String name);
    Type deleteType(Long id);
    Type updateType(Long id, Type type);
    PageInfo<Type> listType(String pageNum);
    PageInfo<Type> listType();
}
