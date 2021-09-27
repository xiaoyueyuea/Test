package com.lay.proxy;

import com.lay.proxy.dynamicProxy.CGLIBDynamicProxyMethodInterceptor;
import com.lay.proxy.dynamicProxy.JDKDynamicProxyInvocationHandler;
import com.lay.service.ITestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.proxy.Enhancer;
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
    public void testJDKDynamicProxy() {
        ISmsService smsService = (ISmsService) Proxy.newProxyInstance(target1.getClass().getClassLoader(), target1.getClass().getInterfaces(), new JDKDynamicProxyInvocationHandler(target1));//获取代理对象
        ITestService testService = (ITestService) Proxy.newProxyInstance(target2.getClass().getClassLoader(),//类加载器
                target2.getClass().getInterfaces(),//被代理类实现的一些接口
                new JDKDynamicProxyInvocationHandler(target2));//实现了InvocationHandler接口的对象
        smsService.send("我是JDK动态代理");//当动态代理对象调用一个方法时，这个方法的调用会被转发到实现了InvocationHandler接口对象的invoke方法
        testService.print();
    }

    @Test
    public void testCGLIBDynamicProxy(){
        //创建动态代理增强类
        Enhancer enhancer = new Enhancer();
        //设置类加载器
        enhancer.setClassLoader(SmsServiceImpl.class.getClassLoader());
        //设置被代理类
        enhancer.setSuperclass(SmsServiceImpl.class);
        //设置方法拦截器
        enhancer.setCallback(new CGLIBDynamicProxyMethodInterceptor());
        //生成动态代理类
        Object o = enhancer.create();

        SmsServiceImpl smsServiceImpl = (SmsServiceImpl)o;
        smsServiceImpl.send("我是CGLIB动态代理");//被转发到实现了MethodInterceptor接口对象的intercept方法
    }

    /*JDK动态代理 VS CGLIB动态代理
     *1.JDK 动态代理只能代理实现了接口的类或者直接代理接口，而 CGLIB 可以代理未实现任何接口的类。另外， CGLIB 动态代理是通过生成一个被代理类的子类来拦截被代理类的方法调用，因此不能代理声明为 final 类型的类和方法。
     *2.就二者的效率来说，大部分情况都是 JDK 动态代理更优秀
     * 补充：Spring 中的 AOP 模块中：如果目标对象实现了接口，则默认采用 JDK 动态代理，否则采用 CGLIB 动态代理。
     */

}
