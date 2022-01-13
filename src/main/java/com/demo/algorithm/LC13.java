package com.demo.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 罗马数字转整数
 * 罗马数字包含以下七种字符:I，V，X，L，C，D和M
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 *
 * 例如， 罗马数字 2 写做II，即为两个并列的 1 。
 * 12 写做XII，即为X+II。
 * 27 写做XXVII, 即为XX+V+II。
 *
 * @author lgn
 * @since 2022/1/12 19:31
 */

public class LC13 {
    public static void main(String[] args) {
        String romanStr = "XL";
        System.out.println(romanToInt(romanStr));
    }

    public static int romanToInt(String s) {

        // 遍历字符串，需要关注的就是当前字符和它后面的字符
        // 情况1、如果 s = III，前面的字符和后面的字符相同，那么当前字符+后面的字符，此处就是1+1+1
        // 情况2、如果 s = IV，IX，前面的字符和后面的字符不同，并且后一个字符大于前面的字符，可能出现的情况就是 4、9，此时需要关注的就是后一个字符V和X，将后一个字符-1，即5-1和10-1
        // 情况3、如果 s = VI，前面的字符和后面的字符不同，并且后一个字符小于前面的字符，可能出现的情况就是 6，此时只需要当前字符+后面的字符即可
        // 情况1和3可以合并

        // 两位数情况：
        // XXX 30
        // XL 40

        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int current = 0;

        int i = 0;
        while (i < s.length() - 1) {

            Integer before = map.get(s.charAt(i));
            Integer after = map.get(s.charAt(i + 1));

            int tmp = 0;
            if (before >= after) {
                tmp = before;
                i = i + 1;
            } else {
                tmp = after - before;
                i = i + 2;
            }
            current += tmp;
        }

        // 处理字符串未遍历完的情况
        while (i < s.length()) {
            current += map.get(s.charAt(i));
            i++;
        }

        return current;
    }
}
