package com.program.blog.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.program.blog.entity.*;
import com.program.blog.service.admin.BlogService;
import com.program.blog.service.admin.TypeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;

@Slf4j
@Controller
@RequestMapping("/admin/blogs")
public class BlogController {
    final BlogService blogService;
    final TypeService typeService;

    public BlogController(BlogService blogService, TypeService typeService) {
        this.blogService = blogService;
        this.typeService = typeService;
    }

    @GetMapping
    public String blogsPage(Model model,
                        @RequestParam(value = "pageNum", required = false,defaultValue = "1") String pageNum){
        PageInfo<Type> typePageInfo = typeService.listType();
        PageInfo<Blog> blogPageInfo = blogService.listBlog(pageNum);
        model.addAttribute("typePageInfo", typePageInfo);
        model.addAttribute("blogPageInfo", blogPageInfo);
        return "admin/blogs";
    }

    @PostMapping("/search")
    public String searchBlogs(BlogQuery blogQuery,
                              Model model,
                              @RequestParam(value = "pageNum", required = false,defaultValue = "1") String pageNum){
        PageInfo<Blog> blogPageInfo = blogService.listBlog(pageNum, blogQuery);
        model.addAttribute("blogPageInfo", blogPageInfo);
        return "admin/blogs :: blogList";
    }

    @GetMapping("/input")
    public String blogAddPage(Model model){
        PageInfo<Type> typePageInfo = typeService.listType();
        model.addAttribute("typePageInfo", typePageInfo);
        model.addAttribute("blog", new Blog());
        return "admin/blogs-input";
    }

    @PostMapping("/new")
    public String blogAdd(Blog blog,
                          HttpSession session,
                          Model model,
                          RedirectAttributes attributes){
        if (blogService.getBlogIdByTitle(blog.getTitle()) != null){
            PageInfo<Type> typePageInfo = typeService.listType();
            model.addAttribute("typePageInfo", typePageInfo);
            model.addAttribute("message", "标题已存在，请重新输入标题");
            return "admin/blogs-input";
        }
//        User user = (User) session.getAttribute("user");
//        blog.setWriter(user);
        User user = new User();
        user.setId(1L);
        blog.setWriter(user);
        blog.setViewCount(0);
        Blog blog1 = blogService.saveBlog(blog);
        if (blog1 != null){
            attributes.addFlashAttribute("message", "success");
        }else {
            attributes.addFlashAttribute("message", "error");
        }
        return "redirect:/admin/blogs";
    }

    @GetMapping("/{id}/delete")
    public String blogDelete(@PathVariable("id") Long id,
                             RedirectAttributes attributes){
        Blog blog = blogService.deleteBlog(id);
        if (blog != null){
            attributes.addFlashAttribute("message", "success");
        }else{
            attributes.addFlashAttribute("message", "error");
        }
        return "redirect:/admin/blogs";
    }

    @GetMapping("/{id}/edit")
    public String blogEditPage(@PathVariable("id") Long id,
                               Model model){
        Blog blog = blogService.getBlog(id);
        PageInfo<Type> typePageInfo = typeService.listType();
        model.addAttribute("blog", blog);
        model.addAttribute("typePageInfo", typePageInfo);
        return "admin/blogs-input";
    }

    @PostMapping("/{id}/edit")
    public String blogEdit(@PathVariable("id") Long id,
                           Blog blog,
                           Model model,
                           RedirectAttributes attributes){
        if (blogService.getBlogIdByTitle(blog.getTitle()) != null && !blogService.getBlogIdByTitle(blog.getTitle()).equals(blog.getId())){
            PageInfo<Type> typePageInfo = typeService.listType();
            model.addAttribute("typePageInfo", typePageInfo);
            model.addAttribute("message", "标题已存在，请重新输入标题");
            return "admin/blogs-input";
        }
        Blog blog1 = blogService.updateBlog(blog);
        if (blog1 != null){
            attributes.addFlashAttribute("message", "success");
        }else {
            attributes.addFlashAttribute("message", "error");
        }
        return "redirect:/admin/blogs";
    }
}
