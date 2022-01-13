package com.demo.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 整数转罗马数字
 *
 * 罗马数字包含以下七种字符：I，V，X，L，C，D和M
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 *
 * 例如，罗马数字 2 写做II，即为两个并列的 1。
 * 12 写做XII，即为X+II。
 * 27 写做XXVII, 即为XX+V+II
 *
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，
 * 例如 4 不写做IIII，而是IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。
 * 同样地，数字 9 表示为IX。这个特殊的规则只适用于以下六种情况：
 * I可以放在V(5) 和X(10) 的左边，来表示 4 和 9。
 * X可以放在L(50) 和C(100) 的左边，来表示 40 和90。
 * C以放在D(500) 和M(1000) 的左边，来表示400 和900。
 *
 * 给你一个整数，将其转为罗马数字。
 *
 * 输入:num = 3
 * 输出: "III"
 *
 * 输入:num = 4
 * 输出: "IV"
 *
 * 输入:num = 9
 * 输出: "IX"
 *
 * 输入:num = 58
 * 输出: "LVIII"
 * 解释: L = 50, V = 5, III = 3.
 *
 * 输入:num = 1994
 * 输出: "MCMXCIV"
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 *
 * 提示：1 <= num <= 3999
 *
 * @author lgn
 * @since 2022/1/12 18:21
 */

public class LC12 {
    public static void main(String[] args) {
        System.out.println(intToRoman(1993));
        System.out.println(intToRoman2(1993));
    }

    // 求模题解
    public static String intToRoman2(int num) {

        /**
         *
         * 回顾前言中列出的这 13 个符号，可以发现：
         * 千位数字只能由 M 表示；
         * 百位数字只能由 C，CD，D 和 CM 表示；
         * 十位数字只能由 X，XL，L 和 XC 表示；
         * 个位数字只能由 I，IV，V 和 IX 表示。
         *
         * 利用模运算和除法运算，我们可以得到 num 每个位上的数字
         * thousands_digit = num / 1000
         * hundreds_digit = (num % 1000) / 100
         * tens_digit = (num % 100) / 10
         * ones_digit = num % 10
         */

        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        String romanStr = "";

        int thousandIndex = num / 1000;
        int hundredIndex = (num % 1000) / 100;
        int tenIndex = (num % 100) / 10;
        int oneIndex = num % 10;

        romanStr += thousands[thousandIndex];
        romanStr += hundreds[hundredIndex];
        romanStr += tens[tenIndex];
        romanStr += ones[oneIndex];

        return romanStr;
    }

    // 贪心题解
    // 罗马数字的规则是：对于罗马数字从左到右的每一位，选择尽可能大的符号值。对于 140，最大可以选择的符号值为 C=100。
    // 接下来，对于剩余的数字 40，最大可以选择的符号值为 XL=40。因此，140 的对应的罗马数字为 C+XL=CXL。
    public static String intToRoman(int num) {

        /**
         * 根据罗马数字的唯一表示法，为了表示一个给定的整数 num，我们寻找不超过 num 的最大符号值，将 num 减去该符号值，
         * 然后继续寻找不超过 num 的最大符号值，将该符号拼接在上一个找到的符号之后，循环直至 num 为 0。最后得到的字符串即为 num 的罗马数字表示。
         */

        // 罗马数字的规则是：对于罗马数字从左到右的每一位，选择尽可能大的符号值。所以在定义时顺序从大到小匹配起来比较快
        int[] values = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
        String[] symbols = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};

        String romanStr = "";

        for (int i = values.length - 1; i >= 0; i--) {
            int value = values[i];
            String symbol = symbols[i];
            while (num >= value) {
                romanStr += symbol;

                num -= value;
            }

            if (num == 0) {
                break;
            }

        }

        return romanStr;
    }

    public static String switches(int num) {

        String res = null;

        if (num < 4) {
            for (int i = 0; i < num; i++) {
                res += "I";
            }
        }

        // 4的10^n倍数
        if (num % 4 == 0) {

            if (num < 10) {
                return "";
            } else {

            }

        }

        // 9的10^n倍数
        if (num % 9 == 0) {}

        return null;
    }
}
