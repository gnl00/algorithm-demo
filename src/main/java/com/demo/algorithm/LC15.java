package com.demo.algorithm;

import java.util.*;

/**
 * 三数之和
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
        int[] nums = {-2,0,0,2,2};
        System.out.println(threeSum(nums));
    }

    public static List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> retList = new ArrayList<>();

        if (nums.length < 2 || nums == null) {
            return retList;
        }

        // 先进行排序
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {

            // 对于三个数而言，如果第一个数大于0，后面的数都大于或等于它，那么三个数相加势必大于0，就没必要在继续下去了
            if (nums[i] > 0) {
                break;
            }

            // 去掉重复情况
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }

            // 第一个值
            int first = nums[i];

            // 接下来只需要关注第一个值后面的部分即可
            int left = i + 1, right = nums.length - 1;

            while (left < right) {
                int sum = first + nums[left] + nums[right];
                // 三数相加等于0，符合要求
                if (sum == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(first);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    retList.add(list);

                    // 左边跳过重复情况
                    while(left < right && nums[left+1] == nums[left]) {
                        left++;
                    }

                    // 右边跳过重复情况
                    while (left < right && nums[right-1] == nums[right]) {
                        right--;
                    }

                    left++;
                    right--;
                } else if (sum < 0){
                    // 三数相加小于0，移动left
                    left++;
                } else {
                    // 三数相加大于0，移动right
                    right--;
                }
            }

        }
        return retList;
    }

}
