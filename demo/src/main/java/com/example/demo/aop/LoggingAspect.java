package com.example.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.example.demo.controller.*.*(..)) || execution(* com.example.demo.service.*.*(..))")
    public void logBeforeMethod(JoinPoint joinPoint) {
        logger.info("Method called: " + joinPoint.getSignature().getName() + " with arguments: " + joinPoint.getArgs());
    }

    @AfterReturning(value = "execution(* com.example.demo.controller.*.*(..)) || execution(* com.example.demo.service.*.*(..))", returning = "result")
    public void logAfterMethod(JoinPoint joinPoint, Object result) {
        logger.info("Method finished: " + joinPoint.getSignature().getName() + " with result: " + result);
    }
}
