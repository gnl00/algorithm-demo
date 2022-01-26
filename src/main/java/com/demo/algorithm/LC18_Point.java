package com.demo.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 四数之和
 *
 * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。
 * 请你找出并返回满足下述全部条件且不重复的四元组[nums[a], nums[b], nums[c], nums[d]]（若两个四元组元素一一对应，则认为两个四元组重复）：
 *
 * 0 <= a, b, c, d < n
 * a、b、c 和 d 互不相同
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * 你可以按 任意顺序 返回答案 。
 *
 * 输入：nums = [1,0,-1,0,-2,2], target = 0
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 *
 * 输入：nums = [2,2,2,2,2], target = 8
 * 输出：[[2,2,2,2]]
 *
 * 提示：
 *
 * 1 <= nums.length <= 200
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 *
 * @author lgn
 * @since 2022/1/18 16:57
 */

public class LC18_Point {
    public static void main(String[] args) {
        LC18_Point lc18 = new LC18_Point();
        // -4 -1 -1 0 1 2
        int [] nums = {0,0,0,1000000000,1000000000,1000000000,1000000000};
        int target = 1000000000;
        System.out.println(lc18.fourSum2(nums, target));
    }

    public List<List<Integer>> fourSum2(int[] nums, int target) {
        if (nums.length < 4 || nums == null) {
            return retVal;
        }

        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len - 3; i++) {
            // 跳过重复元素
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // 前四个元素和大于target，直接break
            if (((long)nums[i] + (long)nums[i + 1] + (long)nums[i + 2] + (long)nums[i + 3]) > target) {
                break;
            }

            // 第一个和最后三个元素和小于target，移动第一个指针
            if (((long)nums[i] + (long)nums[len - 3] + (long)nums[len - 2] + (long)nums[len - 1]) < target) {
                continue;
            }

            for (int j = i + 1; j < len - 2; j++) {
                // 跳过重复元素
                if (j > i + 1 && nums[j] == nums[j-1]) {
                    continue;
                }

                // 前四个元素和大于target，直接break
                if (((long)nums[j] + (long)nums[i] + (long)nums[j + 1] + (long)nums[j + 2]) > target) {
                    break;
                }

                // 第一个和最后三个元素和小于target，移动第一个指针
                if (((long)nums[j] + (long)nums[i] + (long)nums[len - 2] + (long)nums[len - 1]) < target) {
                    continue;
                }

                int left = j + 1, right = len - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        retVal.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        left++;

                        while (right > left && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        right--;

                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return retVal;
    }

    // 题解见第17题 ==> 77题 ==> 216题
    List<List<Integer>> retVal = new ArrayList<>();
    List<Integer> val = new ArrayList<>();

    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums.length < 4) {
            return retVal;
        }
        Arrays.sort(nums);
        backTracking(nums, target, 0, 0);
        return retVal;
    }

    public void backTracking(int[] nums, int target, int sum, int index) {
        if (val.size() == 4) {
            if (!retVal.contains(val) && sum == target) {
                retVal.add(new ArrayList<>(val));
            }
            return;
        }

        for (int i = index; i < nums.length; i++) {
            sum += nums[i];
            val.add(nums[i]);
            backTracking(nums, target, sum, i + 1);
            sum -= nums[i];
            val.remove(val.size() - 1);
        }
    }
}
