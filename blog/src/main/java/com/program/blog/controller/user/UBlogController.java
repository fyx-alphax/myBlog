package com.program.blog.controller.user;

import com.program.blog.entity.Blog;
import com.program.blog.entity.Comment;
import com.program.blog.exception.NotFoundException;
import com.program.blog.service.user.UBlogService;
import com.program.blog.service.user.UCommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
public class UBlogController {
    final UBlogService uBlogService;
    final UCommentService uCommentService;

    public UBlogController(UBlogService uBlogService, UCommentService uCommentService) {
        this.uBlogService = uBlogService;
        this.uCommentService = uCommentService;
    }

    @GetMapping("/{id}/blog")
    public String toBlogPage(@PathVariable("id") Long id,
                             Model model) throws NotFoundException {
        Blog blog = uBlogService.getBlogById(id);
        List<Comment> commentList = uCommentService.listBlogComment(id);
        log.info(commentList.toString());
        model.addAttribute("commentList", commentList);
        model.addAttribute("blog", blog);
        footer(model);
        uBlogService.setViewCount(blog.getId(), blog.getViewCount()+1);
        return "blog";
    }

    @PostMapping("/comment")
    public String comment(Comment comment,
                          Model model){
        log.info(comment.getParentId().toString());
        uCommentService.saveComment(comment);
        List<Comment> commentList = uCommentService.listBlogComment(comment.getBlogId());
        model.addAttribute("commentList", commentList);
        return "blog :: commentList";
    }

    private void footer(Model model){
        int blogCount = uBlogService.blogCount();
        int commentCount = uCommentService.commentCount();
        int viewCount = uBlogService.totalViewCount();
        model.addAttribute("blogCount", blogCount);
        model.addAttribute("commentCount", commentCount);
        model.addAttribute("viewCount", viewCount);
    }
}
