package com.lay.service.run;

import com.lay.service.ITestService;
import com.lay.service.impl.TestServiceImpl2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2021/9/7 17:23
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2021/9/7 lei.yue 1.0 create file
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RunSomeThing {

    @Autowired
    private List<ITestService> testServices;//一个接口被多个类实现，可用List自动注入

    @Autowired//默认byType注入
    @Qualifier(value = "testServiceImpl2")//通过指定IOC中生成的beanName来判断注入哪一个(byName)
    private ITestService testService1;

    @Resource(name = "testServiceImpl2",type = TestServiceImpl2.class)//默认通过byName注入,也可使用type属性通过byType注入。若两个属性都配置了，则注入都满足的bean
    private ITestService testService2;

    @Test
    public void test(){
        for (ITestService t : testServices){
            t.print();//按照实现类上@Order标注的顺序注入(值越小，优先级越高)  应用:一个公共短信发送接口，多个短信平台网关实现了此接口,可配合@Order实现定义优先使用哪个短信平台发送短信
        }
    }
}
