package com.program.blog.service.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.program.blog.entity.Blog;
import com.program.blog.entity.BlogQuery;
import com.program.blog.repository.admin.BlogRepository;
import com.program.blog.repository.admin.TypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
public class BlogServiceImpl implements BlogService {
    final BlogRepository blogRepository;
    final TypeRepository typeRepository;

    public BlogServiceImpl(BlogRepository blogRepository, TypeRepository typeRepository) {
        this.blogRepository = blogRepository;
        this.typeRepository = typeRepository;
    }

    //全部博客加载
    @Override
    public PageInfo<Blog> listBlog(String pageNum) {
        PageHelper.startPage(Integer.parseInt(pageNum), 5);
        List<Blog> blogList = blogRepository.listBlogs();
        return new PageInfo<>(blogList);
    }

    //查找博客
    @Override
    public PageInfo<Blog> listBlog(String pageNum, BlogQuery blogQuery) {
        String blogTitle = null;
        Long typeId = null;
        if (!"".equals(blogQuery.getBlogTitle()) && blogQuery.getBlogTitle() != null){
            blogTitle = blogQuery.getBlogTitle();
        }
        if (blogQuery.getTypeId() != null && blogQuery.getTypeId() != 0){
            typeId = blogQuery.getTypeId();
        }
        PageHelper.startPage(Integer.parseInt(pageNum), 5);
        List<Blog> blogList = blogRepository.listBlogs(blogTitle, typeId, blogQuery.isRecommend());
        return new PageInfo<>(blogList);
    }

    @Override
    public Blog deleteBlog(Long id) {
        Blog blog = blogRepository.getBlogById(id);
        blogRepository.deleteBlogById(id);
        return blog;
    }

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        Date date = new Date();
        blog.setCreateTime(date);
        blog.setUpdateTime(date);
        blogRepository.saveBlog(blog);
        Long blogId = blogRepository.getBlogIdByTitle(blog.getTitle());
        blog.setId(blogId);
        return blog;
    }

    @Override
    public Long getBlogIdByTitle(String title) {
        return blogRepository.getBlogIdByTitle(title);
    }

    @Override
    public Blog updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        blogRepository.updateBlog(blog);
        return blog;
    }

    @Override
    public Blog getBlog(Long id) {
        return blogRepository.getBlogById(id);
    }

    @Override
    public Blog getBlog(String title) {
        Blog blog = blogRepository.getBlogByTitle(title);
        log.info(blog.toString());
        return blog;
    }
}
