package com.program.blog.controller.user;

import com.github.pagehelper.PageInfo;
import com.program.blog.entity.Blog;
import com.program.blog.entity.Type;
import com.program.blog.service.user.UBlogService;
import com.program.blog.service.user.UCommentService;
import com.program.blog.service.user.UTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/types")
public class UTypeController {
    final UTypeService uTypeService;
    final UBlogService uBlogService;
    final UCommentService uCommentService;

    public UTypeController(UTypeService uTypeService, UBlogService UBlogService, UCommentService uCommentService) {
        this.uTypeService = uTypeService;
        this.uBlogService = UBlogService;
        this.uCommentService = uCommentService;
    }

    @GetMapping
    public String toTypePage(Model model){
        List<Type> typeList = uTypeService.listType();
        PageInfo<Blog> blogPageInfo = uBlogService.listBlog();
        model.addAttribute("typeId", 0);
        model.addAttribute("typeList", typeList);
        model.addAttribute("blogPageInfo", blogPageInfo);
        footer(model);
        return "types";
    }

    @PostMapping("/search")
    public String search(@RequestParam(value = "pageNum", required = false, defaultValue = "1") String pageNum,
                       @RequestParam(value = "typeId", required = false, defaultValue = "noType") String typeId,
                       Model model){
        PageInfo<Blog> blogPageInfo = uBlogService.listBlog(pageNum,typeId);
        List<Type> typeList = uTypeService.listType();
        model.addAttribute("typeList", typeList);
        model.addAttribute("blogPageInfo", blogPageInfo);
        model.addAttribute("typeId", typeId);
        return "types :: blogList";
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
