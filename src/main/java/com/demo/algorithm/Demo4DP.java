package com.demo.algorithm;

/**
 * 爬梯子问题 ? 斐波那契数列变形
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：输入： 2 输出2
 * 解释： 有两种方法可以爬到楼顶。
 * a、1 阶 + 1 阶
 * b、2 阶
 *
 * 示例 2：输入： 3 输出3
 * 解释： 有三种方法可以爬到楼顶。
 * a、1 阶 + 1 阶 + 1 阶
 * b、1 阶 + 2 阶
 * c、2 阶 + 1 阶
 *
 * 4 输出5
 * 1 1 1 1
 * 1 1 2
 * 1 2 1
 * 2 1 1
 * 2 2
 *
 * 5 输出8
 * 1 1 1 1 1
 * 1 1 1 2
 * 1 1 2 1
 * 1 2 1 1
 * 1 2 2
 * 2 1 1 1
 * 2 1 2
 * 2 2 1
 *
 * @author lgn
 * @since 2022/1/10 11:13
 */

public class Demo4DP {
    public static void main(String[] args) {
        System.out.println(climbStair(7));
    }

    // 走1阶楼梯只有一种走法，走2阶楼梯有两种走法
    // 如果n是偶数，就可以凑成m种走法，m * 2 = n
    // 如果n是奇数，就可以凑成 m + 1 种走法，m*2 + 1 = n
    public static int climbStair(int n) {

        // 边界问题
//        if (n <= 2) {
//            return n;
//        }

        // 1、定义dp数组，确定下标含义
        // dp[i] 表示有i阶楼梯时，爬上楼顶的方式
        int[] dp = new int[n];

        // 2、确定递推公式
        // dp[i] = dp[i-1] + dp[i+2]

        // 3、初始化数组
        for (int i = 0; i < 2; i++) {
            dp[i] = i + 1;
        }

        // 4、确定遍历顺序，从数组低位到高位

        // 5、推导dp数组
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n-1];
    }
}
