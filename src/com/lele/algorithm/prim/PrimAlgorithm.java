package com.lele.algorithm.prim;

import java.util.Arrays;

/**
 * author: hwl
 * date: 2020/12/13 8:36
 * version: 1.0.0
 * modified by:
 * description: 普里姆算法
 */
public class PrimAlgorithm {

    public static void main(String[] args) {

        // 测试图是否创建成功
        char[] data = new char[]{'A','B','C','D','E','F','G'};
        int verxs = data.length;
        // 邻接矩阵的关系使用二维数组表示,10000表示两个点不连通
        int[][] weight = new int[][]{
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000},
        };

        // 创建MGraph对象
        MGraph mGraph = new MGraph(verxs);
        // 创建一个MinTree对象
        MinTree minTree = new MinTree();
        minTree.createGraph(mGraph, verxs, data, weight);
        // 输出
        minTree.showGraph(mGraph);

        // 测试普里姆算法
        minTree.prim(mGraph, 0);
    }
}

// 创建最小生成树 -> 村庄的图
class MinTree {

    /**
     * 创建图的邻接矩阵
     * @param graph 图对象
     * @param verxs 图的顶点个数
     * @param data 图的各个顶点的值
     * @param weight 图的邻接矩阵
     */
    public void createGraph(MGraph graph, int verxs, char data[], int[][] weight) {
        int i, j;
        for (i = 0; i < verxs; i++) { // 顶点
            graph.data[i] = data[i];
            for (j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    /**
     * 显示图的邻接矩阵
     * @param graph
     */
    public void showGraph(MGraph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 编写prim算法，得到最小生成树
     * @param mGraph 图
     * @param v 表示从图的第几个顶点开始生成 'A' -> 0  'B' -> 1
     */
    public void prim(MGraph mGraph, int v) {
        // visited[] 标记结点（顶点）是否被访问过
        int[] visited = new int[mGraph.verxs];
//        // visited[]默认元素的值都是0，表示没有访问过
//        for (int i = 0; i < mGraph.verxs; i++) {
//            visited[i] = 0;
//        }

        // 把当前这个结点标记为已访问
        visited[v] = 1;
        // h1和h2记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        // 将minWeight初始成一个大数，后面在遍历过程中，会被替换
        int minWeight = 10000;
        // 因为有mGraph.verxs顶点，普里姆算法结束后，有mGraph.verxs-1 条边
        for (int k = 1; k < mGraph.verxs; k++) {

            // 确定每一次生成的子图，和哪个结点的距离最近
            for (int i = 0; i < mGraph.verxs; i++) {// i结点表示被访问过的结点
                for (int j = 0; j < mGraph.verxs; j++) { // j结点表示还没有被访问过的结点
                    if (visited[i] == 1 && visited[j] == 0 && mGraph.weight[i][j] < minWeight) {
                        // 替换minWeight(寻找已经访问过的结点和未访问过的结点间的权值最小的边)
                        minWeight = mGraph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            // 找到一条边
            System.out.println("边<" + mGraph.data[h1] + "," + mGraph.data[h2] + "> 权值:" + minWeight);
            // 当前这个结点标记为已经访问
            visited[h2] = 1;
            // minWeight 重新设置为最大值10000
            minWeight = 10000;
        }
    }
}

class MGraph {
    int verxs;//图的节点个数
    char[] data;// 存放结点数据
    int[][] weight;// 存放边，即邻接矩阵

    public MGraph(int verxs) {
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }
}
