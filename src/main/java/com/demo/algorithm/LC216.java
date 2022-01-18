package com.demo.algorithm;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

/**
 * 组合总和 III
 * 找出所有相加之和为 n 的 k 个数的组合（k个数，相加等于n）
 * 组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 *
 * 说明：
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。
 *
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 *
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 *
 * @author lgn
 * @since 2022/1/18 15:51
 */

public class LC216 {
    public static void main(String[] args) {
        LC216 lc216 = new LC216();
        System.out.println(lc216.combinationSum3(3, 7));
    }

    // 详情见第77题
    List<List<Integer>> retVal = new ArrayList<>();
    List<Integer> val = new ArrayList<>();

    public void backTracking(int k, int n, int currentSum, int startIndex) {
        // 此时没有继续下去的必要了
        if (currentSum > n) {
            return;
        }

        if (val.size() == k) {
            if (currentSum == n && !retVal.containsAll(val)) {
                retVal.add(new ArrayList<>(val));
            }
            return;
        }

        for (int i = startIndex; i <= 9 ; i++) {
            val.add(i);
            currentSum += i;
            backTracking(k, n, currentSum, i + 1);
            val.remove(val.size() - 1);
            currentSum -= i;
        }

    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        backTracking(k, n, 0, 1);
        return retVal;
    }
}
