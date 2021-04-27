package com.program.blog.repository.user;

import com.program.blog.entity.Blog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UBlogRepository {
    List<Blog> getPublishedList();
    int publishedCount();
    List<Blog> listRecommend();
    List<Blog> listBlogByTypeId(Long typeId);
    List<Blog> listBlogByTitle(String title);
    Blog getBlogById(Long id);
    long updateViewCount(Long id, int viewCount);

    int getTotalView();
}
