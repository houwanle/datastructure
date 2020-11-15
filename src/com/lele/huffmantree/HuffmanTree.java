package com.lele.huffmantree;

/**
 * author: hwl
 * date: 2020/11/16 7:24
 * version: 1.0.0
 * modified by:
 * description:
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int arr[] = {13,7,8,3,29,6,1};
    }
}

// 创建结点类
// 为了让 Node 对象持续排序Collections集合排序
// 让Node实现Comparable接口
class Node implements  Comparable<Node> {
    int value;//结点权值
    Node left;// 指向左子结点
    Node right;//指向右子结点

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}
