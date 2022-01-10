package com.demo.algorithm;

/**
 * 斐波那契数列
 *
 * @author lgn
 * @since 2022/1/10 10:47
 */

public class Demo3DP {
    public static void main(String[] args) {
        System.out.println(fib(9));
        System.out.println(fib2(9));
    }

    // 递归求前n项斐波拉契数列
    // 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233，377，610，987，1597，2584，4181，6765，10946，17711，28657，46368
    // 这个数列从第3项开始，每一项都等于前两项之和。
    // 1 1
    // 2 1
    // 3 3
    // 4 4
    // 5 7
    // 6 11
    // 7 18
    public static int fib(int n) {
        if (n < 3) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    // 动态规划求斐波那契数列 O(n)
    public static int fib2(int n) {

        // 1、确定dp数组以及下标含义
        // dp[i] 表示第i位斐波那契数列和
        int[] dp = new int[n];

        // 2、确定递推公式
        // dp[i] = dp[i-1] + dp[i-2]

        // 3、初始化dp数组
        for (int i = 0; i < 2; i++) {
            dp[i] = 1;
        }

        // 4、确定遍历顺序
        // 因为i位置的结果需要先得到i-1和i-2位置的结果
        // 所以遍历顺序是从数组低位到高位


        // 5、举例推导dp数组
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n-1];
    }
}
