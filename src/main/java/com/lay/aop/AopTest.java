package com.lay.aop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2021/7/2 11:19
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2021/7/2 lei.yue 1.0 create file
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AopTest {

    @Autowired
    private JoinPointMethod joinPointMethod;

    @Test
    public void test(){
        joinPointMethod.printSomething();
    }
}
