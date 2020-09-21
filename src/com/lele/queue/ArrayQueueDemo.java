package com.lele.queue;

import java.util.Scanner;

/**
 * author: hwl
 * date: 2020/9/15 21:31
 * version: 1.0.0
 * modified by:
 * description: 采用数组模拟队列
 */
public class ArrayQueueDemo {

    public static void main(String[] args) {

        // 创建一个队列
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' '; // 接受用户的输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        // 输出一个菜单
        while(loop) {
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列中取出数据");
            System.out.println("s(show):显示队列中的数据");
            System.out.println("h(head):查看队头的数据");
            System.out.println("e(exit):退出程序");
            key = scanner.next().charAt(0); // 接受一个字符
            switch (key) {
                case's':
                    queue.showQueue();
                    break;
                case'a':
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case'g': // 取出数据
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case'h': // 查看队头数据
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队头数据为%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case'e': //退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

    }

}

class ArrayQueue {
    private int maxSize;  // 队列最大长度
    private int front;   // 头部指针
    private int rear;  // 尾部指针
    private int[] arr; // 数组用来存放数据，模拟队列

    // 创建队列构造器
    public ArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1; // 队列头部指针，指向队列头部元素的前一个位置
        rear = -1;  // 队列尾部指针，指向队列尾部的最后一个元素
    }

    // 判断队列是否满了
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return front == rear;
    }

    // 添加数据到队列，入队
    public void addQueue(int n) {
        if(isFull()) {
            System.out.println("队列已满，不能加入数据。");
            return;
        }
        rear++;  // 尾部指针后移
        arr[rear] = n;
    }

    // 获取队列的数据，出队
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能取数据。");
        }
        front++;  // 头部指针后移
        return arr[front];
    }

    // 显示队列所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空，不能取数据");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("a[%d]=[%d]\n", i, arr[i]);
        }
    }

    // 显示队列的头部数据，注意此处不是取出数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，没有数据");
        }
        return arr[front+1];
    }

}
