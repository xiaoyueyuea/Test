package com.lay.run;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lay.bean.ResultBean;
import com.lay.util.JsonResult;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2020/7/21 11:00
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2020/7/21 lei.yue 1.0 create file
 */
public class SortedMethod {

    public static void main(String[] args) throws IOException, ParseException {
        Integer[] arr = new Integer[6];
        arr[0] = 5;
        arr[1] = 2;
        arr[2] = 8;
        arr[3] = 7;
        arr[4] = 6;
        arr[5] = 1;
        for(int i =0;i<arr.length-1;i++){
            for(int j = i+1;j<arr.length;j++){
                if(arr[i] > arr[j]){
                    int tempt = arr[i];
                    arr[i]=arr[j];
                    arr[j] = tempt;
                }
            }
        }
        for(int k : arr){
            System.out.println(k);
        }

        List<Integer> arrayList = new ArrayList<>();
        Collections.addAll(arrayList,arr);
        arrayList.add(10);
        List<Integer> arrayList1 = new ArrayList<>(Arrays.asList(arr));
        arrayList1.add(10);
        arrayList1.remove(1);
        for(Integer i : arrayList1){
            System.out.println("数组转集合:" + i);
        }
        System.out.println(JSONObject.toJSONString(JsonResult.ok()));
        ObjectMapper mapper = new ObjectMapper();
        ResultBean bean = mapper.readValue(mapper.writeValueAsString(JsonResult.ok()),ResultBean.class);
        System.out.println(bean.getMsg());



        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        System.out.println(formatter.format(time));

        String id = "510182199805260813";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        final Date date = simpleDateFormat.parse(id.substring(6,14));
        System.out.println(date);
    }
}
