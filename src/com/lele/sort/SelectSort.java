package com.lele.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * author: hwl
 * date: 2020/10/10 7:09
 * version: 1.0.0
 * modified by:
 * description: 选择排序
 */
public class SelectSort {

    public static void main(String[] args) {
//        int[] arr = {101,34,119,1,-1,90,123};
//        System.out.println("排序前：");
//        System.out.println(Arrays.toString(arr));
//        selectSort(arr);
//        System.out.println("排序后：");
//        System.out.println(Arrays.toString(arr));

        // 创建一个80000个数的随机数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random()*8000000); // 生成一个[0,8000000)数
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是：" + date1Str);

        selectSort(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序前的时间是：" + date2Str);

    }

    // 选择排序
    public static void selectSort(int[] arr) {
        // 选择排序的时间复杂度是O(n^2)
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) { //说明假定的最小值，并不是最小
                    min = arr[j]; // 重置min
                    minIndex = j; // 重置minIndex
                }
            }

            // 将最小值，放在arr[0]，即交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }

//            System.out.println("第"+(i+1)+"轮排序后");
//            System.out.println(Arrays.toString(arr));
        }
    }
}
