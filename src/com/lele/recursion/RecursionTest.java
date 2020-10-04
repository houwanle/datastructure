package com.lele.recursion;

/**
 * author: hwl
 * date: 2020/10/4 9:15
 * version: 1.0.0
 * modified by:
 * description:
 */
public class RecursionTest {

    public static void main(String[] args) {
//        test(4);

        System.out.println("阶乘结果：" + factorial(3));
    }

    /**
     * 打印问题
     * @param n
     */
    public static void test(int n) {
        if (n > 2) {
            test(n - 1);
        }
        System.out.println("n=" + n);
    }

    /**
     * 阶乘问题
     * @param n
     * @return
     */
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1) * n; // 1*2*3
        }
    }
}
