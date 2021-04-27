package com.program.blog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.program.blog.entity.Type;
import com.program.blog.service.admin.TypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;


@Slf4j
@Controller
@RequestMapping("/admin/types")
public class TypeController {
    final TypeService typeService;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping
    public String typePage(Model model,
                           @RequestParam(value = "pageNum", required = false,defaultValue = "1") String pageNum){
        PageInfo<Type> typePageInfo = typeService.listType(pageNum);
        model.addAttribute("typePageInfo", typePageInfo);
        return "admin/types";
    }


    @GetMapping("/input")
    public String typeAddPage(Model model){
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    @PostMapping("/new")
    public String typeAdd(@Valid Type type,
                          BindingResult bindingResult,
                          Model model,
                          RedirectAttributes attributes){
        if (bindingResult.hasErrors()){
            model.addAttribute("message","分类名称不能为空，请输入后提交。");
            return "admin/types-input";
        }
        if (typeService.getType(type.getName()) != null){
            model.addAttribute("message", "分类已存在,请重新输入名称！");
            return "admin/types-input";
        }
        Type type1 = typeService.addType(type.getName());
        if (type1 != null){
            attributes.addFlashAttribute("message", "success");
        }else {
            attributes.addFlashAttribute("message", "error");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/{id}/delete")
    public String typeDelete(@PathVariable("id") Long id,
                             RedirectAttributes attributes){
        Type type = typeService.deleteType(id);
        if (type != null){
            attributes.addFlashAttribute("message", "success");
        }else{
            attributes.addFlashAttribute("message", "error");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/{id}/edit")
    public String typeEditPage(@PathVariable("id") Long id,
                               Model model){
        Type type = typeService.getType(id);
        model.addAttribute("type", type);
        return "admin/types-input";
    }

    @PostMapping("/{id}/edit")
    public String typeEdit(@Valid Type type,
                           BindingResult bindingResult,
                           @PathVariable("id") Long id,
                           RedirectAttributes attributes,
                           Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("message", "分类名称不能为空，请输入后提交。");
            model.addAttribute("type", type);
            return "admin/types-input";
        }
        if (typeService.getType(type.getName()) != null){
            model.addAttribute("message", "分类已存在,请重新输入名称！");
            model.addAttribute("type", type);
            return "admin/types-input";
        }
        Type preType = typeService.updateType(id, type);
        if (preType != null){
            attributes.addFlashAttribute("message", "success");
        }else {
            attributes.addFlashAttribute("message", "error");
        }
        return "redirect:/admin/types";
    }
}
