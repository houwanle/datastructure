package com.lele.search;

import java.util.Arrays;

/**
 * author: hwl
 * date: 2020/10/24 11:13
 * version: 1.0.0
 * modified by:
 * description:
 */
public class FibonacciSearch {

    public static int maxSize = 20;
    public static void main(String[] args) {
        int[] arr = {1,8,10,89,1000,1234};

        System.out.println("index=" + fibSearch(arr, 1234));
    }

    /**
     * 因为后面我们 mid = low + F(k-1)-1,需要使用到斐波那契数列，因此需要先获取到一个斐波那契数列
     * 非递归方法得到一个斐波那契数列
     * @return
     */
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
     * 编写斐波那契查找算法
     * 使用非递归的方式编写算法
     * @param a 数组
     * @param key 需要查找的关键码（值）
     * @return 返回对应的下标，如果没有，则返回-1
     */
    public static int fibSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int k = 0;// 表示斐波那契分割数值的下标
        int mid = 0;// 存放mid值
        int f[] = fib(); // 获取到斐波那契数列
        // 获取到斐波那契分割数值的下标
        while(high > f[k] - 1) {
            k++;
        }
        // 因为f[k]值 可能大于a的长度，因此我们需要使用Arrays类，构造一个新的数组，并指向temp[]
        // 不足的部分会使用0填充
        int[] temp = Arrays.copyOf(a, f[k]);
        // 实际上需求使用a数组最后的数填充temp
        // temp = {1,8,10,89,1000,1234,0,0} => {1,8,10,89,1000,1234,1234,1234}
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = a[high];
        }
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) { // 向左查找
                high = mid - 1;
                /**
                 * 说明：
                 * 1. 全部元素 = 前面的元素 + 后面元素
                 * 2. f[k] = f[k-1]+f[k-2]
                 * 因为 前面有 f[k-1]个元素，所以可以继续拆分 f[k-1] = f[k-2]+f[k-3], 即 在 f[k-1] 的前面继续查找 k--
                 * 即 下次循环 mid = f[k-1-1]-1
                 */
                k--;
            } else if (key > temp[mid]) { // 继续向右查找
                low = mid + 1;
                /**
                 * 说明
                 * 1.全部元素 = 前面的元素 + 后面的元素
                 * 2.f[k] = f[k-1]+f[k-2]
                 * 3.因为后面我们有 f[k-2] 所以可以继续拆分 f[k-1] = f[k-3]+f[k-4]
                 * 4.即 在 f[k-2] 的前面进行查找 k-=2
                 * 5.即下次循环 mid = f[k-1-2] - 1
                 */
                k -= 2;
            } else {
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }
}