package com.lay.mq;

import com.lay.bean.MyProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
//
//    @Autowired
//    private RedisTemplate<Object,Object> redisTemplate;

    @Autowired
    private MyProperties myProperties;

    @Test
    public void test(){
//        //设置值
//        redisTemplate.boundValueOps("name").set("Lay");
        //设置过期时间
        //redisTemplate.boundValueOps("name").expire(1, TimeUnit.MINUTES);
//        String name = (String) redisTemplate.boundValueOps("name").get();
//        System.out.println(name);


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


        System.out.println(RedisMQTest.class.getSimpleName());

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        final int currentDay = calendar.get(Calendar.DATE);
        System.out.println(currentDay);

        System.out.println(true && false || true);

        ////////tttttttttttttt
    }


}
