package com.demo.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 括号生成
 * 数字 n代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 *
 * 输入：n = 1
 * 输出：["()"]
 *
 * 提示：
 * 1 <= n <= 8
 *
 * @author lgn
 * @since 2022/1/20 11:26
 */

public class LC22_BT {
    public static void main(String[] args) {
        LC22_BT lc22 = new LC22_BT();
        System.out.println(lc22.generateParenthesis(3));
    }

    public List<String> generateParenthesis3(int n) {
        if (n < 1) {
            return retVal;
        }
        generate("", n, n);
        return retVal;
    }

    public void generate(String val, int leftCount, int rightCount) {
        if (leftCount == 0 && rightCount == 0) {
            retVal.add(val);
            return;
        }

        if (leftCount == rightCount) { // 左右括号数相等，下一个只能用左括号
            generate(val + "(", leftCount - 1, rightCount);
        } else if (leftCount < rightCount) { // 左括号数需要小于右括号数
            // 此时可以先用左括号也可以先用右括号
            if (leftCount > 0) {
                generate(val + "(", leftCount - 1, rightCount);
            }
            generate(val + ")", leftCount, rightCount - 1);
        }
    }

    // dfs
    public List<String> generateParenthesis2(int n) {
        dfs(n, n, "");
        return retVal;
    }

    public void dfs(int countLeft, int countRight, String val) {
        // 左右括号都不剩了，终止递归
        if (countLeft == 0 && countRight == 0) {
            retVal.add(val);
            return;
        }

        // 如果还剩左括号，拼接左括号
        if (countLeft > 0) {
            dfs(countLeft - 1, countRight, val + "(");
        }

        // 如果还剩右括号，拼接右括号
        // 剩余的左括号要小于等于右括号
        if (countRight > countLeft) {
            dfs(countLeft, countRight - 1, val + ")");
        }
    }

    /**
     * 利用回溯，把每一种括号组合的结果都枚举出来，然后再根据枚举内容选择满足条件的值放到 List 中，最后返回
     *
     * 目前进行回溯的单位是左括号或者右括号，其实可以使用()作为回溯单位，可以减少循环次数
     */
    public List<String> generateParenthesis(int n) {

        if (n == 1) {
            retVal.add("()");
            return retVal;
        }

        String bracket = "()";

        String[] brackets = new String[n];
        for (int i = 0; i < n; i++) {
            brackets[i] = bracket;
        }

        backTracking(bracket, n);
        return retVal;
    }

    List<String> retVal = new ArrayList<>();
    StringBuilder val = new StringBuilder();

    /**
     * 回溯函数
     * 优化思路：对循环条件进行剪枝
     */
    public void backTracking(String bracket, int n) {

        if (val.length() == n * 2) {
            if (isValid(val.toString())) {
                retVal.add(val.toString());
            }
            return;
        }

        for (char c : bracket.toCharArray()) {
            val.append(c);
            backTracking(bracket, n);
            val.deleteCharAt(val.length() - 1);
        }

    }

    /**
     * 判断有没有满足条件的值
     */
    public boolean isValid(String s) {
        int len = s.length() / 2;
        for (int i = 0; i < len; i++) {
            s = s.replace("()", "");
        }
        return s.length() == 0;
    }
}
