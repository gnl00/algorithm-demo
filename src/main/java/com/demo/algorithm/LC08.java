package com.demo.algorithm;

/**
 * 请你来实现一个myAtoi(string s)函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数），不可以使用long
 *
 * 函数myAtoi(string s) 的算法如下：
 * 读入字符串并丢弃无用的前导空格
 * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
 * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
 * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
 * 如果整数数超过 32 位有符号整数范围 [−2^31, 2^31− 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −2^31 的整数应该被固定为 −2^31 ，大于 2^31− 1 的整数应该被固定为 2^31− 1 。
 * 返回整数作为最终结果。
 * 注意：
 * 本题中的空白字符只包括空格字符 ' ' 。
 * 除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。
 *
 * @author lgn
 * @since 2022/1/10 14:48
 */

public class LC08 {
    public static void main(String[] args) {
        System.out.println(myAtoi("2147483648"));

        // System.out.println(Integer.MAX_VALUE); // 2147483647
        // System.out.println(Integer.MIN_VALUE); // -2147483648


        // System.out.println(0 + '0'); // 48
        // System.out.println(9 + '0'); // 57
    }

    // O(n) 重点在对于边界问题的处理上，注意处理顺序
    public static int myAtoi(String s) {

        // 丢弃前后空格
        s = s.trim();

        // 处理边界
        if (s.length() == 0) {
            return 0;
        }

        char[] chars = s.toCharArray();

        // 判断是否存在操作符/判断正负
        char firstChar = chars[0];

        // 第一个字符非数字和操作符
        if (!isNum(firstChar) && !isOperator(firstChar)) {
            return 0;
        }

        int startIndex = 0;
        boolean isNegative = false;
        if (isOperator(firstChar)) {
            isNegative = isNegative(firstChar);
            startIndex = 1;
        }

        int n = 0;

        // O(n)
        for (int i = startIndex; i < chars.length; i++) {
            if (isNum(chars[i])) {
                int carry = chars[i] - '0';

                // 将Integer.MAX_VALUE缩小并加上相同的carry，小于n证明溢出
                if (n > (Integer.MAX_VALUE - carry) / 10) {
                    return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }

                n = n * 10 + carry;

            } else {
                break;
            }
        }

        return isNegative ? -1 * n : n;
    }

    // 判断正负
    public static boolean isNegative(char ch) {
        return ch == '-' ? true : false;
    }

    // 是否是操作符
    public static boolean isOperator(char ch) {
        return ch == '-' || ch == '+';
    }

    // 判断是否是数字
    public static boolean isNum(char ch) {
        return 48 <= ch && ch <= 57;
    }
}
