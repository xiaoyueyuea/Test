package com.lay.mq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2020/11/10 10:43
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2020/11/10 lei.yue 1.0 create file
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisMQTest {

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Test
    public void test(){
        //设置值
        //redisTemplate.boundValueOps("name").set("Lay");
        //设置过期时间
        //redisTemplate.boundValueOps("name").expire(1, TimeUnit.MINUTES);
//        String name = (String) redisTemplate.boundValueOps("name").get();
//        System.out.println(name);

        List<String> str1 = new ArrayList<>();
        List<String> str2 = new ArrayList<>();
        str1.add("2");
//        str2.add("1");
//        str1.addAll(str2);
//        System.out.println(str1);
//        StringBuilder sb = new StringBuilder();
//        if(sb.toString().equals("")){
//            System.out.println(1);
//        }else {
//            System.out.println(0);
//        }

//        String a = null;
//        String b = "2";
//        System.out.println(a + b);

//        String s = LocalDate.of(2021, 8, 11).toString();
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate parse = LocalDate.parse(s, dateTimeFormatter);
//        System.out.println(parse);
//        String sss = parse.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日"));
//        System.out.println(sss);

//        Instant instant = Instant.now();
//        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
//        System.out.println(zdt.getYear() + "-" + zdt.getMonthValue() + "-" + zdt.getDayOfMonth() + " " + zdt.getHour() + ":" + zdt.getMinute() + ":" + zdt.getSecond());
//        System.out.println(instant);
//        System.out.println(zdt);
//        System.out.println(zdt.toLocalDateTime());
//        LocalDate localDate = zdt.toLocalDateTime().toLocalDate();

//        final LocalDateTime endLocalDateTime = LocalDateTime.now();
//        final LocalDateTime beginLocalDateTime = LocalDateTime.of(endLocalDateTime.getYear(),endLocalDateTime.getMonthValue(),1,0,0);
//        System.out.println(beginLocalDateTime);

        Random random = new Random();
        for (int i = 0;i< 100;i++){
            System.out.println(random.nextInt(2));
        }
    }


}
