package com.lele.linkedlist;

import java.util.Stack;

/**
 * author: hwl
 * date: 2020/9/22 7:16
 * version: 1.0.0
 * modified by:
 * description:
 */
public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack();
        // 入栈
        stack.add("1");
        stack.add("2");
        stack.add("3");

        // 出栈
        while(stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }
}
