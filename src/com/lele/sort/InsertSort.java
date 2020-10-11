package com.lele.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * author: hwl
 * date: 2020/10/12 7:02
 * version: 1.0.0
 * modified by:
 * description:
 */
public class InsertSort {

    public static void main(String[] args) {
//        int[] arr = {101,34,119,1,-1,89};
        // 创建一个 有80000 个随机数的数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random()*8000000);
        }

//        System.out.println("排序前：");
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是：" + date1Str);

        insertSort(arr);
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是：" + date2Str);
//        System.out.println(Arrays.toString(arr));
    }

    // 插入排序
    public static void insertSort(int[] arr) {
        int insertVal = 0;
        int insertIndex = 0;

        for (int i = 1; i < arr.length;i++) {
            //定义待插入的数
            insertVal = arr[i];
            insertIndex = i - 1;


            // 给insertVal找到插入的位置
            /**
             * 说明：
             * 1. inserIndex >= 0 保证在给 insertVal 找插入位置，不越界；
             * 2. insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
             * 3. 需要将 arr[insertIndex] 后移
             */
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];//arr[insertIndex]
                insertIndex--;
            }
            // 当退出while循环时，说明插入的位置找到，insertIndex+1
            // 判断是否需要赋值
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }

//            System.out.println("第"+i+"轮插入");
//            System.out.println(Arrays.toString(arr));

        }
    }
}
