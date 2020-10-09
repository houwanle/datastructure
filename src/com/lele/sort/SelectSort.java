package com.lele.sort;

import java.util.Arrays;

/**
 * author: hwl
 * date: 2020/10/10 7:09
 * version: 1.0.0
 * modified by:
 * description: 选择排序
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] arr = {101,34,119,1};
        selectSort(arr);
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

            System.out.println("第"+(i+1)+"轮排序后");
            System.out.println(Arrays.toString(arr));
        }
    }
}
