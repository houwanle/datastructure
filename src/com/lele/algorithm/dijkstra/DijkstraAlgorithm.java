package com.lele.algorithm.dijkstra;

import java.util.Arrays;

/**
 * author: hwl
 * date: 2020/12/14 21:52
 * version: 1.0.0
 * modified by:
 * description: 迪杰斯特拉算法
 */
public class DijkstraAlgorithm {

    public static void main(String[] args) {
        char[] vertex = {'A','B','C','D','E','F','G'};
        // 邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;// 表示不可以连接
        matrix[0] = new int[]{N,5,7,N,N,N,2};
        matrix[1] = new int[]{5,N,N,9,N,N,3};
        matrix[2] = new int[]{7,N,N,N,8,N,N};
        matrix[3] = new int[]{N,9,N,N,N,4,N};
        matrix[4] = new int[]{N,N,8,N,N,5,4};
        matrix[5] = new int[]{N,N,N,4,5,N,6};
        matrix[6] = new int[]{2,3,N,N,4,6,N};

        // 创建 Graph 对象
        Graph graph = new Graph(vertex, matrix);
        // 测试
        graph.showGraph();
    }

}

class Graph {
    private char[] vertex;//顶点数组
    private int[][] matrix;// 邻接矩阵

    // 构造器
    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    /**
     * 显示图
     */
    public void showGraph() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }
}