package com.lele.huffmancode;

import java.io.*;
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
//        String content = "i like like like java do you like a java";
//        byte[] contentBytes = content.getBytes();
//        System.out.println(contentBytes.length);// 40
//
//        byte[] huffmanCodesBytes = huffmanZip(contentBytes);
//        System.out.println("压缩后的结果为：" + Arrays.toString(huffmanCodesBytes));
//
//        byte[] sourceBytes = decode(huffmanCodes, huffmanCodesBytes);
//        System.out.println("原来的字符串:" + new String(sourceBytes));//i like like like java do you like a java

//        // 测试压缩文件
//        String srcFile = "E:\\IdeaProjects\\project\\DataStructure\\data\\test.bmp";
//        String dstFile = "E:\\IdeaProjects\\project\\DataStructure\\data\\test.zip";
//
//        zipFile(srcFile,dstFile);
//        System.out.println("压缩文件成功");

        // 测试解压文件
        String zipFile = "E:\\IdeaProjects\\project\\DataStructure\\data\\test.zip";
        String dstFile = "E:\\IdeaProjects\\project\\DataStructure\\data\\test1.bmp";
        unZipFile(zipFile, dstFile);
        System.out.println("解压成功~~");

//        List<Node> nodes = getNodes(contentBytes);
//        System.out.println("nodes=" + nodes);
//
//        // 测试，创建二叉树
//        System.out.println("哈夫曼树");
//        Node huffmanTreeRoot = createHuffmanTree(nodes);
//        System.out.println("前序遍历");
//        huffmanTreeRoot.preOrder();
//
//        // 测试，生成对应的哈夫曼编码
//        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
//        System.out.println("生成的哈夫曼编码表" + huffmanCodes);
//
//        byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);
//        System.out.println("huffmanCodeBytes=" + Arrays.toString(huffmanCodeBytes));
    }

    /**
     * 将文件解压
     * @param zipFile
     * @param dstFile
     */
    public static void unZipFile(String zipFile, String dstFile) {
        // 定义输入流
        InputStream is = null;
        // 定义一个对象输入流
        ObjectInputStream ois = null;
        // 定义文件的输出流
        OutputStream os = null;

        try {
            // 创建文件输入流
            is = new FileInputStream(zipFile);
            // 创建一个和 is 关联的对象输入流
            ois = new ObjectInputStream(is);
            // 读取byte数组 huffmanBytes
            byte[] huffmanBytes = (byte[])ois.readObject();
            // 读取哈夫曼编码表
            Map<Byte,String> huffmanCodes = (Map<Byte, String>)ois.readObject();

            // 解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            //将bytes 数组写入到目标文件
            os = new FileOutputStream(dstFile);
            // 写数据到dstFile文件
            os.write(bytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * 将文件进行压缩
     * @param srcFile 需要压缩的文件的全路径
     * @param dstFile 压缩文件的存放目录
     */
    public static void zipFile(String srcFile, String dstFile) {

        // 创建输出流
        OutputStream os = null;
        ObjectOutputStream oos = null;
        // 创建文件输入流
        FileInputStream is = null;
        try {
            // 创建文件输入流
            is = new FileInputStream(srcFile);
            // 创建一个和源文件大小一样的byte[]
            byte[] b = new byte[is.available()];
            // 读取文件
            is.read(b);
            // 直接对源文件压缩
            byte[] huffmanBytes = huffmanZip(b);
            // 创建文件的输出流，存放压缩文件
            os = new FileOutputStream(dstFile);
            // 创建一个和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            // 将哈夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);//

            // 以对象流的方式写入 哈夫曼编码，是为了以后恢复源文件时使用
            // 注意一定要把哈夫曼编码写入压缩文件
            oos.writeObject(huffmanCodes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                is.close();
                oos.close();
                os.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 完成数据的解压
     * 1. 将huffmanCodeBytes [-88,-65,-56,-65,-65,-55 ...],先转成 哈夫曼编码对应的二进制的字符串 "1010100010111..."
     * 2. 哈夫曼编码对应的二进制的字符串 "1010100010111..." => 对照 哈夫曼编码 => "i like like like java do you like a java"
     */

    /**
     * 完成对 压缩数据的解码
     * @param huffmanCodes 哈夫曼编码表 map
     * @param huffmanBytes 哈夫曼编码得到的字节数组
     * @return 就是原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        // 1.先得到 huffmanBytes 对应的二进制的字符串
        StringBuilder stringBuilder = new StringBuilder();
        // 2. 将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            // 判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }

        // 把字符串按照指定的哈夫曼编码进行解码
        // 把哈夫曼编码表进行调换
        Map<String, Byte> map = new HashMap<String, Byte>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length();) {
            int count = 1;
            boolean flag = true;
            Byte b = null;

            while(flag) {
                // 递增的取出key
                String key = stringBuilder.substring(i, i + count);// i不动，让count移动，直到匹配到一个字符
                b = map.get(key);
                if (b == null) {  //没有匹配到
                    count++;
                } else {
                    // 匹配到
                    flag = false;
                }
            }
            list.add(b);
            i += count;// i直接移动到count位置
        }
        // 当for循环结束后，list中存放了所有的字符 "i like like like java do you like a java"
        // 把list中的数据放入到byte[] 并返回
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    /**
     * 将一个byte 转成一个二进制的字符串
     * @param flag  标志是否需要补高位，如果是true，需要补高位，false表示不补
     * @param b 传入的byte
     * @return b 对应的二进制字符串（注意是按补码返回）
     */
    private static String byteToBitString(boolean flag, byte b) {
        // 使用变量保存 b
        int temp = b; // 将b转成int
        // 如果是正数，还存在补高位
        if (flag) {
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);// 返回的是temp对应的二进制的补码
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }


    /**
     * 使用一个方法，将前面的方法封装，便于调用
     * @param bytes 原始字符串对应的字节数组
     * @return 是经过 哈夫曼编码处理后的字节数组（压缩后的数组）
     */
    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        // 根据 nodes 创建的哈夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        // 生成对应的哈夫曼编码（根据哈夫曼树）
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        // 根据生成的哈夫曼编码压缩，得到压缩后的哈夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;

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



