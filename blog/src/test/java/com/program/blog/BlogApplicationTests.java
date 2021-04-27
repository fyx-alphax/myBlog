package com.program.blog;

import com.program.blog.service.user.UBlogService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class BlogApplicationTests {
    @Autowired
    UBlogService UBlogService;

    @Test
    void contextLoads() {
    }

}
