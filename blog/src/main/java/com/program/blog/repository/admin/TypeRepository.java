package com.program.blog.repository.admin;

import com.program.blog.entity.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TypeRepository {
    long insertType(Type type);
    Type getTypeById(Long id);
    long deleteTypeById(Long id);
    long updateTypeName(@Param("id") Long id, @Param("name") String name);
    List<Type> findAll();
    Type getTypeByName(String name);
}
