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
        // 测试
        System.out.println("双向链表的测试");
        //先创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        // 创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.list();

        // 修改
        HeroNode2 heroNode2 = new HeroNode2(4, "公孙胜", "入云龙");
        doubleLinkedList.update(heroNode2);
        System.out.println("修改后的链表情况");
        doubleLinkedList.list();

        // 删除
        doubleLinkedList.del(3);
        System.out.println("删除后的链表情况");
        doubleLinkedList.list();
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
     * 从双向链表中删除一个节点
     * 说明
     * 1. 对于双向链表，我们可以直接找到要删除的这个节点
     * 2. 找到后，自我删除即可
     * @param no
     */
    public void del (int no) {

        // 判断当前链表是否为空
        if (head.next == null) {
            System.out.println("链表为空，无法删除");
        }

        HeroNode2 temp = head.next;  // 辅助变量（指针）
        boolean flag = false; //标志是否找到待删除节点的
        while(true) {
            if (temp == null) {  // 已经到链表的最后
                break;
            }
            if (temp.no == no) {  // 找到待删除节点的前一个节点
                flag = true;
                break;
            }
            temp = temp.next; // temp后移，遍历
        }
        // 判断flag
        if (flag) { // 找到
            // 可以删除
//            temp.next = temp.next.next;  单项链表的删除方式
            temp.pre.next = temp.next;
            // 如果是最后一个节点，就不需要执行下面这句话，否则出现空指针异常
            if(temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("要删除的 %d 节点不存在\n", no);
        }
    }

    /**
     * 修改一个节点的内容，可以看到双向链表的节点内容修改和单链表一样
     * 只是 节点类型改成HeroNode2
     * @param newHeroNode
     */
    public void update(HeroNode2 newHeroNode) {
        // 判断是否为空
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        // 找到需要修改的节点，根据no编号
        // 定义一个辅助变量
        HeroNode2 temp = head.next;
        boolean flag = false; // 表示是否找到该节点
        while(true) {
            if (temp == null) {
                break; // 已经遍历完链表
            }
            if (temp.no == newHeroNode.no) { // 找到需要修改的节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 根据 flag 判断是否找到要修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        } else { // 没有找到
            System.out.printf("没有找到编号为 %d 的节点，无法修改\n", newHeroNode.no);
        }
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
