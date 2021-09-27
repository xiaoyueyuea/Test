package com.lay.proxy.dynamicProxy;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2021/9/27 15:18
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2021/9/27 lei.yue 1.0 create file
 */
//CGLIB动态代理 实现MethodInterceptor接口（在spring.core包中）
public class CGLIBDynamicProxyMethodInterceptor implements MethodInterceptor {

    /**
     *
     * @param o 被代理对象(增强的对象)
     * @param method 被拦截的方法(增强的方法)
     * @param objects 方法入参
     * @param methodProxy 用于调用原始方法
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("--------------------------------");
        System.out.println("CGLIB动态代理before");
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("CGLIB动态代理after");
        return result;
    }

}
