package com.lay.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2021/9/24 10:25
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2021/9/24 lei.yue 1.0 create file
 */
@Component
@ConfigurationProperties(prefix = "my.properties")//通过前缀与配置文件绑定
public class MyProperties {
    private String author;

    private Integer age;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
