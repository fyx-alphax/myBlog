package com.program.blog.service.user;

import com.program.blog.entity.Comment;

import java.util.List;

public interface UCommentService {
    Comment saveComment(Comment comment);
    List<Comment> listBlogComment(Long blogId);

    int commentCount();
}
