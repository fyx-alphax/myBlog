package com.program.blog.controller.user;

import com.program.blog.entity.Blog;
import com.program.blog.service.user.UBlogService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/archives")
public class archiveController {
    final UBlogService uBlogService;

    public archiveController(UBlogService uBlogService) {
        this.uBlogService = uBlogService;
    }

    @GetMapping
    public String archivePage(Model model){
        List<Blog> blogList = uBlogService.listTimeLine();
        model.addAttribute("blogList", blogList);
        return "archives";
    }
}
