package com.lele.hashtab;

/**
 * author: hwl
 * date: 2020/10/26 21:33
 * version: 1.0.0
 * modified by:
 * description:
 */
public class HashTabDemo {
    public static void main(String[] args) {

    }
}

// 表示一个雇员
class Emp {
    public int id;
    public String name;
    public Emp next;// next默认为 null

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}

// 创建EmpLinkedList表示链表
class EmpLinkedList {
    // 头指针，执行第一个Emp，因此我们这个链表的head是直接指向第一个Emp
    private Emp head;// 默认 null

    /**
     * 添加雇员到链表
     * 说明
     * 1.假定，当添加雇员时，id是自增长即id的分配总是从小到大
     * 因此我们将该雇员直接加入到本链表的最后即可
     * @param emp
     */
    public void add(Emp emp) {
        // 如果是添加第一个雇员
        if (head == null) {
            head = emp;
            return;
        }
        // 如果不是第一个雇员，则使用一个辅助的指针，帮助定位到最后
        Emp curEmp = head;
        while(true) {
            if (curEmp.next == null) { // 已经到链表的最后
                break;
            }
            curEmp = curEmp.next;// 后移
        }
        // 退出时直接将emp加入链表
        curEmp.next = emp;
    }

    /**
     * 遍历链表的雇员信息
     * @param no
     */
    public void list(int no) {
        if (head == null) { // 链表为空
            System.out.println("第 " +(no+1)+ " 链表为空");
            return;
        }
        System.out.println("第 "+(no+1)+" 链表的信息为");
        Emp curEmp = head; // 辅助指针
        while(true) {
            System.out.printf(" =>id=%d name=%s\t", curEmp.id, curEmp.name);
            if (curEmp.next == null) {  // curEmp已经是最后结点
                break;
            }
            curEmp = curEmp.next; // 后移，遍历
        }
        System.out.println();
    }
}
