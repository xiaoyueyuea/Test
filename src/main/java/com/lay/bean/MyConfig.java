package com.lay.bean;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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

    //嵌套配置类必须声明为static
    @Configuration
    @ConditionalOnBean(name = "myConfig")
    public static class InnerConfig{

        @Bean(name = "anotherResult")
        @ConditionalOnMissingBean(name = "Result")
        public ResultBean getAnotherResult(){
            System.out.println("嵌套配置类未找到Result");//静态内部类优先执行，此时getResultBean()还未执行
            return new ResultBean();
        }
    }
}
