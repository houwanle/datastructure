package com.lele.linkedlist;

/**
 * author: hwl
 * date: 2020/9/19 16:10
 * version: 1.0.0
 * modified by:
 * description:
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        // 创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        // 创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        // 不考虑顺序
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);
//        singleLinkedList.list();

        // 按照编号顺序插入
//        singleLinkedList.addByOrder(hero1);
//        singleLinkedList.addByOrder(hero4);
//        singleLinkedList.addByOrder(hero2);
//        singleLinkedList.addByOrder(hero3);
//        singleLinkedList.list();

//        // 测试修改节点
//        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~");
//        singleLinkedList.update(newHeroNode);
//        System.out.println("修改后的链表情况~~");
//        singleLinkedList.list();

//        // 测试查找节点
//        System.out.println("测试查找");
//        singleLinkedList.query(1);
//        System.out.println(singleLinkedList.query(1));


        // 测试删除节点
//        singleLinkedList.del(1);
//        singleLinkedList.del(4);
//        singleLinkedList.del(2);
//        singleLinkedList.del(3);
//        System.out.println("删除后的链表情况~~");
//        singleLinkedList.list();

//        // 测试 获取单链表有效节点个数
//        System.out.printf("单链表中有效节点的个数为：%d", getLength(singleLinkedList.getHead()));

        // 测试 单链表的反转
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);

        System.out.println("原来链表的情况~~");
        singleLinkedList.list();

        System.out.println("反转链表后~~");
        reverseList(singleLinkedList.getHead());
        singleLinkedList.list();

    }

    /**
     * 单链表反转
     * @param head 单链表头指针
     */
    public static void reverseList(HeroNode head) {
        // 如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }

        // 定义一个辅助的指针（变量），帮助我们遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null; // 指向当前节点[cur]的下一个节点
        HeroNode reverseHead = new HeroNode(0, "","");
        // 遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead的最前端
        while(cur != null) {
            next = cur.next; // 先暂时保存当前节点的下一个节点，因为后面需要使用
            cur.next = reverseHead.next; // 将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur; // 将cur连接到新的链表上
            cur = next; // 让 cur 后移
        }
        // 将 head.next 指向 reverseHead.next，实现单链表的反转
        head.next = reverseHead.next;
    }

    /**
     * 查找单链表中的倒数第k个节点
     * 思路：
     * 1.编写一个方法，接收head节点，同时接收一个index
     * 2.index表示是倒数第index个节点
     * 3.先把链表从头到尾遍历，得到链表的总长度getLength
     * 4.得到size后，我们从链表的第一个开始遍历（size-index）个，就可以得到
     * 5.如果找到了，则返回该节点，否则返回null
     * @param head
     * @param index
     * @return
     */
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        // 如果链表为空，则返回null
        if (head.next == null) {
            return null;
        }
        // 第一个遍历 获取链表的长度
        int size = getLength(head);
        // 第二个遍历 size-index,即：倒数的第K个节点
        // 先做一个index 校验
        if (index <= 0 || index > size) {
            return null;
        }
        // 定义给辅助变量，for循环定位到倒数的index
        HeroNode cur = head.next;
        for (int i = 0; i < size-index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    /**
     * 方法：获取单链表节点个数（如果带头节点，需求不统计头节点）
     * @param head 链表的头结点
     * @return 链表的有效节点个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {  // 空链表
            return 0;
        }
        int length = 0; // 表示链表的长度
        // 定义一个辅助变量，这里没有统计头节点
        HeroNode cur = head.next;
        while(cur != null) {
            length++;
            cur = cur.next; // 遍历
        }
        return length;
    }
}

// 定义 SingleLinkedList
class SingleLinkedList {
    // 先初始化一个头节点，头节点不要动，不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    /**
     *
     * @return 返回头节点
     */
    public HeroNode getHead() {
        return head;
    }

    // 添加节点到单链表中
    // 思路，当不考虑编号顺序时
    // 1. 找到当前链表的最后节点
    // 2. 将最后这个节点的next指向 新的节点
    public void add(HeroNode heroNode) {
        // 因为head节点不能动，因此我们需要一个辅助变量 temp
        HeroNode temp = head;
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
        // 将最后这个节点的next指向 新的节点
        temp.next = heroNode;
    }

    // 第二种方式在添加英雄时，根据排名蒋英雄插入到指定位置
    // 如果已经存在该排名，则添加失败，并给出提示
    public void addByOrder(HeroNode heroNode) {
        // 因为头节点不能动，因此我们仍然通过一个辅助指针（变量）来帮助找到添加的位置
        // 因为单链表，由于我们找的temp是位于 添加位置的前一个节点，否则不能插入
        HeroNode temp = head;
        boolean flag = false; // flag 标志添加的编号是否存在，默认为false
        while(true) {
            if (temp.next == null) { // temp已经在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no) { // 位置找到，就在temp的后面插入
                break;
            } else if (temp.next.no == heroNode.no) { // 说明将要添加的heroNode的编号已经存在
                flag = true; // 标识编号已经存在
                break;
            }
            temp = temp.next; // 后移，遍历当前链表
        }
        // 判断flag 的值
        if (flag) { // 编号已经存在，不能添加
            System.out.printf("准备插入的英雄的编号 %d 已经存在了，不能插入\n", heroNode.no);
        } else {
            // 插入到链表中，temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    // 查找链表中是否存在某个节点,根据编号查找
    public HeroNode query(int no) {
        // 判断是否为空
        if (head.next == null) {
            System.out.println("链表为空~");
            return null;
        }
        HeroNode temp = head.next; // 定义一个辅助变量
        while(true) {
            if (temp == null) {
                System.out.println("链表没有该节点~~");
                return null;// 已经遍历完链表
            }
            if (temp.no == no) {
                return temp;
            }
            temp = temp.next;
        }
    }

    // 修改节点的信息，根据no编号来修改，即no编号不能改
    // 1. 根据 newHeroNode 的no来修改即可
    public void update(HeroNode newHeroNode) {
        // 判断是否为空
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        // 找到需要修改的节点，根据no编号
        // 定义一个辅助变量
        HeroNode temp = head.next;
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

    // 删除节点
    // 思路
    // 1.head不能动,因为我们需要一个temp辅助节点找到待删除节点的前一个节点
    // 2.说明我们在比较时，是 temp.next.no 和 需要删除的节点的no比较
    public void del (int no) {
        HeroNode temp = head;
        boolean flag = false; //标志是否找到待删除节点的
        while(true) {
            if (temp.next == null) {  // 已经到链表的最后
                break;
            }
            if (temp.next.no == no) {  // 找到待删除节点的前一个节点
                flag = true;
                break;
            }
            temp = temp.next; // temp后移，遍历
        }
        // 判断flag
        if (flag) { // 找到
            // 可以删除
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的 %d 节点不存在\n", no);
        }
    }

    // 显示链表（遍历）
    public void list(){
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为头节点不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
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



// 定义HeroNode，每个HeroNode对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;  // 指向下一个节点

    // 构造方法
    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    // 重写toString
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
