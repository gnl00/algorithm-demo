package com.demo.algorithm;

/**
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
 *
 * @author lgn
 * @since 2021/12/28 14:28
 */

public class LC09 {
    public static void main(String[] args) {
        System.out.println(isPalindrome(121));
    }

    // O(n)
    public static boolean isPalindrome(int x) {

        if (x < 0) return false;

        if(x >= 0 && x < 10) return true;

        long n = 0;
        int tmp = x;

        while (tmp != 0) {
            n = n * 10 + tmp % 10;
            tmp /= 10;
        }

        return (int)n == x;
    }
}
