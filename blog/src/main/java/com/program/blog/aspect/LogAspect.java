package com.program.blog.aspect;

import lombok.*;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LogAspect {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.program.blog.controller.*.*.*(..))")
    public void log(){}

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();

        String url = httpServletRequest.getRequestURL().toString();
        String ip = httpServletRequest.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        RequestLog requestLog = new RequestLog(url,ip,classMethod,args);
        logger.info("Request: {}", requestLog.toString());
    }

    @After("log()")
    public void doAfter(){

    }

    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterLog(Object result){
        logger.info("Result : {}", result);
    }

    @Data
    @AllArgsConstructor
    private class RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;
    }
}
