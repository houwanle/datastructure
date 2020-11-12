package com.lele.tree;

import java.util.Arrays;

/**
 * author: hwl
 * date: 2020/11/12 7:29
 * version: 1.0.0
 * modified by:
 * description:
 */
public class HeapSort {

    public static void main(String[] args) {
        // 升序排序
        int arr[] = {4,6,8,5,9};
        heapSort(arr);
    }

    public static void heapSort(int arr[]) {
        System.out.println("堆排序！");

        // 分步完成
        adjustHeap(arr, 1, arr.length);
        System.out.println("第一次" + Arrays.toString(arr));// 4,9,8,5,6
    }

    /**
     * 将一个数组（二叉树），调整成一个大顶堆
     * 功能：完成将以i对应的非叶子节点的树调整成大顶堆
     * 举例 int arr[] = {4,6,8,5,9}; => i=1 =>adjustHeap => 得到{4,9,8,5,6}
     * 如果再次调用 adjustHeap 传入的是 i=0 => 得到{4,9,,8,5,6} => {9,6,8,5,4}
     * @param arr 待调整的数组
     * @param i
     * @param length
     */
    public static void adjustHeap(int arr[], int i, int length) {
        int temp = arr[i];// 先取出当前元素的值，保存在临时变量
        // 开始调整
        // 说明
        // 1. k=1*2 + 1  k是i结点的左子节点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k+1]) { // 说明左子节点的值小于右子节点的值
                k++; // k指向右子节点
            }
            if (arr[k] > temp) { // 如果子节点大于父结点
                arr[i] = arr[k];// 把较大的值赋给当前结点
                i = k;// i指向k，继续循环比较
            } else {
                break;
            }
        }
        // 当for循环结束后，我们已经将以i为父结点的树的最大值，放在了 最顶（局部）
        arr[i] = temp;// 将temp值放到调整后的位置
    }




}
