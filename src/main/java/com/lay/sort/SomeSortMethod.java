package com.lay.sort;

import java.util.Arrays;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2021/7/15 15:25
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2021/7/15 lei.yue 1.0 create file
 */
public class SomeSortMethod {

    /**
     * 冒泡
     */
    public static void bubbleSort(){
        int arr[] = {8,9,7,2,10,4,1,3};
        for (int i = 0; i < arr.length; i++) {
            //外层循环，遍历次数
            for (int j = 0; j < arr.length - i - 1; j++) {
                //内层循环，升序（如果前一个值比后一个值大，则交换）
                //内层循环一次，获取一个最大值
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        for (int i : arr){
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
//        bubbleSort();
    }
}
