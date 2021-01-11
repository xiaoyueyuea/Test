package com.lay.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2020/9/17 10:18
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2020/9/17 lei.yue 1.0 create file
 */
//发布事件
@Component
public class EventPublisher {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void eventPublisher(){
        Event event = new Event(this);
        event.setOrderCode("hhhhhhh");
        eventPublisher.publishEvent(event);

    }
}
