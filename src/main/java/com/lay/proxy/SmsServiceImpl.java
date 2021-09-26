package com.lay.proxy;

import org.springframework.stereotype.Service;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2021/9/26 16:47
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2021/9/26 lei.yue 1.0 create file
 */
@Service("smsServiceImpl")
public class SmsServiceImpl implements ISmsService{

    @Override
    public void send(String message) {
        System.out.println("收到新消息：" + message);
    }
}
