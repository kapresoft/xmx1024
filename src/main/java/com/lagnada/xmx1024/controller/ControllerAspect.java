package com.lagnada.xmx1024.controller;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerAspect {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void inControllerMethod() {
    }

    @Pointcut("@annotation(com.lagnada.xmx1024.controller.MyAnnotation)")
    public void inMyAnnotation() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.ResponseBody)")
    public void hasResponseBody() {
    }

}
