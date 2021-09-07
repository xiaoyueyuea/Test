package com.lay.service.impl;

import com.lay.service.ITestService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2021/9/7 16:26
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2021/9/7 lei.yue 1.0 create file
 */
@Service
@Order(1)
public class TestServiceImpl2 implements ITestService {
    @Override
    public void print() {
        System.out.println("我是TestServiceImpl2");
    }
}
