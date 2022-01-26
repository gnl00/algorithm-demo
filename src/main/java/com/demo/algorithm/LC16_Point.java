package com.demo.algorithm;

import java.util.Arrays;

/**
 * 最接近的三数之和
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

public class LC16_Point {
    public static void main(String[] args) {
        // -1 -1 1 1 3
        int[] nums = {1,1,-1,-1,3};
        int target = -1;
        System.out.println(threeSumClosest2(nums, target));
    }

    // 双指针题解 O(N^2)
    public static int threeSumClosest2(int[] nums, int target) {
        int len = nums.length;
        if (len < 3 ) {
            return 0;
        }

        // 先对数组进行排序，之后进行遍历，求不同三个元素和target之间的差，最小的差即为所求

        Arrays.sort(nums);

        int sum = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < len; i++) {

            // 保证和上一次枚举的元素不相等
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1, right = len - 1;
            while (left < right) {

                int currentSum = nums[i] + nums[left] + nums[right];

                // 如果三数之和等于target，直接返回
                if (currentSum == target) {
                    return currentSum;
                }

                // 根据插值绝对值判断
                if (Math.abs(currentSum - target) < Math.abs(sum - target)) {
                    sum = currentSum;
                }

                if (currentSum > target) { // 如果大于target，将后面的指针向前移动
                    int R = right - 1;
                    // 移动到下一个不相等的元素
                    while (R > left && nums[R] == nums[right]) {
                        R--;
                    }
                    right = R;
                } else if(currentSum < target) { // 如果小于target，前面的指针向后移动
                    int L = left + 1;
                    // 移动到下一个不相等的元素
                    while (L < right && nums[L] == nums[left]) {
                        L++;
                    }
                    left = L;
                }
            }

        }
        return sum;
    }

    // 暴力 O(n^3)
    public static int threeSumClosest(int[] nums, int target) {
        int len = nums.length;
        if (len < 3 ) {
            return 0;
        }

        // 先对数组进行排序，之后进行遍历，求不同三个元素和target之间的差，最小的差即为所求

        Arrays.sort(nums);

        int sum = nums[0] + nums[1] + nums[2];

        for (int i = 0; i < len; i++) {
            int a = nums[i];
            for (int j = i + 1; j < len; j++) {
                int b = nums[j];
                for (int k = j + 1; k < len; k++) {
                    int c = nums[k];

                    int currentSum = a + b + c;
                    sum = Math.abs(sum - target) > Math.abs(currentSum - target) ? currentSum : sum;

                }
            }
        }
        return sum;
    }
}
