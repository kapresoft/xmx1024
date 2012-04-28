package com.lagnada.xmx1024.controller;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BeforeAdvice {

    @Around("com.lagnada.xmx1024.controller.ControllerAspect.inMyAnnotation()")
    public Object doAccessCheck(ProceedingJoinPoint jp) throws Throwable {
        "".toString();
        Object rv = null;
        try {
            rv = jp.proceed();
        } finally {
            System.out.println("doAccessCheck...");
        }
        return rv;
    }

    //@Before("com.lagnada.xmx1024.controller.ControllerAspect.inMyAnnotation()")
    //public void before() {
    //    "".toString();
    //}

}
