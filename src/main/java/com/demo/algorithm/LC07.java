package com.demo.algorithm;

/**
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * 如果反转后整数超过 32 位的有符号整数的范围[−2^31, 2^31− 1] ，就返回 0。
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 *
 * @author lgn
 * @since 2021/12/27 20:42
 */

public class LC07 {
    public static void main(String[] args) {
        System.out.println(reverse(-2147483648));
    }

    public static int reverse(int x) {

        StringBuilder numStr = new StringBuilder(x + "");

        // 处理负数
        if (x < 0) {
            numStr.delete(0, 1);
        }

        String retStr = numStr.reverse().toString();

        int retInt = 0;

        try {
            retInt = Integer.parseInt(retStr);
        } catch (NumberFormatException e) {
            return 0;
        }

        return x < 0 ? retInt * -1 : retInt;
    }
}
