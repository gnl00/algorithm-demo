package com.demo.algorithm;

/**
 * 给你一个字符串 s，找到 s 中最长的回文子串
 *
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 *
 * 输入：s = "cbbd"
 * 输出："bb"
 *
 * 输入：s = "a"
 * 输出："a"
 *
 * 输入：s = "ac"
 * 输出："a"
 *
 * @author lgn
 * @since 2022/1/4 17:00
 */

public class LC05 {
    public static void main(String[] args) {

        String s = "cbbd";
        String s1 = longestPalindrome4(s);
        System.out.println(s1);
    }

    public static int maxLen = 0;
    public static int left = 0;
    public static int right = 0;

    // 中心扩展双指针解析
    public static String longestPalindrome4(String s) {
        /**
         * 首先确定回文串，就是找中心然后向两边扩散看是不是对称的
         *
         * 在遍历中心点的时候，要注意中心点有两种情况
         * 1、一个元素可以作为中心点
         * 2、两个元素也可以作为中心点
         */

        for (int i = 0; i < s.length(); i++) {
            // 以i为中心
            expend4(s, i, i, s.length());
            // 以i+1为中心
            expend4(s, i, i + 1, s.length());
        }

        String sub = "";
        char[] chars = s.toCharArray();
        for (int i = left; i < right - left + 1; i++) {
            sub += String.valueOf(chars[i]);
        }

        System.out.println(sub);

        return s.substring(left, right - left + 1);
    }

    public static void expend4(String s, int i, int j, int len){
        while (i >= 0 && j < len && s.charAt(i) == s.charAt(j)) {
            if (maxLen < j - i + 1) {
                left  = i;
                right = j;
                maxLen = right - left + 1;
            }
            i--;
            j++;
        }
    }

    // 动态规划解析
    public static String longestPalindrome3(String s) {
        int len = s.length();

        if (len < 2) {
            return s;
        }

        // 一、确定dp数组，以及下标含义
        // dp[i][j] 表示从下标i到下标j之间的字符是否是回文串
        boolean[][] dp = new boolean[len][len];

        /**
         * 二、确定递推公式
         * 情况 1 下标i与下标j相等，例如a，显然是回文
         * 情况 2 下标i与下标j相差为1，例如aa，是回文
         * 情况 3 下标i与下标j相差大于1，例如cabac，若是下标i与下标j字符相同，
         * 只需要关注在i和j之间的字符是否是回文即可 if(dp[i+1][j-1])
         *
         *         if (s.charAt(i) == s.charAt(j)) {
         *             if (j - i <= 1) { // 情况1和2
         *                 dp[i][j] = true;
         *             } else if (dp[i+1][j-1]) { // 情况3
         *                 dp[i][j] = true;
         *             }
         *         }
         *
         *         在得到[i,j]区间是否是回文子串的时候，直接保存最长回文子串的左边界和右边界
         *         if (dp[i][j] && j - i + 1 > maxLen) {
         *             maxLen = j - i + 1;
         *             left = i;
         *             right = j;
         *         }
         *
         */

        // 三、初始化数组
        // 每个字符本身都是一个回文如 dp[0][0] dp[1][1]
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        /**
         *
         * aba
         *     0    1     2
         * 0 true false false
         * 1 false true false
         * 2 false false true
         *
         *
         * cabac
         *
         *      0    1     2     3     4
         * 0  true false false false false
         * 1  false true false false false
         * 2  false false true false false
         * 3  false false false true false
         * 4  false false false false true
         *
         *
         * 以 aba 为例子：
         * 每个字符串本身显然都是回文，所以 dp[0][0] dp[1][1] dp[2][2] 都是 true
         */


        /**
         * 四、确定遍历顺序
         * 若此时s.charAt(i) = s.charAt(j) && j - 1 > 1 判断 dp[i+1][j-1] =? true
         *
         * 而 dp[i+1][j-1] 在 dp[i][j]的左下角
         * 如果是从上到下，从左到右遍历，那么会用到没有计算过的dp[i + 1][j - 1]，也就是根据不确定是不是回文的区间[i+1,j-1]，来判断了[i,j]是不是回文，那结果一定是不对的
         * 所以一定要从下到上，从左到右遍历，这样保证dp[i + 1][j - 1]都是经过计算的
         *
         *         for (int i = s.length(); i >= 0; i--) {
         *             for (int j = i; j < s.length(); j++) {
         *                 if (s.charAt(i) == s.charAt(j)) {
         *                     if (j - i <= 1) { // 情况1和2
         *                         dp[i][j] = true;
         *                     } else if (dp[i+1][j-1]) { // 情况3
         *                         dp[i][j] = true;
         *                     }
         *                 }
         *
         *                 // 在得到[i,j]区间是否是回文子串的时候，直接保存最长回文子串的左边界和右边界
         *                 if (dp[i][j] && j - i + 1 > maxLen) {
         *                     maxLen = j - i + 1;
         *                     left = i;
         *                     right = j;
         *                 }
         *             }
         *         }
         *
         */

        // 五、举例推导dp数组

        int maxLen = 0, left = 0, right = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i <= 1) { // 情况1和2，其实在经过初始化之后可以把相差小于1的条件去掉，因为此时相差小于1的情况只有一种，就是j == i，相差为0，初始化的时候已经考虑到了这一情况
                        dp[i][j] = true;
                    } else if (dp[i+1][j-1]) { // 情况3
                        dp[i][j] = true;
                    }
                }

                // 在得到[i,j]区间是否是回文子串的时候，直接保存最长回文子串的左边界和右边界
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    left = i;
                    right = j;
                }
            }
        }

        return s.substring(left, right - left + 1);
    }

    // 中心扩展
    // 如果两边的字母相同，继续扩展
    // 如果两边的字母不同，停止扩展，因为在这之后的子串都不是回文串了
    public static String longestPalindrome2(String s) {

        if (s == null || s.length() < 1) {
            return "";
        }

        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            int len1 = expand(s, i, i);
            int len2 = expand(s, i, i + 1);

            int len =  Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len-1)/2;
                end = i + len/2;
            }
        }

        return s.substring(start, end + 1);
    }

    public static int expand(String s, int left, int right) {

        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return right - left - 1;
    }

    // 动态规划 O(n^2)
    public static String longestPalindrome(String s) {

        int len = s.length();

        if (len < 2) {
            return s;
        }

        int maxLen = 1;

        // dp[i][j] 表示在位置i到位置j的字串是否是回文串
        boolean[][] dp = new boolean[len][len];

        // 初始化：所有长度为 1 的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] charArray = s.toCharArray();

        int begin = 0;

        // 递推开始
        // 先枚举子串长度
        for (int L = 2; L <= len; L++) {
            // 枚举左边界，左边界的上限设置可以宽松一些
            for (int i = 0; i < len; i++) {
                // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
                int j = L + i - 1;
                // 如果右边界越界，就可以退出当前循环
                if (j >= len) {
                    break;
                }

                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

}
