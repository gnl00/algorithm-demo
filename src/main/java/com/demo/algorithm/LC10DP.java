package com.demo.algorithm;

/**
 * 给你一个字符串s和一个字符规律p，请你来实现一个支持 '.'和'*'的正则表达式匹配。说人话，自己实现一个正则匹配
 *
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖整个字符串s的，而不是部分字符串。
 *
 * 输入：s = "aa" p = "a"
 * 输出：false
 * 解释："a" 无法匹配 "aa" 整个字符串
 *
 * 输入：s = "aa" p = "a*"
 * 输出：true
 * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 *
 * 输入：s = "ab" p = ".*"
 * 输出：true
 * 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 *
 * 输入：s = "aab" p = "c*a*b"
 * 输出：true
 * 解释：因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 *
 * 输入：s = "[mis] s [is] si [p] pi" p = "mis*is*p*."
 * 输出：false
 *
 * 我们每次从字符串 p 中取出一个字符或者「字符 + 星号」的组合，并在 s 中进行匹配
 * 对于 p 中一个字符而言，它只能在 s 中匹配一个字符，匹配的方法具有唯一性
 * 而对于 p 中[字符 + 星号]的组合而言，它可以在 s 中匹配任意自然数个字符，并不具有唯一性
 * 因此我们可以考虑使用动态规划，对匹配的方案进行枚举。
 *
 * 用 f[i][j] 表示 s 的前 i 个字符与 p 中的前 j 个字符是否能够匹配
 * 显然 s 中的第 0 个字符和 p 中的第 0 个字符是相匹配的 ===> f[0][0] = true
 *
 * 1、
 * 如果 p 的第 j 个字符是一个小写字母，那么我们必须在 s 中匹配一个相同的小写字母
 * f[i][j] = s[i] == p[j]
 *
 * 2、
 * 如果 p 的第 j 个字符是 *，那么就表示我们可以对 p 的第 j-1 个字符匹配任意自然数次
 * 在匹配 0 次的情况下，我们有 f[i][j] = f[i][j−2]
 *
 * @author lgn
 * @since 2021/12/28 14:44
 */

public class LC10DP {
    public static void main(String[] args) {

        String s = "aab";
        String p = "c*a*b";

        System.out.println(isMatch(s, p));

        System.out.println(s.matches("c*a*b"));

    }

    public static boolean isMatch(String s, String p) {

        int m = s.length();
        int n = p.length();

        // 用 f[i][j] 表示 s 的前 i 个字符与 p 中的前 j 个字符是否能够匹配
        boolean [][] f = new boolean[m + 1][n + 1];
        // 显然 s 中的第 0 个字符和 p 中的第 0 个字符是相匹配的
        f[0][0] = true;

        for (int i = 0; i <= m ; ++i) {
            for (int j = 1; j <= n ; ++j) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }

        return f[m][n];
    }

    public static boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }

        if (p.charAt(j - 1) == '.') {
            return true;
        }

        return s.charAt(i - 1) == p.charAt(j - 1);
    }
}
