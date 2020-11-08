package com.lele.tree.threadedbinarytree;

/**
 * author: hwl
 * date: 2020/11/8 10:39
 * version: 1.0.0
 * modified by:
 * description:
 */
public class ThreadedBinaryTreeDemo {

    public static void main(String[] args) {
        // 测试中序线索二叉树的功能
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");

        // 手动创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        // 测试中序线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        // 测试：以10号节点测试
        HeroNode leftNode = node5.getLeft();
        HeroNode rightNode = node5.getRight();
        System.out.println("10号结点的前驱结点是：" + leftNode);// 3
        System.out.println("10号结点的后继结点是：" + rightNode);// 1

        // 当线索化二叉树后，能在使用原来的遍历方法
//        threadedBinaryTree.infixOrder();
        System.out.println("使用线索化的方式遍历 线索化二叉树");
        threadedBinaryTree.threadedList();// 8,3,10,1,14,6
    }

}

// 定义 ThreadedBinaryTree 实现了线索化功能的二叉树
class ThreadedBinaryTree {
    private HeroNode root;

    // 为了实现线索化，需要创建要给指向当前结点的前驱结点的指针
    // 在递归进行线索化时，pre总是保留前一个节点
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    // 重载 threadedNodes 方法
    public void threadedNodes(){
        this.threadedNodes(root);
    }

    /**
     * 遍历线索化二叉树
     */
    public void threadedList() {
        // 定义一个变量，存储当前遍历的结点，从root开始
        HeroNode node = root;
        while(node != null) {
            // 循环的找到 leftType == 1的结点，第一个找到就是8结点
            // 处理后的有效结点
            while(node.getLeftType() == 0) {
                node = node.getLeft();
            }
            // 打印当前这个节点
            System.out.println(node);
            // 如果当前节点的右指针指向的是后继结点，就一直输出
            while(node.getRightType() == 1) {
                // 获取到当前节点的后继结点
                node = node.getRight();
                System.out.println(node);
            }
            // 替换这个遍历的节点
            node = node.getRight();
        }
    }

    /**
     * 二叉树中序线索化
     * @param node
     */
    public void threadedNodes(HeroNode node) {
        // 如果node==null,不能线索化
        if (node == null) {
            return;
        }

        // 先线索化左子树
        threadedNodes(node.getLeft());
        // 线索化当前结点

        // 处理当前节点的前驱结点
        // 以8结点来理解
        // 8节点的.left = null，8节点的.leftType = 1
        if (node.getLeft() == null) {
            // 让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            // 修改当前结点的左指针的类型，指向前驱结点
            node.setLeftType(1);
        }

        // 处理后继结点
        if (pre != null && pre.getRight() == null) {
            // 让前驱节点的右指针指向当前结点
            pre.setRight(node);
            // 修改前驱结点的右指针类型
            pre.setRightType(1);
        }
        // 每处理一个节点后，让当前节点是下一个节点的前驱结点
        pre = node;

        // 线索化右子树
        threadedNodes(node.getRight());
    }

    /**
     * 删除结点
     * @param no
     */
    public void delNode(int no) {
        if (root != null) {
            // 如果只有一个root结点，这里立即判断root是不是就是要删除节点
            if (root.getNo() == no) {
                root = null;
            } else {
                // 递归删除
                root.delNode(no);
            }
        } else {
            System.out.println("空树，不能删除~");
        }
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    /**
     * 前序遍历查找
     * @param no
     * @return
     */
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    /**
     * 中序遍历查找
     * @param no
     * @return
     */
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    /**
     * 后序遍历
     * @param no
     * @return
     */
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return this.root.postOrderSearch(no);
        } else {
            return null;
        }
    }

}

class HeroNode {
    private int no;
    private String name;
    private HeroNode left; // 默认 null
    private HeroNode right; // 默认 null
    // 说明
    // 1.如果leftType == 0 表示指向的是左子树，如果1则表示指向前驱结点
    // 2.如果rightType == 0 表示指向的是右子树，如果1则表示指向后继借点
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 递归删除结点
     * 1.如果删除的节点是叶子节点，则删除该节点
     * 2.如果删除的节点是非叶子节点，则删除该子树
     * @param no
     */
    public void delNode(int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.delNode(no);
        }
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        System.out.println(this);// 先输出父结点
        // 递归左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    /**
     * 前序遍历查找
     * @param no
     * @return
     */
    public HeroNode preOrderSearch(int no) {
        System.out.println("进入前序遍历");
        if (this.no == no) {
            return this;
        }
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 中序遍历查找
     * @param no
     * @return
     */
    public HeroNode infixOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入中序查找");
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 后序遍历查找
     * @param no
     * @return
     */
    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入后序查找");
        if (this.no == no) {
            return this;
        }
        return resNode;
    }




}