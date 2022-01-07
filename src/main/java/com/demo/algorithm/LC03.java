package com.demo.algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 找出字符串中最长的不重复字串的长的
 *
 * abcabcbb 最长子串为 abc | bca | cab，长度为3
 *
 * @author lgn
 * @since 2021/12/29 9:48
 */

public class LC03 {
    public static void main(String[] args) {
        System.out.println(longestSubString1("pwwkew"));
    }

    // 滑动窗口 O(n)
    public static int longestSubString1(String s) {
        int count = 0;
        int i = 0;

        Map<Character, Integer> map = new HashMap();
        while (i < s.length()) {
            if (!map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), i);
                count = count < map.size() ? map.size() : count;
                i++;
            } else {
                i = map.get(s.charAt(i)) + 1;
                map.clear();
            }
        }
        return count;
    }

    // 暴力 O(n^2)
    public static int longestSubString(String s) {
        int count = 1;

        for (int i = 0; i < s.length(); i++) {
            Set<Character> set = new HashSet<>();
            for (int j = i; j < s.length(); j++) {
                if (!set.contains(s.charAt(j))) {
                    set.add(s.charAt(j));
                } else {
                    break;
                }
            }
            count = count < set.size() ? set.size() : count;
        }

        return count;
    }
}
