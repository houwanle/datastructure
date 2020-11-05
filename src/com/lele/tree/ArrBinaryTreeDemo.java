package com.lele.tree;

/**
 * author: hwl
 * date: 2020/11/5 7:27
 * version: 1.0.0
 * modified by:
 * description:
 */
public class ArrBinaryTreeDemo {

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        // 创建一个 ArrBinaryTree
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);

        arrBinaryTree.preOrder();// 1,2,4,5,3,6,7  前序遍历
//        arrBinaryTree.infixOrder();  // 4,2,5,1,6,3,7  中序遍历
//        arrBinaryTree.postOrder();  // 4,5,2,6,7,3,1   后序遍历
    }

}

// 编写一个ArrayBinaryTree，实现顺序存储二叉树遍历
class ArrBinaryTree {
    private int[] arr; // 存储数据节点的数组

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    // 重载preOrder
    public void preOrder() {
        this.preOrder(0);
    }
    // 重载infixOrder
    public void infixOrder() {
        this.infixOrder(0);
    }
    // 重载postOrder
    public void postOrder() {
        this.postOrder(0);
    }

    /**
     * 顺序存储的前序遍历
     * @param index 数组下标
     */
    public void preOrder(int index) {
        // 如果数组为空，或者 arr.length = 0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        // 输出当前这个元素
        System.out.println(arr[index]);
        // 向左递归遍历
        if ((index * 2 + 1) < arr.length) {
            preOrder(index * 2 + 1);
        }
        // 向右递归遍历
        if ((index * 2 + 2) < arr.length) {
            preOrder(index * 2 + 2);
        }
    }

    /**
     * 顺序存储的中序遍历
     * @param index 数组下标
     */
    public void infixOrder(int index) {
        // 如果数组为空，或者 arr.length = 0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        // 向左递归遍历
        if ((index * 2 + 1) < arr.length) {
            infixOrder(index * 2 + 1);
        }
        // 输出当前这个元素
        System.out.println(arr[index]);

        // 向右递归遍历
        if ((index * 2 + 2) < arr.length) {
            infixOrder(index * 2 + 2);
        }
    }

    /**
     * 顺序存储的后序遍历
     * @param index
     */
    public void postOrder(int index) {
        // 如果数组为空，或者 arr.length = 0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        // 向左递归遍历
        if ((index * 2 + 1) < arr.length) {
            postOrder(index * 2 + 1);
        }
        // 向右递归遍历
        if ((index * 2 + 2) < arr.length) {
            postOrder(index * 2 + 2);
        }
        // 输出当前这个元素
        System.out.println(arr[index]);
    }
}
