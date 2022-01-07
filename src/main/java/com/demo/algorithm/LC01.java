package com.demo.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 *
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 *
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 *
 * @author lgn
 * @since 2021/12/24 10:57
 */

public class LC01 {
    public static void main(String[] args) {
        int [] nums = {3,2,4};
        int target = 6;

        int[] ints = twoSum(nums, target);

        for (int i : ints) {
            System.out.print(i + " ");
        }
    }


    // O(n)
    public static int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();

        // O(n)
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        int[] res = new int[2];

        for (int i = 0; i < nums.length; i++) {
            int expectedKey = target - nums[i];
            if (map.containsKey(expectedKey) && map.get(expectedKey) != i) {
                res[0] = i;
                res[1] = map.get(expectedKey);
                return res;
            }
        }

        return new int[2];
    }
}
