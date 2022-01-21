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

public class LC10_DP {
    public static void main(String[] args) {

        String s = "aab";
        String p = "aaa.*";

        System.out.println(isMatch(s, p));

        System.out.println(s.matches(p));

    }

    // 动态规划题解
    public static boolean isMatch(String s, String p) {

        if (null == s || null == p) {
            return false;
        }

        int sLen = s.length();
        int pLen = p.length();

        // 一、定义dp数组以及下标含义
        // dp[i][j] 表示 s 的前 i 个字符与 p 中的前 j 个字符是否能够匹配
        boolean[][] dp = new boolean[sLen+1][pLen+1];

        // 二、确定dp数组推导公式
        // 很容易知道dp[i][j]的需要根据dp[i-1][j-1]得出，若是dp[i][j]前面出现false的情况，那两个字符串显然是不匹配的
        // 已知 dp[i-1][j-1] = true 意思就是前面的字符都匹配了，当前 dp[i][j] 的情况未知

        // 此时需要分情况讨论s.charAt(i) 和 p.charAt(j)：
        // 1、s.charAt(i) == p.charAt(j) 时，dp[i][j] = dp[i-1][j-1]

        // 2、考虑p.charAt(j)：
        // 2.1、p.charAt(j) == '.'时，dp[i][j] = dp[i-1][j-1]
        // 2.2、p.charAt(j) == '*' 时：
        // 首先知道 * 可以匹配 0 个或任意多个字符，但是只有 * 前面的字符都匹配上了，才轮到 * 匹配。
        // 所以要考虑 * 前面的元素 p.charAt(j-1) 是否匹配，如果 * 前面的字符不匹配，那 * 也就无须匹配了
        // 根据 s.charAt(i) 和 p.charAt(j-1) 是否相等可以分为以下情况：
        // 2.2.1、可以匹配：s.charAt(i) == p.charAt(j-1) 或者 p.charAt(j-1) == ‘.’
        // 2.2.2 匹配不上：s.charAt(i) != p.charAt(j-1)

        // 3、怎么判断*前面的字符匹配
        // dp[i][j] = dp[i-1][j] 前多个字符匹配的情况
        // dp[i][j] = dp[i][j-1] 前一个字符匹配的情况
        // dp[i][j] = dp[i][j-2] 前面没有匹配的情况
        // 比如 s = aac p = aac*
        // dp[i-1][j] 表示s串的前i个字符和p串中j位置的字符都匹配，比如
        // dp[i][j-1] 表示s串的第i个字符和去掉*号后的p串是否匹配，比如 s = aab 和 p = aab*
        // dp[i][j-2] 表示p去掉*号以及*号前面的字符后，s和p是否还能进行匹配，比如 s = aab 和 p = aabb* 即使去掉b*，前面的aab还是可以匹配的

        // 三、初始化dp数组
        // s为空，p为空，能匹配上
        dp[0][0] = true;

        // 四、确定遍历顺序

        // 五、推导dp数组
        for (int i = 0; i <= sLen ; i++) {
            for (int j = 1; j <= pLen ; j++) { // p不能为空串，所以下标从1开始
                // p的第j个字符为 *
                if (p.charAt(j - 1) == '*') {
                    // 是*号，分情况讨论
                    // 判断*号前面的字符是否匹配

                    // *前面的字符不匹配
                    dp[i][j] = dp[i][j-2];

                    if (i != 0 && ( s.charAt(i-1) == p.charAt(j-2) || p.charAt(j-2) == '.') ) {
                        // *前面的字符匹配
                        dp[i][j] = dp[i-1][j];
                    }
                } else {
                    // 非*号可能的情况为:
                    // 匹配： s.charAt(i) == p.charAt(j)，不匹配： s.charAt(i) != p.charAt(j)
                    // 匹配的情况又分为:
                    // s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'
                    if ( i != 0 && ( s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.') ) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }

        return dp[sLen][pLen];
    }

}
