package com.program.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Long id;
    private String content;
    private Date createTime;
    private List<Comment> replyComment;
    private String writerName;
    private Boolean isAdmin;
    private String replyTo;
    private Long parentId;
    private Long blogId;
}
