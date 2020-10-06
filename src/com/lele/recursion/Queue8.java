package com.lele.recursion;

/**
 * author: hwl
 * date: 2020/10/6 16:56
 * version: 1.0.0
 * modified by:
 * description: 八皇后问题
 */
public class Queue8 {

    // 定义一个 max 表示共有多少个皇后
    int max = 8;
    //定义数组 array,保存皇后放置位置的结果，比如 arr = {0,4,7,5,2,6,1,3}
    int[] array = new int[max];
    static int count = 0;
    static int judgeCount = 0;

    public static void main(String[] args) {
        // 测试 8皇后是否正确
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.printf("一共有%d种解法", count);
        System.out.printf("一共判断冲突的次数有%d次", judgeCount);// 1.5w
    }

    /**
     * 放置第 n 个皇后
     * 注意：check 是每一次递归时，进入到chenck中都有 for(int i = 0; i < max; i++),因此会有回溯
     * @param n
     */
    private void check(int n) {
        if (n == max) {  // 8个皇后已经放好
            print();
            return;
        }

        // 依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            // 先把当前这个皇后 n，放到该行的第1列
            array[n] = i;
            // 判断当放置第n个皇后到i列时，是否冲突
            if (judge(n)) {
                // 接着放 n+1 个皇后，即开始递归
                check(n+1);
            }
            // 如果冲突，就继续执行 array[n] = i;即将第n个皇后，放置本行的后移一个位置
        }
    }

    /**
     * 查看当我们放置第n个皇后，就去检测该皇后是否和前面已经摆放的皇后冲突
     * @param n  表示第n个皇后
     * @return
     */
    private boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            /**
             * 说明：
             *   1. array[i] == array[n] 表示 判断第n个皇后是否和前面的n-1个皇后在同一列
             *   2. Math.abs(n-i) == Math.abs(array[n] - array[i]) 表示 判断第n个皇后是否和第i皇后在同一斜线
             *   3. 判断是否在同一行：没有必要，n每次都在递增
             */
            if (array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 输出皇后的摆放位置
     */
    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
