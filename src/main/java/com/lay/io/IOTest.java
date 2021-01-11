package com.lay.io;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lay.bean.BHReportQueryResponseBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;


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
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    }

    @Test
    public void Test1(){
        String str = "{\"reportHeader\":{\"reportId\":\"BH1808282011450000000001\",\"reportTime\":\"2018-08-28T10:00:00\",\"queryResult\":1}}";
        String str1 = "{\n" +
                "\t\"reportHeader\": {\n" +
                "\t\t\"reportId\": \"BH1808282011450000000001\",\n" +
                "\t\t\"reportTime\": \"2018-08-28T10:00:00\",\n" +
                "\t\t\"queryResult\": 1\n" +
                "\t},\n" +
                "\t\"homeInfo\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"homeAddress\": \"上海市南京西路\",\n" +
                "\t\t\t\"date\": \"2018-08-12\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"homeAddress\": \"上海市南京路\",\n" +
                "\t\t\t\"date\": \"2018-07-12\"\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";
        JSONObject jsonData = JSONObject.parseObject(str1);
        BHReportQueryResponseBean bhReportQueryResponseBean = JSON.toJavaObject(jsonData,BHReportQueryResponseBean.class);
        System.out.println(bhReportQueryResponseBean.getHomeInfo().get(0).getHomeAddress());
        String str2 = "000077fc-29dd-49f3-9d93-c515cd3caac4";
        System.out.println(str2.split("-")[0]);
    }
}
