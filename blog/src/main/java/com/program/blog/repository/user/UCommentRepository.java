package com.program.blog.repository.user;

import com.program.blog.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UCommentRepository {

    long saveComment(Comment comment);
    List<Comment> listReplyComment(Long parentId);
    List<Comment> listBlogComment(Long blogId);
    int count();

    long deleteComment(Long id);

    Comment getComment(Long id);
}
