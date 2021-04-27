package com.program.blog.controller.user;

import com.github.pagehelper.PageInfo;
import com.program.blog.entity.Blog;
import com.program.blog.entity.Type;
import com.program.blog.service.user.UBlogService;
import com.program.blog.service.user.UCommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class IndexController {
    final UBlogService uBlogService;
    final UCommentService uCommentService;

    public IndexController(UBlogService UBlogService, UCommentService uCommentService) {
        this.uBlogService = UBlogService;
        this.uCommentService = uCommentService;
    }

    @GetMapping("/")
    public String index(Model model){
        initIndex(model);
        footer(model);
        return "index";
    }

    @GetMapping("/music")
    public String music(Model model){
        footer(model);
        return "music";
    }

    @GetMapping("/about")
    public String about(Model model){
        footer(model);
        return "about";
    }


    @PostMapping("/page")
    public String page(@RequestParam(value = "pageNum", required = false, defaultValue = "1") String pageNum,
                       Model model){
        PageInfo<Blog> blogPageInfo = uBlogService.listBlog(pageNum);
        model.addAttribute("blogPageInfo", blogPageInfo);
        return "index :: blogList";
    }

    @PostMapping("/search")
    public String search(String title,
                         Model model){
        PageInfo<Blog> blogPageInfo = uBlogService.searchBlog(title);
        model.addAttribute("title", title);
        model.addAttribute("blogPageInfo", blogPageInfo);
        footer(model);
        return "search";
    }

    @PostMapping("/search/page")
    public String searchPage(@RequestParam(value = "pageNum", required = false, defaultValue = "1") String pageNum,
                             String title,
                             Model model){
        log.info(title);
        PageInfo<Blog> blogPageInfo = uBlogService.searchBlog(pageNum, title);
        model.addAttribute("blogPageInfo", blogPageInfo);
        model.addAttribute("title", title);
        return "search :: blogList";
    }

    private void footer(Model model){
        int blogCount = uBlogService.blogCount();
        int commentCount = uCommentService.commentCount();
        int viewCount = uBlogService.totalViewCount();
        model.addAttribute("blogCount", blogCount);
        model.addAttribute("commentCount", commentCount);
        model.addAttribute("viewCount", viewCount);
    }

    private void initIndex(Model model){
        int blogCount = uBlogService.blogCount();
        PageInfo<Blog> blogPageInfo = uBlogService.listBlog();
        PageInfo<Type> typePageInfo = uBlogService.listType();
        PageInfo<Blog> recommendPageInfo = uBlogService.listRecommend();
        model.addAttribute("recommendPageInfo", recommendPageInfo);
        model.addAttribute("blogCount", blogCount);
        model.addAttribute("blogPageInfo", blogPageInfo);
        model.addAttribute("typePageInfo", typePageInfo);
    }

}
