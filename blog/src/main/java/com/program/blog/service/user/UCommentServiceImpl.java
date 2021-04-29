package com.program.blog.service.user;

import com.program.blog.entity.Comment;
import com.program.blog.repository.user.UCommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class UCommentServiceImpl implements  UCommentService {
    final UCommentRepository uCommentRepository;

    public UCommentServiceImpl(UCommentRepository uCommentRepository) {
        this.uCommentRepository = uCommentRepository;
    }

    @Override
    public List<Comment> listBlogComment(Long blogId) {
        return uCommentRepository.listBlogComment(blogId);
    }

    @Override
    public int commentCount() {
        return uCommentRepository.count();
    }

    @Override
    public long deleteComment(Long commentId) {
       return uCommentRepository.deleteComment(commentId);
    }

    @Override
    public Comment saveComment(Comment comment) {
        comment.setCreateTime(new Date());
        if (comment.getReplyTo().equals("null")){
            comment.setReplyTo(null);
        }
        uCommentRepository.saveComment(comment);
        return comment;
    }
}
