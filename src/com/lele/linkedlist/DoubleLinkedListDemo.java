package com.lele.linkedlist;

/**
 * author: hwl
 * date: 2020/9/23 6:56
 * version: 1.0.0
 * modified by:
 * description:
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {

    }
}

class DoubleLinkedList{
    private HeroNode2 head = new HeroNode2(0, "", "");

    /**
     *
     * @return 返回头节点
     */
    public HeroNode2 getHead() {
        return head;
    }

    /**
     * 添加一个节点到双向链表的最后
     * @param heroNode
     */
    public void add(HeroNode2 heroNode) {
        // 因为head节点不能动，因此我们需要一个辅助变量 temp
        HeroNode2 temp = head;
        // 遍历链表，找到最后
        while(true) {
            // 找到链表的最后
            if(temp.next == null) {
                break;
            }
            // 如果没有找到最后，将temp后移
            temp = temp.next;
        }
        // 当退出while循环时，temp就指向了链表的最后
        // 形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    /**
     * 显示链表（遍历）
     */
    public void list(){
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为头节点不能动，因此我们需要一个辅助变量来遍历
        HeroNode2 temp = head.next;
        while(true) {
            // 判断是否到了链表的尾部
            if (temp == null) {
                break;
            }
            // 输出节点的信息
            System.out.println(temp);
            //将temp后移，一定小心
            temp = temp.next;
        }
    }
}

class HeroNode2 {
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;  // 指向下一个节点
    public HeroNode2 pre; // 指向前一个节点

    // 构造方法
    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    // 重写toString
    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
