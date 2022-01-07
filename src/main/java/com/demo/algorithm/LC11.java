package com.demo.algorithm;

/**
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点(i,ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i的两个端点分别为(i,ai) 和 (i, 0) 。
 * 找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
 * 说明：你不能倾斜容器。
 *
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为49。
 *
 * 输入：height = [1,1]
 * 输出：1
 *
 * 输入：height = [4,3,2,1,4]
 * 输出：16
 *
 * f[i][j] 表示坐标i和j之间所能容纳的水体积
 *
 *
 *
 * @author lgn
 * @since 2021/12/28 17:26
 */

public class LC11 {
    public static void main(String[] args) {
        // [2,3,4,5,18,17,6]
        int[] height = { 2,3,4,5,18,17,6 };
        System.out.println(maxArea1(height));
    }

    // 双指针 O(n)
    public static int maxArea1(int[] height) {
        int max = 0;

        for (int i = 0, j = height.length - 1; i < j ;) {
            int currentLen = j - i;
            int currentHeight = height[i] < height[j] ? height[i++] : height[j--];
            max = max < currentLen * currentHeight ? currentLen * currentHeight : max;
        }

        return max;
    }

    // 暴力 O(n^2) 超时
    public static int maxArea(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int currentLen = j - i;
                int currentHeight = Math.min(height[j], height[i]);
                max = max < currentLen * currentHeight ? currentLen * currentHeight : max;
            }
        }

        return max;
    }
}
