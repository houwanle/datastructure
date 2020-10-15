package com.lele.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * author: hwl
 * date: 2020/10/15 21:24
 * version: 1.0.0
 * modified by:
 * description:
 */
public class QuickSort {

    public static void main(String[] args) {
//        int[] arr = {-9,78,0,23,-567,70,-1,900,4561};

        // 创建一个 有80000 个随机数的数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random()*8000000);
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是：" + date1Str);

        quickSort(arr, 0, arr.length-1);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是：" + date2Str);

//        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left;// 左下标
        int r = right;// 右下标
        // pivot 中轴值
        int pivot = arr[(left + right) / 2];
        int temp = 0;// 临时变量，作为交换时使用
        // while循环的目的是让比pivot值小的放到左边，比pivot值大的放到右边
        while(l < r) {
            // 在pivot的左边一直找，找到大于等于pivot值，才退出
            while(arr[l] < pivot) {
                l++;
            }

            // 在pivot的右边一直找，找到小于等于pivot值，才退出
            while(arr[r] > pivot) {
                r--;
            }
            // 如果 l >= r 说明pivot的左右两边的值，已经按照左边全部是小于等于 pivot 值，右边全部是大于等于pivot值
            if (l >= r) {
                break;
            }

            // 交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            // 如果交换完后，发现这个arr[l] == pivot 值 相等 r--，前移
            if (arr[l] == pivot) {
                r--;
            }

            // 如果交换完后，发现这个arr[r] == pivot 值 相等 l++，后移
            if (arr[r] == pivot) {
                l++;
            }
        }
        // 如果 l==r，必须l++,r--,否则出现栈溢出
        if (l == r) {
            l++;
            r--;
        }
        // 向左递归
        if (left < r) {
            quickSort(arr,left, r);
        }
        // 向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }
    }
}
