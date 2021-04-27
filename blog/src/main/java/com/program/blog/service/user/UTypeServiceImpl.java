package com.program.blog.service.user;

import com.github.pagehelper.PageInfo;
import com.program.blog.entity.Type;
import com.program.blog.repository.user.UTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UTypeServiceImpl implements UTypeService {
    final UTypeRepository uTypeRepository;

    public UTypeServiceImpl(UTypeRepository uTypeRepository) {
        this.uTypeRepository = uTypeRepository;
    }

    @Override
    public List<Type> listType() {
        return uTypeRepository.getList();
    }
}
