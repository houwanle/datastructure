package com.lele.sparsearray;

/**
 * author: hwl
 * date: 2020/9/15 6:47
 * version: 1.0.0
 * modified by:
 * description: 稀疏数组与二维数组的相互转换
 */
public class SparseArray {

    public static void main(String[] args) {
//        int[][] chessArr1 = init();
//        int[][] sparseArr = toSparse(chessArr1);
//        sparseToArray(sparseArr);
        sparseToArray(toSparse(init()));
    }

    /**
     * 创建一个原始的二维数组  11 * 11
     * 1 表示黑色棋子，2 表示蓝色棋子
     * @return
     */
    private static int[][] init() {
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;

        // 输出原始二维数组
        System.out.println("原始二维数组为：");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        return chessArr1;
    }

    /**
     * 将二维数组 转为 稀疏数组
     * @param chessArr1
     * @return
     */
    private static int[][] toSparse(int[][] chessArr1) {
        // 先遍历二维数组，取出二维数组中非0数据的个数
        int sum = 0;  // 记录原始二维数组中非0数据的个数
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        // 创建对应的稀疏数组
        int[][] sparseArr = new int[sum+1][3];
        // 给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        // 遍历二维数组，将二维数组中有效数据存入到稀疏数组中
        int count = 0; // 记录第几个是非0数据
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        System.out.println();

        // 输出稀疏数组
        System.out.println("得到的稀疏数组为：");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }
        System.out.println();
        return sparseArr;
    }

    /**
     * 将稀疏数组 恢复成 二维数组
     * @param sparseArr
     */
    private static void sparseToArray(int[][] sparseArr) {
        // 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组。
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        System.out.println();

        // 输出恢复后的二维数组
        // 再读取稀疏数组的后几行数据，赋值给原始的二维数组。
        System.out.println("恢复后的二维数组为：");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
