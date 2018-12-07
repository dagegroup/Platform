package com.dage.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @className:LogUtil
 * @discription:切面的实现类（通知类）
 * @author:ProMonkey-K
 * @creatTime:2018-11-23 18:02
 */
@Component
@Aspect
public class LogUtil {

    /**
     * 切入点配置
     */
    @Pointcut(value = "execution(* com.aaa.sboot.service..*.*(..))")
    public void one(){}

    /**
     * 前置通知
     * @param joinPoint
     */
    @Before(value = "one()")
    public void before(JoinPoint joinPoint){
        String name = joinPoint.getTarget().getClass().getName();
        System.out.println("在调用"+name+"的"+joinPoint.getSignature().getName()+"方法之前，做打印...");
    }

    @AfterReturning(value = "one()")
    public void afterReturning(JoinPoint joinPoint){
        String name = joinPoint.getTarget().getClass().getName();
        System.out.println("在调用"+name+"的"+joinPoint.getSignature().getName()+"方法之后，做打印...");
    }

    @AfterThrowing(value = "one()",throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint,Exception ex){
        String name = joinPoint.getTarget().getClass().getName();
        String name1 = ex.getClass().getName();
        System.out.println("在调用"+name+"的"+
                joinPoint.getSignature().getName()+"方法之前出现了"+name1+"异常");
    }
    @After(value = "one()")
    public void after(JoinPoint joinPoint){
        String name = joinPoint.getTarget().getClass().getName();
        System.out.println("在调用"+name+"的"+joinPoint.getSignature().getName()+"方法之后，做打印...");
    }
//    @Around(value = "one()")
//    public Object arround(ProceedingJoinPoint proceedingJoinPoint){
//        Object proceed = null;
//
//        try {
//            proceed = proceedingJoinPoint.proceed();
//
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//        return proceed;
//    }
}
