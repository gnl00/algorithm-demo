package com.demo.algorithm;

/**
 * 实现 strStr()
 * 给你两个字符串haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回 -1 。
 *
 * 说明：
 * 当needle是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * 对于本题而言，当needle是空字符串时我们应当返回 0 。这与 C 语言的strstr()以及 Java 的indexOf()定义相符。
 *
 * 输入：haystack = "hello", needle = "ll"
 * 输出：2
 *
 * 输入：haystack = "aaaaa", needle = "bba"
 * 输出：-1
 *
 * 输入：haystack = "", needle = ""
 * 输出：0
 *
 * @author lgn
 * @since 2022/1/25 14:21
 */

public class LC28_KMP {
    public static void main(String[] args) {
        LC28_KMP lc28 = new LC28_KMP();
        System.out.println(lc28.strStr2("hello", "ll"));
    }

    /**
     * KMP 算法题解
     * <p> 思想是当出现字符串不匹配时，可以记录一部分之前已经匹配的文本内容，利用这些信息避免从头再去做匹配。
     * <p> 所以如何记录已经匹配的文本内容，是 KMP 的重点，我们把已经匹配的内容记录在数组 next 中
     *
     * <p> 一、什么是前缀表
     * 前缀表是用来回退的，它记录了模式串与主串(文本串)不匹配的时候，模式串应该从哪里开始重新匹配。
     * 
     * <p> 以字符串"hello"为例
     * <p> "hello"的前缀集合: h he hel hell
     * <p> "hello"的后缀集合: ello llo lo o
     *
     * next数组就是一个前缀表（prefix table）
     *
     * <p> Knuth-Morris-Pratt 算法的核心为前缀函数，记作 π(i)，其定义如下
     * <p> 对于长度为 m 的字符串 s，其前缀函数 π(i) ( 0 ≤ i  < m ) 表示 s 的子串 s[0:i] 的最长的相等的真前缀与真后缀的长度。
     * <p> 特别地，如果不存在符合条件的前后缀，那么 π(i) = 0。其中真前缀与真后缀的定义为不等于自身的的前缀与后缀。
     *
     * <p> 举个例子说明：字符串 aabaaab 的前缀函数值依次为 0,1,0,1,2,2,3
     *
     * <p> π(0)=0，因为 a 没有真前缀和真后缀，根据规定为 00（可以发现对于任意字符串 \pi(0)=0π(0)=0 必定成立）；
     *
     * <p> π(1)=1，因为 aa 最长的一对相等的真前后缀为 a，长度为 1；
     *
     * <p> π(2)=0，因为 aab 没有对应真前缀和真后缀，根据规定为 0；
     *
     * <p> π(3)=1，因为 aaba 最长的一对相等的真前后缀为 a，长度为 1；
     *
     * <p> π(4)=2，因为 aabaa 最长的一对相等的真前后缀为 aa，长度为 2；
     *
     * <p> π(5)=2，因为 aabaaa 长的一对相等的真前后缀为 aa，长度为 2；
     *
     * <p> π(6)=3，因为 aabaaab 最长的一对相等的真前后缀为 aab，长度为 3。
     *
     * <p> 有了前缀函数，我们就可以快速地计算出模式串在主串中的每一次出现。
     *
     * 如何求解前缀函数
     * 长度为 m 的字符串 s 的所有前缀函数的求解算法的总时间复杂度是严格 O(m) 的，
     * 且该求解算法是增量算法，即我们可以一边读入字符串，一边求解当前读入位的前缀函数。
     */
    public int strStr2(String haystack, String needle) {
        return -1;
    }

    /**
     * 暴力
     */
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }

        int n = haystack.length();
        int m = needle.length();
        for (int i = 0; i + m <= n; i++) {
            boolean flag = true;
            for (int j = 0; j < m; j++) {
                // haystack和needle的当前字符不匹配，直接break，匹配 haystack 的下一个字符
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    flag = false;
                    break;
                }

                // needle 循环到末尾都匹配

            }
            if (flag) {
                return i;
            }
        }
        return -1;
    }
}
