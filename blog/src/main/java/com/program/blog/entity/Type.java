package com.program.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Type {

    private Long id;

    @NotBlank(message = "分类名称不能为空！")
    private String name;
    private int blogCount;
    private List<Blog> blogList;
}
