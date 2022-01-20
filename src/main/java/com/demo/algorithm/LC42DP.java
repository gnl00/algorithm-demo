package com.demo.algorithm;

/**
 * 给定n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 *
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 *
 * @author lgn
 * @since 2022/1/12 10:51
 */

public class LC42DP {
    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap2(height));
    }

    // 双指针
    public static int trap2(int[] height) {

        /**
         * 在使用dp的解法中，数组leftMax和rightMax只使用一次之后就用不到了
         */

        int len = height.length;

        // 记录当前列左边最高列
        int leftMax = 0;

        int[] rightMax = new int[len];
        rightMax[len - 1] = height[len - 1];

        for (int i = len - 2; i >= 0 ; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int area = 0;

        for (int i = 1; i < len; i++) {
            // 计算当前列左边最高列
            leftMax = Math.max(leftMax, height[i - 1]);
            area += Math.min(leftMax, rightMax[i]) > height[i] ? Math.min(leftMax, rightMax[i]) - height[i] : 0;
        }

        return area;
    }

    // dp 按列求题解
    public static int trap(int[] height) {

        /**
         * 求每一列的存储的雨水，需要关注三列，当前列、左边最高的列、右边最高的列
         * 装水的多少，是由左右两边较矮的列决定的
         *
         * 以 { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 } 为例
         * 根据较矮列和当前列的高度可以分为三种情况：
         * 1、较矮列的高度大于当前列的高度，比如当前为第4列时，高为1，左边最高列高为2，右边最高列高为3，此时只需要左右两边较矮的一边，减去当前列的高度就可以了，即 2-1=1
         * 2、较矮列的高度小于当前列的高度，比如当前为第3列时，高为2，左边最高列高为1，右边最高列高为3，因为当前列【大于】左右两边中较矮的一端，此时能存储的雨水为 0
         * 3、较矮列的高度等于当前列的高度，比如当前为第8列时，高为2，左边最高列高为3，右边最高列高为2，因为当前列【等于】左右两边中较矮的一端，此时能存储的雨水为 0
         *
         * 因此只需要遍历每一列，然后分别求出这一列两边最高的墙。找出较矮的一端，和当前列的高度比较，结果就是上边的三种情况
         */

        int len = height.length;

        // 记录每一列的左边最高列
        int[] leftMax = new int[len];
        // 第一列的左边
        leftMax[0] = height[0];

        // 记录每一列的右边最高列
        int[] rightMax = new int[len];
        // 最后一列的右边
        rightMax[len - 1] = height[len - 1];

        // 分别计算每一列的左最高列
        for (int i = 1; i < len; i++) {
            leftMax[i] = Math.max(leftMax[i-1], height[i]);
        }

        // 分别计算每一列的右最高列
        for (int i = len - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int area = 0;

        // 计算当前列
        for (int i = 0; i < len; i++) {
            // 只需要关注两边较矮列的高度大于当前列的高度的情况即可
            area += (height[i] < leftMax[i] || height[i] < rightMax[i]) ? Math.min(leftMax[i], rightMax[i]) - height[i] : 0;
        }

        return area;
    }

}
