package com.lele.algorithm.kmp;

/**
 * author: hwl
 * date: 2020/12/8 7:17
 * version: 1.0.0
 * modified by:
 * description: 暴力匹配
 */
public class ViolenceMatch {

    public static void main(String[] args) {
        String str1 = "I like Java very much!";
        String str2 = "Java";
        System.out.println("index=" + violenceMatch(str1, str2));
    }

    /**
     * 暴力匹配算法实现
     * @param str1
     * @param str2
     * @return
     */
    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1Len = s1.length;
        int s2Len = s2.length;

        int i = 0;// i索引指向s1
        int j = 0;// j索引指向s2
        while (i < s1Len && j < s2Len) {//保证匹配时，不越界
            if (s1[i] == s2[j]) {// 匹配到
                i++;
                j++;
            } else {// 没有匹配成功
                // 如果没有匹配
                i = i - (j - 1);
                j = 0;
            }
        }

        //判断是否匹配成功
        if (j == s2Len) {
            return i - j;
        } else {
            return -1;
        }
    }
}
