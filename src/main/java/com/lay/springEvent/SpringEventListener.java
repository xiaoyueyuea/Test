package com.lay.springEvent;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2020/9/17 10:17
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2020/9/17 lei.yue 1.0 create file
 */
//监听事件
@Component
public class SpringEventListener {

    @EventListener
    public void handleEvent(Event event){
        System.out.println("event触发成功：" + event.getOrderCode());
    }
}
