package com.program.blog.service.user;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.program.blog.entity.Blog;
import com.program.blog.entity.Type;
import com.program.blog.exception.NotFoundException;
import com.program.blog.repository.user.UBlogRepository;
import com.program.blog.repository.user.UTypeRepository;
import com.program.blog.util.MarkdownUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UBlogServiceImpl implements UBlogService {
    final UBlogRepository uBlogRepository;
    final UTypeRepository uTypeRepository;

    public UBlogServiceImpl(UBlogRepository uBlogRepository, UTypeRepository uTypeRepository) {
        this.uBlogRepository = uBlogRepository;
        this.uTypeRepository = uTypeRepository;
    }

    @Override
    public int blogCount() {
        return uBlogRepository.publishedCount();
    }

    @Override
    public PageInfo<Blog> listBlog(String pageNum) {
        PageHelper.startPage(Integer.parseInt(pageNum), 5);
        List<Blog> blogList = uBlogRepository.getPublishedList();
        return new PageInfo<>(blogList);
    }

    @Override
    public PageInfo<Blog> listBlog() {
        PageHelper.startPage(0, 5);
        List<Blog> blogList = uBlogRepository.getPublishedList();
        return new PageInfo<>(blogList);
    }

    @Override
    public PageInfo<Blog> listBlog(String pageNum, String typeId) {
        if ("noType".equals(typeId)){
            listBlog(pageNum);
        }
        PageHelper.startPage(Integer.parseInt(pageNum), 5);
        List<Blog> blogList = uBlogRepository.listBlogByTypeId(Long.valueOf(typeId));
        return new PageInfo<>(blogList);
    }

    @Override
    public PageInfo<Blog> listRecommend() {
        PageHelper.startPage(0, 4);
        List<Blog> recommendList = uBlogRepository.listRecommend();
        return new PageInfo<>(recommendList);
    }

    @Override
    public PageInfo<Type> listType() {
        PageHelper.startPage(0, 5);
        List<Type> typeList = uTypeRepository.getList();
        return new PageInfo<>(typeList);
    }

    @Override
    public int totalViewCount() {
        return uBlogRepository.getTotalView();
    }

    @Override
    public Blog getBlogById(Long id) throws NotFoundException {
        Blog blog = uBlogRepository.getBlogById(id);
        if (blog == null){
            throw new NotFoundException("资源不存在");
        }
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(blog.getContent()));
        return blog;
    }

    @Override
    public List<Blog> listTimeLine() {
        return uBlogRepository.getPublishedList();
    }

    @Override
    public void setViewCount(Long id, int viewCount) {
        uBlogRepository.updateViewCount(id, viewCount);
    }

    @Override
    public PageInfo<Blog> searchBlog(String pageNum, String title) {
        if (pageNum == "1"){
            searchBlog(title);
        }
        PageHelper.startPage(Integer.parseInt(pageNum), 5);
        List<Blog> blogList = uBlogRepository.listBlogByTitle(title);
        return new PageInfo<>(blogList);
    }

    @Override
    public PageInfo<Blog> searchBlog(String title) {
        PageHelper.startPage(0, 5);
        List<Blog> blogList = uBlogRepository.listBlogByTitle(title);
        return new PageInfo<>(blogList);
    }
}
