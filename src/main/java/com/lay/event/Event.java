package com.lay.event;

import org.springframework.context.ApplicationEvent;

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

//消息体
public class Event extends ApplicationEvent {
    public Event(Object source) {
        super(source);
    }

    private String orderCode;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }
}
