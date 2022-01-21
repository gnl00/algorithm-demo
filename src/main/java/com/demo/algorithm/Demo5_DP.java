package com.demo.algorithm;

/**
 * 所有路径问题
 * 一个机器人位于一个 m x n 网格的左上角
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角
 * 问总共有多少条不同的路径？
 *
 * @author lgn
 * @since 2022/1/10 13:43
 */

public class Demo5_DP {
    public static void main(String[] args) {
        System.out.println(path(3, 7));
    }

    // O(n^2)
    public static int path(int m, int n) {

        // 1、定义dp数组及下标含义
        // dp[i][j] 表示走到ij位置的路径
        int[][] dp = new int[m][n];

        /**
         *
         *   0 1 2 3 4 5 6
         * 0 $ 0 0 0 0 0 0
         * 1 0 0 0 0 0 0 0
         * 2 0 0 0 0 0 0 *
         */

        // 2、确定推导公式
        // 如 dp[2][3] = ( dp[2][2] ) + ( dp[1][3] )
        // 即 dp[i][j] = dp[i][j-1] + dp[i-1][j]

        // 3、初始化dp数组
        /**
         *   0 1 2 3 4 5 6
         * 0 1 1 1 1 1 1 1
         * 1 1 2 0 0 0 0 0
         * 2 1 0 0 0 0 0 *
         */
        // 初始化第一行和第一列
        int row = 0;
        int col = 0; // 在经过行初始化之后，其实列的初始值可以从1开始

        while (row < m) {
            dp[row][0] = 1;
            row++;
        }

        while (col < n) {
            dp[0][col] = 1;
            col++;
        }

        // 4、确定遍历顺序
        // 从上到下，从左到右

        // 5、推导dp数组
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        return dp[m-1][n-1];
    }
}
