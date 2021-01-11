package com.lay.net;

import com.lay.bean.ResultBean;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2020/7/21 14:32
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2020/7/21 lei.yue 1.0 create file
 */
public class NetTest {

    public static List<String> strs = new ArrayList<>();

    static {
        strs.add("Tom");
        strs.add("Lay");
    }

    public static void main(String[] args) throws UnknownHostException {
        InetAddress myAddress = null;
        //myAddress = InetAddress.getLocalHost();
        myAddress = InetAddress.getByName("www.baidu.com");
        System.out.println(myAddress);

        ResultBean bean = new ResultBean();
        System.out.println(strs.contains(bean.getMsg()) + bean.getMsg());

        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
    }
}
