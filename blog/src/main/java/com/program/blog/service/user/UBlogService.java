package com.program.blog.service.user;

import com.github.pagehelper.PageInfo;
import com.program.blog.entity.Blog;
import com.program.blog.entity.Type;
import com.program.blog.exception.NotFoundException;

import java.util.List;

public interface UBlogService {

    PageInfo<Blog> listBlog();
    int blogCount();
    PageInfo<Type> listType();
    PageInfo<Blog> listRecommend();
    PageInfo<Blog> listBlog(String pageNum);
    PageInfo<Blog> listBlog(String pageNum, String typeId);
    PageInfo<Blog> searchBlog(String title);
    PageInfo<Blog> searchBlog(String pageNum, String title);
    List<Blog> listTimeLine();
    Blog getBlogById(Long id) throws NotFoundException;
    void setViewCount(Long id, int viewCount);
    int totalViewCount();
}
