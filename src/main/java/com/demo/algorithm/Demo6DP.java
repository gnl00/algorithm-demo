package com.demo.algorithm;

/**
 * 最小路径和 => 所有路径问题变种
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 *
 * 示例: 输入:[ [1,3,1], [1,5,1], [4,2,1] ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小
 *
 *   0 1 2
 * 0 1 3 1
 * 1 1 5 1
 * 2 4 2 1
 *
 * @author lgn
 * @since 2022/1/10 14:06
 */

public class Demo6DP {
    public static void main(String[] args) {
        int[][] arr = {
                {1,3,1},
                {1,5,1},
                {4,2,1}
        };
        System.out.println(minSum(arr));
    }

    public static int minSum(int[][] arr) {

        // 1、确定dp数组及下标含义
        // dp[i][j] 表示走到ij位置，路径上的数字和
        int[][] dp = new int[arr.length][arr.length];

        // 2、确定dp数组推导公式
        // dp[i][j] = dp[i][j-1] + dp[i-1][j]

        // 3、初始化数组
        dp[0][0] = arr[0][0];
        // 初始化第一行和第一列
        int row = 1;
        int col = 1;

        while (row < arr.length) {
            dp[row][0] = arr[row][0] + dp[row - 1][0];
            row++;
        }

        while (col < arr[0].length) {
            dp[0][col] = arr[0][col] + dp[0][col-1];
            col++;
        }

        // 4、确定遍历顺序
        // 从上到下，从左到右

        // 5、推导dp数组
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j < arr[i].length; j++) {
                dp[i][j] = Math.min(dp[i][j-1] + arr[i][j], dp[i-1][j] + arr[i][j]);
            }
        }


        return dp[arr.length -1][arr[0].length - 1];
    }
}
