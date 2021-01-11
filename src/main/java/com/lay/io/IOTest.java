package com.lay.io;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayInputStream;


/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2020/9/28 15:00
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2020/9/28 lei.yue 1.0 create file
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IOTest {

    @Test
    public void Test(){
        ByteArrayInputStream inputStream = new ByteArrayInputStream("岳磊".getBytes());
        System.out.println(inputStream);
        //ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    }
}
