package com.demo.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 电话号码的字母组合

 * 给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * 1    2abc 3def
 * 4ghi 5jkl 6mno
 * 7pqr 8stu 9wxyz
 *
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 * 输入：digits = ""
 * 输出：[]
 *
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 *
 * @author lgn
 * @since 2022/1/18 13:51
 */

public class LC17_BT {
    public static void main(String[] args) {
        LC17_BT lc17BT = new LC17_BT();
        String digits = "22";
        System.out.println(lc17BT.letterCombinations(digits));
    }

    /**
     * 回溯法
     * 回溯法也可以叫做回溯搜索法，它是一种搜索的方式。回溯是递归的副产品，只要有递归就会有回溯
     * 因为回溯的本质是穷举，穷举所有可能，然后选出我们想要的答案，因此可以看出回溯并不是一个高效的算法
     * 如果想让回溯法高效一些，可以加一些剪枝的操作，但也改不了回溯法就是穷举的本质。
     *
     * 回溯法一般可以解决如下几种问题：
     * 组合问题，N个数里面按一定规则找出k个数的集合
     * 切割问题，一个字符串按一定规则有几种切割方式
     * 子集问题，一个N个数的集合里有多少符合条件的子集
     * 排列问题，N个数按一定规则全排列，有几种排列方式
     * 棋盘问题，N皇后，解数独等等
     *
     * 如何理解回溯法
     * 回溯法解决的问题都可以抽象为树形结构
     * 因为回溯法解决的都是在集合中递归查找子集，集合的大小就决定了树的宽度，递归的深度决定的树的深度。
     * 递归就要有终止条件，所以必然是一棵高度有限的树（N叉树）
     *
     * 回溯法解题步骤：
     * 1、函数返回值以及参数
     * 回溯算法中函数返回值一般为void，
     * 因为回溯算法需要的参数没那么容易一次性确定下来，所以一般是先写逻辑，然后需要什么参数，就填什么参数
     * void backTracking(params) {}
     *
     * 2、函数终止条件
     * 抽象为树形结构为例子，一般来说遍历到叶子节点就能找到满足条件的答案，将答案保存，本次递归结束
     * if(end Condition) {
     *     save result
     *     return;
     * }
     *
     * 3、函数搜索遍历过程
     * 回溯法一般在集合中递归搜索，集合的大小就决定了树的宽度，递归的深度决定的树的深度。
     * 即使用for循环遍历当前层次的集合中的每个元素，在for循环横向遍历进行的同时，使用递归进行纵向遍历，这样就能把一棵树遍历完了
     * for(当前层集合中的元素) {
     *     处理遍历到的节点
     *     递归
     *     回溯
     * }
     *
     * https://programmercarl.com/%E5%9B%9E%E6%BA%AF%E7%AE%97%E6%B3%95%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%80.html
     */
    public List<String> letterCombinations(String digits) {

        // 长度等于0，直接返回
        if (digits == null || digits.length() == 0 ) {
            return retVal;
        }

        String[] arr = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        backTracking(digits, arr, 0);
        return retVal;
    }



    // 保存最终结果
    List<String> retVal = new ArrayList<>();
    // 保存节点结果
    StringBuilder strVal = new StringBuilder();

    public void backTracking(String digits, String[] arr,  int currentIndex) {
        // 回溯终止条件
        if (strVal.length() == digits.length()) {
            if (!retVal.contains(strVal)) {
                retVal.add(strVal.toString());
            }
            return;
        }

        String letters = arr[digits.charAt(currentIndex) - '0'];
        for (int i = 0; i < letters.length(); i++) {
            strVal.append(letters.charAt(i));
            backTracking(digits,arr, currentIndex + 1);
            strVal.deleteCharAt(strVal.length() - 1);
        }

    }
}
