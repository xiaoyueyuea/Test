package com.lay;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Question2 {

    public static void main(String[] args) {
        int[][] weight = {{2,4,6,8,7,9}, {3,8,9,1,4,5}, {6,9,12,2,15,11}, {8,7,18,7,13,2}, {7,2,10,4,3,7}, {9,5,6,17,5,1}};
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "A");
        map.put(1, "B");
        map.put(2, "C");
        map.put(3, "D");
        map.put(4, "E");
        map.put(5, "F");

        int x = 0;
        int y = 0;
        int total = weight[0][0];
        boolean deepX = false;
        boolean deepY = false;

        StringJoiner stringJoiner = new StringJoiner("->");
        stringJoiner.add("A1");

        while(x != (weight.length - 1) || y != (weight[0].length - 1)) {
            if(weight[x + 1][y] <= weight[x][y + 1]) {
                x = x + 1;
            }
            if (weight[x + 1][y] >= weight[x][y + 1]) {
                y = y + 1;
            }


            total = total + weight[x][y];
            stringJoiner.add(map.get(y) + (x + 1));
        }



        System.out.println("路径：" + stringJoiner.toString());
        System.out.println("最短用时：" + total);
    }
}
