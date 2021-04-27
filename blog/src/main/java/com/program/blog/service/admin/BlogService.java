package com.program.blog.service.admin;

import com.github.pagehelper.PageInfo;
import com.program.blog.entity.Blog;
import com.program.blog.entity.BlogQuery;

public interface BlogService {
    PageInfo<Blog> listBlog(String pageNum);
    PageInfo<Blog> listBlog(String pageNum, BlogQuery blogQuery);
    Blog saveBlog(Blog blog);
    Long getBlogIdByTitle(String title);
    Blog deleteBlog(Long id);
    Blog getBlog(Long id);
    Blog getBlog(String title);
    Blog updateBlog(Blog blog);
}
