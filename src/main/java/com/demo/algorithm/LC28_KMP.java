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
        System.out.println(lc28.strStrKMP("hello", "ll"));
    }

    /**
     * KMP 算法题解
     * <p> 思想是当出现字符串不匹配时，可以记录一部分之前已经匹配的文本内容，利用这些信息避免从头再去做匹配。
     * 所以如何记录已经匹配的文本内容，是 KMP 的重点，我们把已经匹配的内容记录在数组 next 中
     *
     * <p> 一、什么是前缀表（prefix table）/匹配表（Partial Match Table）
     * <p> <strong>前缀表/匹配表是一个数组，是用来回退的，它记录了模式串与主串(文本串)不匹配的时候，模式串应该从哪里开始重新匹配。</strong>
     * 
     * <p> 以字符串 abababca 为例：
     * <p> a 前缀为空，后缀也为空，前后缀的交集 空=0
     * <p> ab 前缀：a，后缀：b，前后缀交集 空=0
     * <p> aba 前缀：a ab，后缀：ba a，前后缀交集 a=1
     * <p> abab 前缀：a ab aba，后缀：bab ab b，前后缀交集 ab=2
     * <p> ababa 前缀：a ab aba abab，后缀：baba aba ba a，前后缀交集 aba=3
     * <p> ababab 前缀：a ab aba abab ababa，后缀：babab abab bab ab b，前后缀交集 abab=4
     * <p> abababc 前缀：a ab aba abab ababa ababab，后缀：bababc ababc babc abc bc c，前后缀交集 空=0
     * <p> abababca 前缀：a ab aba abab ababa ababab abababc，后缀：bababca ababca babca abca bca ca a，前后缀交集 a=1
     *
     * <p> 就可以得到下表：
     *
     * <p>  char| a b a b a b c a
     * <p> index| 0 1 2 3 4 5 6 7
     * <p> value| 0 0 1 2 3 4 0 1
     *
     * <p><strong>next数组就是一个前缀表</strong>
     *
     * <p> 二、前缀表如何使用
     * 例如，要在主串 abababca 中查找子串 ababca，next = {0, 0, 1, 2, 3, 4, 0, 1}
     * 两个字符串从头到尾遍历挨个字符进行匹配，在匹配到子串的 c 时就会发现不匹配了。如果使用的是暴力匹配，此时就需要重新从头开始匹配
     *
     *                   i
     *       a b a b a b a b c a
     * index 0 1 2 3 4 5 6 7 8 9
     * next  0 0 1 2 3 4 0 1
     *       a b a b a b c a
     *                   j
     * a != c，开始回退，j 回退的位置就是 j = next[j-1] = 4，i回退到i-1的位置 i = i - 1，再进行匹配
     *                 i
     *       a b a b a b a b c a
     * index 0 1 2 3 4 5 6 7 8 9
     * next  0 0 1 2 3 4 0 1
     *           a b a b a b c a
     *                 j
     *
     *
     * <p> 三、构造next数组
     * 构造next数组其实就是计算模式串s，前缀表的过程，主要分为以下三步：
     * 1、初始化
     * 2、处理前后缀不相同的情况
     * 3、处理前后缀相同的情况
     *
     *
     */
    public int strStrKMP(String haystack, String needle) {
        return -1;
    }

    public void getNext(int[] next, String s) {
        int j = -1;
        next[0] = j;
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
