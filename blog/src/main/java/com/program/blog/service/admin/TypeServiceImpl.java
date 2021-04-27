package com.program.blog.service.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.program.blog.entity.Blog;
import com.program.blog.entity.Type;
import com.program.blog.repository.admin.BlogRepository;
import com.program.blog.repository.admin.TypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class TypeServiceImpl implements TypeService {
    final TypeRepository typeRepository;
    final BlogRepository blogRepository;

    public TypeServiceImpl(TypeRepository typeRepository, BlogRepository blogRepository) {
        this.typeRepository = typeRepository;
        this.blogRepository = blogRepository;
    }

    @Override
    public Type addType(String typeName) {
        Type type = new Type();
        type.setName(typeName);
        typeRepository.insertType(type);
        return type;
    }

    @Override
    public Type getType(Long id) {
        return typeRepository.getTypeById(id);
    }

    @Override
    public Type getType(String name) {
        return typeRepository.getTypeByName(name);
    }

    @Transactional
    @Override
    public Type deleteType(Long id) {
        blogRepository.updateTypes(id);
        Type type = typeRepository.getTypeById(id);
        typeRepository.deleteTypeById(id);
        return type;
    }

    @Override
    public Type updateType(Long id, Type type) {
        Type preType = typeRepository.getTypeById(id);
        String typeName = type.getName();
        typeRepository.updateTypeName(id,typeName);
        return preType;
    }


    @Override
    public PageInfo<Type> listType() {
        List<Type> typeList = typeRepository.findAll();
        return new PageInfo<>(typeList);
    }

    @Override
    public PageInfo<Type> listType(String pageNum) {
        int page = Integer.parseInt(pageNum);
        PageHelper.startPage(page, 5);
        List<Type> typeList = typeRepository.findAll();
        return new PageInfo<>(typeList);
    }
}
