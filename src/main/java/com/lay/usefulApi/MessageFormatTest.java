package com.lay.usefulApi;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2020/8/31 16:03
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2020/8/31 lei.yue 1.0 create file
 */
//动态拼接字符串
public class MessageFormatTest {

    public static void main(String[] args) {
        String str = "我是{0},性别{1}";
        String newStr = MessageFormat.format(str,"Lay","男");
        System.out.println(newStr);

        try {
            final Date now = new Date();
            System.out.println(now);
            final Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(now));
            System.out.println(date);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
