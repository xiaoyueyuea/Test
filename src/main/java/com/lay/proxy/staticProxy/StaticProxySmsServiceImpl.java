package com.lay.proxy.staticProxy;

import com.lay.proxy.ISmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2021/9/26 16:50
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2021/9/26 lei.yue 1.0 create file
 */
//静态代理 与被代理对象实现同一个接口
@Service("staticProxySmsServiceImpl")
public class StaticProxySmsServiceImpl implements ISmsService {

    @Autowired
    @Qualifier("smsServiceImpl")//注入被代理的目标对象
    private ISmsService smsService;

    @Override
    public void send(String message) {
        System.out.println("发送消息前doSomething");
        smsService.send(message);
        System.out.println("发送消息后doSomething");
    }
}
