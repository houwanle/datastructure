package com.lele.hashtab;

import java.sql.SQLOutput;
import java.util.Scanner;

/**
 * author: hwl
 * date: 2020/10/26 21:33
 * version: 1.0.0
 * modified by:
 * description:
 */
public class HashTabDemo {
    public static void main(String[] args) {

        // 创建哈希表
        HashTab hashTab = new HashTab(7);

        // 写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("del: 删除雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch(key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    // 创建 雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的 id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "del":
                    System.out.println("请输入要删除的 id");
                    id = scanner.nextInt();
                    hashTab.deleteEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

// 创建 HashTab 管理多条链表
class HashTab {
    private EmpLinkedList[] empLinkedListArray;
    private int size;// 表示有多少条链表

    // 构造器
    public HashTab(int size) {
        this.size = size;
        // 初始化 empLinkedListArray
        empLinkedListArray = new EmpLinkedList[size];
        // 这时不要初始化每个链表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    /**
     * 添加雇员
     * @param emp
     */
    public void add(Emp emp) {
        // 根据员工的 id，得到该员工应当添加到哪条链表
        int empLinkedListNO = hashFun(emp.id);
        // 将 emp 添加到对应的链表中
        empLinkedListArray[empLinkedListNO].add(emp);
    }

    /**
     * 遍历所有的链表，遍历hashtab
     */
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    /**
     * 根据输入的id，查找雇员
     * @param id
     */
    public void findEmpById(int id) {
        // 使用散列函数确定到哪条链表查找
        int empLinkedListNO = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNO].findEmpById(id);
        if (emp != null) {
            System.out.printf("在第%d条链表中找到 雇员 id=%d\n", (empLinkedListNO + 1), id);
        } else {
            System.out.println("在哈希表中，没有找到该雇员~");
        }
    }

    /**
     * 根据id，删除雇员
     * @param id
     */
    public void deleteEmpById(int id) {
        int empLinkedListNO = hashFun(id);
        empLinkedListArray[empLinkedListNO].deleteEmpById(id);
    }

    /**
     * 编写散列函数，使用一个简单取模法
     * @param id
     * @return
     */
    public int hashFun(int id) {
        return id % size;
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
        System.out.print("第 "+(no+1)+" 链表的信息为");
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

    /**
     * 根据id查找雇员
     * 如果查找到，就返回Emp，如果没有找到，就返回null
     * @param id
     * @return
     */
    public Emp findEmpById(int id) {
        // 判断链表是否为空
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        // 辅助指针
        Emp curEmp = head;
        while(true) {
            if (curEmp.id == id) { // 找到
                break;// 这时 curEmp 就指向要查找的雇员
            }
            // 退出
            if (curEmp.next == null) { // 说明遍历当前链表没有找到该雇员
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }

    /**
     * 根据id删除雇员
     * @param id
     */
    public void deleteEmpById(int id) {
        // 判断链表是否为空
        if (head == null) {
            System.out.println("链表为空");
            return;
        }
        Emp curEmp = head;
        boolean flag = false;
        while(true) {
            if (head.id == id) {
                flag = true;
                break;
            }
            if (curEmp.next == null) {
                break;
            }
            if (curEmp.next.id == id) {
                flag = true;
                break;
            }
            curEmp = curEmp.next;
        }
        if (flag) {
            if (curEmp == head) {
                head = null;
            } else {
                curEmp.next = curEmp.next.next;
            }
            System.out.printf("id为%d的雇员成功删除\n", id);
        } else {
            System.out.printf("id为%d雇员不存在,删除失败\n", id);
        }
    }
}
