package com.lele.queue;

import javafx.scene.shape.Circle;

import java.util.Scanner;

/**
 * author: hwl
 * date: 2020/9/17 20:17
 * version: 1.0.0
 * modified by:
 * description: 数组模拟环形队列
 */
public class CircleArrayQueueDemo {

    public static void main(String[] args) {
        System.out.println("测试数组模拟环形队列的案例~~");

        // 创建一个环形队列
        CircleArray queue = new CircleArray(4); // 说明设置4，其队列的有效数据最大是3
        char key = ' '; // 接受用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        // 输出一个菜单
        while(loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");
            key = scanner.next().charAt(0); // 接受一个字符
            switch(key) {
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
                case'h': // 查看队列头的数据
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
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
        System.out.println("程序退出~~");
    }
}

class CircleArray {
    private int maxSize;  // 数组最大容量
    private int front;   // front 变量含义做了调整：front 指向队列第一个元素，即 arr[front] 表示队列的第一个元素，front 的初始值等于0；
    private int rear;  // rear变量含义做了调整：rear 指向队列的最后一个元素的后一个位置，目的是为了空出一个位置留作约定，rear 的初始值等于0；
    private int[] arr; // 数组用来存放数据，模拟队列

    // 创建队列构造器
    public CircleArray(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    // 判断队列是否满了
    public boolean isFull() {
        return (rear+1) % maxSize == front;
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
        arr[rear] = n;  //直接将数据加入队列
        rear = (rear+1) % maxSize;  // 将rear后移，这里必须考虑取模
    }

    // 获取队列的数据，出队
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能取数据。");
        }
        // 先把front对应的值保留到一个临时变量
        // 将 front 后移，考虑取模
        // 将临时保存的变量返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    // 显示队列所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空，不能取数据");
            return;
        }
        for (int i = 0; i < front + size(); i++) {
            System.out.printf("a[%d]=[%d]\n", i % maxSize, arr[i%maxSize]);
        }
    }

    // 求出当前队列有效数据的个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    // 显示队列的头部数据，注意此处不是取出数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，没有数据");
        }
        return arr[front];
    }

}
