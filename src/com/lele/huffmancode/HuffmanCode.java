package com.lele.huffmancode;

import java.util.*;

/**
 * author: hwl
 * date: 2020/11/20 21:56
 * version: 1.0.0
 * modified by:
 * description:
 */
public class HuffmanCode {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        System.out.println(contentBytes.length);// 40

        List<Node> nodes = getNodes(contentBytes);
        System.out.println("nodes=" + nodes);

        // 测试，创建二叉树
        System.out.println("哈夫曼树");
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        System.out.println("前序遍历");
        huffmanTreeRoot.preOrder();

        // 测试，生成对应的哈夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        System.out.println("生成的哈夫曼编码表" + huffmanCodes);

        byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);
        System.out.println("huffmanCodeBytes=" + Arrays.toString(huffmanCodeBytes));
    }

    /**
     * 将字符串对应的byte[]数组，通过生成的哈夫曼编码表，返回一个哈夫曼编码 压缩后的byte[]
     * @param bytes 这是原始字符串对应的 byte[]
     * @param huffmanCodes 返回哈夫曼处理后的 byte[]
     * @return
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        // 1.利用 huffmanCodes 将 bytes 转成 哈夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        // 遍历bytes数组
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }

        // 统计返回 byte[] huffmanCodeBytes 长度
        // 一行代码 int len = (stringBuilder.length() + 7)/8
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        // 创建 存储压缩后的 byte 数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;// 记录是第几个byte
        for (int i = 0; i < stringBuilder.length(); i += 8) { // 每8位对应一个byte
            String strByte;
            if (i + 8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }

            // 将strByte 转成一个byte，放入到 huffmanCodeBytes
            huffmanCodeBytes[index] = (byte)Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    // 生成哈夫曼树对应的哈夫曼编码
    // 思路：
    // 1. 将哈夫曼编码存放在 Map<Byte, String> 形式
    // 生成的哈夫曼编码表{32=01,97=100,100=11000,117=11001,101=1110,118=11011, 105=101, 121=11010, 106=0010}
    static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
    // 2. 定义一个StringBuilder 存储某个叶子结点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    // 为了调用方便，重载getCodes
    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        // 处理 root 的左子树
        getCodes(root.left, "0", stringBuilder);
        // 处理root 的右子树
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 功能：将传入的Node结点的所有叶子节点的哈夫曼编码得到，并放入到huffmanCodes集合
     * @param node  传入节点
     * @param code  路径：左子节点是 0，右子节点 1
     * @param stringBuilder  用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        // 将code加入到stringBuilder2
        stringBuilder2.append(code);
        if (node != null) {
            // 判断当前 node 是叶子节点还是非叶子节点
            if (node.data == null) { // 非叶子节点
                // 递归处理
                // 向左递归
                getCodes(node.left, "0", stringBuilder2);
                // 向右递归
                getCodes(node.right, "1", stringBuilder2);
            } else {
                // 表示找到某个叶子节点的最后
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    private static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("哈夫曼树为空");
        }
    }

    /**
     *
     * @param bytes  接收字节数组
     * @return 返回的就是List形式
     */
    private static List<Node> getNodes(byte[] bytes) {
        // 1. 创建一个ArrayList
        ArrayList<Node> nodes = new ArrayList<>();

        // 遍历bytes，统计每个byte出现的次数 -> map
        HashMap<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {  //Map还没有这个字符数据，第一次
                counts.put(b, 1);
            } else {
                counts.put(b, count+1);
            }
        }

        // 把每个键值对转成一个Node对象，并加入nodes
        for (Map.Entry<Byte,Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            // 排序，从小到大
            Collections.sort(nodes);
            // 取出第一颗最小的二叉树
            Node leftNode = nodes.get(0);
            // 取出第二颗最小的二叉树
            Node rightNode = nodes.get(1);
            // 创建一颗新的二叉树，它的根节点没有Data，只有权值
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            // 将已经处理的两颗二叉树从nodes删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            // 将新的二叉树，加入到nodes
            nodes.add(parent);
        }
        // nodes最后的节点，就是哈夫曼树的根节点
        return nodes.get(0);
    }
}

// 创建Node，待数据和权值
class Node implements Comparable<Node> {
    Byte data; // 存放数据本身，比如 'a' => 97  ' ' => 32
    int weight;// 权值，表示字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        // 从小到大排序
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    // 前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }




}



