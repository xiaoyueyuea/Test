package com.lay.run;

import com.lay.event.EventPublisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2020/9/17 10:16
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2020/9/17 lei.yue 1.0 create file
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringEventTest {

    @Autowired
    private EventPublisher publisher;

    @Test
    public void test(){
        publisher.eventPublisher();
    }
}
