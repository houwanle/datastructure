package com.lele.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * author: hwl
 * date: 2020/10/13 7:26
 * version: 1.0.0
 * modified by:
 * description:
 */
public class ShellSort {

    public static void main(String[] args) {
//        int[] arr = {8,9,1,7,2,3,5,4,6,0};

        // 创建一个 有80000 个随机数的数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random()*8000000);
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是：" + date1Str);

        shellSort(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是：" + date2Str);
    }

    // 希尔排序时，对有序序列在插入时采用交换法，
    public static void shellSort(int[] arr) {
        int temp = 0;
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                // 遍历各组中所有的元素（共gap组，每组有个元素），步长gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    // 如果当前元素大于加上步长后的那个元素，则交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
//            System.out.println("希尔排序第"+(++count)+"轮 =" + Arrays.toString(arr));
        }
    }







}
