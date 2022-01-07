// 双指针扩展
let left = 0, right = 0, maxLen = 0
var longestPalindrome = function(s) {

    for (let i = 0; i < s.length; i++) {
        // 一个元素为中心
        expend(s, i, i, s.length)

        // 两个元素为中心
        expend(s, i, i+1, s.length)
    }
    return s.substr(left, right - left + 1)
};

const expend = (s, i, j, len) => {
    while (i >= 0 && j < len && s[i] === s[j]) {
        if (j - i + 1 > maxLen) {
            maxLen = j - i + 1
            left = i
            right = j
        }
        i--
        j++
    }
}

// 动态规划
var longestPalindrome2 = function(s) {

    if (s.length < 2) return s

    // 1、确定数组以及下标含义
    // dp[i][j] 表示下标i到j之间的字符是否是回文
    let dp = []

    // 2、确定递推公式
    // i == j
    // j - i = 1
    // j - i > 1

    // 3、初始化数组
    for (let i = 0; i < s.length; i++) {
        dp[i] = true
    }

    console.log(dp)

    // 4、确定遍历顺序

    // 5、举例推导数组

}

console.log(longestPalindrome2('cbbd'))