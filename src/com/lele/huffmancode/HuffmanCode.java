package com.lele.huffmancode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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



