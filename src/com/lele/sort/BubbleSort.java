package com.lele.sort;

import java.util.Arrays;

/**
 * author: hwl
 * date: 2020/10/8 21:10
 * version: 1.0.0
 * modified by:
 * description: 冒泡排序
 */
public class BubbleSort {

    public static void main(String[] args) {

        int arr[] = {3,9,-1,10,-2};
        bubbleSort(arr);
//        System.out.println(Arrays.toString(arr));

    }

    // 冒泡排序的时间复杂度：O(n^2)
    private static void bubbleSort(int[] arr) {
        int temp = 0;// 临时变量
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                // 如果前面的数比后面的数大，则交换
                if (arr[j] > arr[j+1]) {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            System.out.println("第"+(i+1)+"趟排序后的数组：");
            System.out.println(Arrays.toString(arr));
        }
    }
}
