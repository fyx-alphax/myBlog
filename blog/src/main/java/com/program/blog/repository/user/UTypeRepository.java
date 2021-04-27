package com.program.blog.repository.user;

import com.program.blog.entity.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UTypeRepository {
    Type getTypeById(Long id);
    List<Type> getList();
    Type getTypeByName(String name);
}
