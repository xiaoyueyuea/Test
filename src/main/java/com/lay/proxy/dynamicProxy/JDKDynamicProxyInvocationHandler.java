package com.lay.proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2021/9/26 17:06
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2021/9/26 lei.yue 1.0 create file
 */
//jdk动态代理 使用条件：只能代理实现了接口的类 应用场景：为指定的方法增加日志
public class JDKDynamicProxyInvocationHandler implements InvocationHandler {

    private final Object target;//目标对象

    public JDKDynamicProxyInvocationHandler(Object target) {
        this.target = target;
    }

    /**
     *
     * @param proxy 动态生成的代理类
     * @param method 与代理类调用的方法对应
     * @param args 当前method方法的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("--------------------------------");
        System.out.println("代理类:" + proxy.getClass());
        if(args != null && args.length > 0){
            System.out.println("参数:" + args[0]);
        }
        System.out.println("JDK动态代理:before  " + method.getName());
        Object invoke = method.invoke(target, args);
        System.out.println("JDK动态代理:after  " + method.getName());
        return invoke;
    }

}
