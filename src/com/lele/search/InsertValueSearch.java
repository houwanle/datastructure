package com.lele.search;

/**
 * author: hwl
 * date: 2020/10/23 7:30
 * version: 1.0.0
 * modified by:
 * description:
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }

//        int arr[] = {1,8,10,89,1000,1000,1234};
        int index = insertValueSearch(arr,0, arr.length - 1, 34);
        System.out.println("index = " + index);
    }

    /**
     * 插值查找算法，也要求数组有序
     * @param arr  数组
     * @param left  左边索引
     * @param right 右边索引
     * @param findVal  查找值
     * @return  如果找到，就返回对应的下标，如果没有找到，就返回-1
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
        // findVal < arr[0] 和 findVal > arr[arr.length - 1],避免数组越界
        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }

        // 求出 mid，自适应
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if (findVal > midVal) {
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }
}
