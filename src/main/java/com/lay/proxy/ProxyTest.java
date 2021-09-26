package com.lay.proxy;

import com.lay.proxy.dynamicProxy.JDKDynamicProxyInvocationHandler;
import com.lay.service.ITestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Proxy;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2021/9/26 16:56
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2021/9/26 lei.yue 1.0 create file
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProxyTest {

    @Autowired
    @Qualifier("staticProxySmsServiceImpl")//注入静态代理对象
    private ISmsService staticProxySmsServiceImpl;

    @Autowired
    @Qualifier("smsServiceImpl")//注入被jdk动态代理的目标对象
    private ISmsService target1;

    @Autowired
    @Qualifier("testServiceImpl1")
    private ITestService target2;

    @Test
    public void testStaticProxy() {
        staticProxySmsServiceImpl.send("SUCCESS");
    }

    @Test
    public void testDynamicProxy() {
        ISmsService smsService = (ISmsService) Proxy.newProxyInstance(target1.getClass().getClassLoader(), target1.getClass().getInterfaces(), new JDKDynamicProxyInvocationHandler(target1));//获取代理对象
        ITestService testService = (ITestService) Proxy.newProxyInstance(target2.getClass().getClassLoader(), target2.getClass().getInterfaces(), new JDKDynamicProxyInvocationHandler(target2));//获取代理对象
        smsService.send("我是JDK动态代理");
        testService.print();
    }

}
