package com.demo.algorithm;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 最长公共前缀
 *
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串""。
 *
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 *
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 *
 * 提示：
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] 仅由小写英文字母组成
 *
 * @author lgn
 * @since 2022/1/13 14:32
 */

public class LC14 {
    public static void main(String[] args) {
        String[] strs = {"flower","flow","flight"};
        System.out.println(longestCommonPrefix(strs));
    }

    public static String longestCommonPrefix2(String[] strs) {

        // 数组中仅有一个元素的话，最长前缀就是它本身
        if (strs.length == 1) {
            return strs[0];
        }

        // 对字符串进行排序
        // 排序完成比较头和尾的公共前缀即可
        return null;

    }

    public static String longestCommonPrefix(String[] strs) {

        // Any ideas ?

        // 数组中仅有一个元素的话，最长前缀就是它本身
        if (strs.length == 1) {
            return strs[0];
        }

        // 选第一个元素最为初始前缀
        String prefixMin = strs[0];

        for (int i = 1; i < strs.length; i++) {

            String before = strs[i-1];
            String after = strs[i];
            int minLen = Math.min(before.length(), after.length());

            int j = 0;
            String prefix = "";
            while (j < minLen) {
                if (before.charAt(j) == after.charAt(j)) {
                    prefix += before.charAt(j);
                } else {
                    break;
                }
                j++;
            }
            prefixMin = prefix.length() < prefixMin.length() ? prefix : prefixMin;
        }

        return prefixMin;
    }

}
