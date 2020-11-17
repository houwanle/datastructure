package com.lele.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

        Node root = createHuffmanTree(arr);
        preOrder(root);
    }

    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("是空树，不能遍历~");
        }
    }

    /**
     * 创建哈夫曼树的方法
     * @param arr 需要创建成哈夫曼树的数组
     * @return  创建好后的哈夫曼树的 root 节点
     */
    public static Node createHuffmanTree(int[] arr) {
        // 第一步为了操作方便
        // 1.遍历 arr 数组
        // 2.将arr的每个元素构成一个Node
        // 3.将Node放入到ArrayList中
        List<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }

        while (nodes.size() > 1) {
            // 排序 从小到大
            Collections.sort(nodes);

            System.out.println("nodes=" + nodes);

            // 取出根节点权值最小的两颗二叉树
            // 取出权值最小的结点（二叉树）
            Node leftNode = nodes.get(0);
            // 取出权值第二小的节点（二叉树）
            Node rightNode = nodes.get(1);
            // 构建一颗新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            // 从ArrayList删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            // 将parent加入到nodes
            nodes.add(parent);
        }

        // 返回哈夫曼树的root结点
        return nodes.get(0);
    }
}

// 创建结点类
// 为了让 Node 对象持续排序Collections集合排序
// 让Node实现Comparable接口
class Node implements  Comparable<Node> {
    int value;//结点权值
    Node left;// 指向左子结点
    Node right;//指向右子结点

    public Node(int value) {
        this.value = value;
    }

    // 写一个前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

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
