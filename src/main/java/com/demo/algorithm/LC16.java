package com.demo.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 给你一个长度为 n 的整数数组nums和 一个目标值target。请你从 nums 中选出三个整数，使它们的和与target最接近。
 * 返回这三个数的和。
 * 假定每组输入只存在恰好一个解。
 *
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 *
 * 输入：nums = [0,0,0], target = 1
 * 输出：0
 *
 * 提示：
 * 3 <= nums.length <= 1000
 * -1000 <= nums[i] <= 1000
 * -104 <= target <= 104
 *
 * @author lgn
 * @since 2022/1/17 17:23
 */

public class LC16 {
    public static void main(String[] args) {
        int[] nums = {0,0,0};
        int target = 1;
        System.out.println(threeSumClosest(nums, target));
    }

    public static int threeSumClosest(int[] nums, int target) {

        int len = nums.length;

        if (len < 3 ) {
            return 0;
        }

        // 1、定义dp数组，并确定数组下标含义
        // dp[i]表述nums[i]和target相差的商
        // 遍历nums数组，将相差的商保存到dp数组中
        // 之后对dp数组进行排序，前三个元素之和即为所求


        return 0;
    }
}
