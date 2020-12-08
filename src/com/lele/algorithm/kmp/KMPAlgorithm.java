package com.lele.algorithm.kmp;

import java.util.Arrays;

/**
 * author: hwl
 * date: 2020/12/8 20:51
 * version: 1.0.0
 * modified by:
 * description: KMP算法
 */
public class KMPAlgorithm {

    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";

        int[] next = kmpNext(str2);
        System.out.println("next=" + Arrays.toString(next));

        int index = kmpSearch(str1, str2, next);
        System.out.println("index=" + index);
    }

    /**
     * kmp搜索算法
     * @param str1  源字符串
     * @param str2  子串
     * @param next  部分匹配表，是子串对应的部分匹配表
     * @return
     */
    public static int kmpSearch(String str1, String str2, int[] next) {
        // 遍历
        for (int i = 0, j = 0; i < str1.length(); i++) {

            // KMP算法核心点
            while(j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j-1];
            }

            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    /**
     * 获取到一个字符串（子串）的部分匹配值 表
     * @param dest
     * @return
     */
    public static int[] kmpNext(String dest) {
        //创建一个next数组保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0;// 如果字符串是长度为1，部分匹配值就是0
        for (int i = 1, j = 0; i < dest.length(); i++) {
            // kmp算法的核心
            while(j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j-1];
            }

            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }








}
