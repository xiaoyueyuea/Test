package com.lay.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2021/9/2 17:40
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2021/9/2 lei.yue 1.0 create file
 */
@Configuration
public class MyConfig {

    @Bean(name = "Result")
    public ResultBean getResultBean(){
        ResultBean resultBean = new ResultBean();
        System.out.println("ResultBean----加入容器：" + resultBean);
        return resultBean;
    }
}
