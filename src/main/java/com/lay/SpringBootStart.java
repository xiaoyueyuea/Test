package com.lay;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2020/7/24 12:18
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2020/7/24 lei.yue 1.0 create file
 */

@SpringBootApplication
@MapperScan("com.yl.mybatis.mapper")//扫描mapper
public class SpringBootStart {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootStart.class,args);
    }
}
