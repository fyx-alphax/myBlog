package com.program.blog.repository.admin;

import com.program.blog.entity.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BlogRepository {
    long insertBlog(Blog blog);
    Blog getBlogById(Long id);
    long deleteBlogById(Long id);
    long updateBlogName(@Param("id") Long id, @Param("name") String name);
    List<Blog> listBlogs();
    List<Blog> listBlogs(@Param("blogTitle") String blogTitle, @Param("typeId") Long typeId, @Param("recommend") boolean recommend);
    Long getBlogIdByTitle(String title);
    long saveBlog(Blog blog);
    long updateBlog(Blog blog);

    Blog getBlogByTitle(String title);

    long updateTypes(Long id);
}
