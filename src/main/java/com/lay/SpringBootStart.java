package com.lay;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

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
@MapperScan("com.yl.mybatis.mapper")//扫描所有mapper
public class SpringBootStart {

    //默认扫描主程序所在包及其下面的所有子包里面的组件
    //各种配置拥有默认值,位于指定的类中,application.yml文件中的的配置值会绑定到指定的类上，并被加载到容器中。（可查看运行后容器中组件：*Properties类）
    //按需加载配置:根据在pom中引入的相关stater(启动场景),自动配置功能都通过spring-boot-autoconfigure包实现
    public static void main(String[] args) {
//        SpringApplication.run(SpringBootStart.class, args);//正常启动只需这一行
        //springboot启动详情
        //返回IOC容器
        ConfigurableApplicationContext run = SpringApplication.run(SpringBootStart.class, args);
        //查看容器里的组件
        String[] beanDefinitionNames = run.getBeanDefinitionNames();
        for (String name : beanDefinitionNames){
            System.out.println(name);
        }
    }
}
