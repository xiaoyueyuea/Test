package com.lay.aop;

//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.springframework.stereotype.Component;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2021/7/2 10:58
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2021/7/2 lei.yue 1.0 create file
 */
//@Aspect
//@Component
//public class LogAspects {
//
//    @Pointcut("execution(public void com.lay.aop.JoinPointMethod.*(..))")
//    public void pointCut(){}
//
//    //@before代表在目标方法执行前切入, 并指定在哪个方法前切入
//    @Before("pointCut()")
//    public void logBefore(){
//        System.out.println("@Before");
//    }
//
//    @After("pointCut()")
//    public void logAfter(){
//        System.out.println("@After");
//    }
//
//    @Around("pointCut()")
//    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
//        System.out.println("@Around:执行目标方法之前");
//        Object obj = joinPoint.proceed();//手动执行目标方法
//        System.out.println("@Around:执行目标方法之后");
//        return obj;
//    }
//}
