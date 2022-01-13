package com.demo.algorithm;

import java.util.Collections;
import java.util.List;

/**
 * 三数之和
 *
 * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 *
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 *
 * 输入：nums = []
 * 输出：[]
 *
 * 输入：nums = [0]
 * 输出：[]
 *
 * 提示：
 * 0 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 *
 * @author lgn
 * @since 2022/1/13 15:40
 */

public class LC15 {
    public static void main(String[] args) {

    }

    public List<List<Integer>> threeSum(int[] nums) {

        if (nums.length < 2) {
            return Collections.emptyList();
        }

        return null;

    }
}
