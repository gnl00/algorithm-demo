package com.demo.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行Z 字形排列。
 * 比如输入字符串为 "PAYPALISHIRING"行数为 3 时，排列如下：
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 *
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 *
 * 行数为4时：
 * P     I     N
 * A   L S   I G
 * Y A   H R
 * P     I
 *
 * PINALSIGYAHRPI
 *
 * 请你实现这个将字符串进行指定行数变换的函数
 *
 *
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 *
 * 输入：s = "A", numRows = 1
 * 输出："A"
 *
 * @author lgn
 */

public class LC06 {
    public static void main(String[] args) {
        String s = "PAYPALISHIRING";

        System.out.println(convert2(s, 3));
    }

    // 按行访问
    public static String convert3(String s, int numRows) {
        return null;
    }

    // 按行排序 O(n)
    // 通过从左向右迭代字符串，可以确定字符位于 Z 字形图案中的哪一行
    // 使用 Math.min(numRows, s.length()) 来表示 Z 字形图案中的非空行
    // 左到右迭代 ss，将每个字符添加到合适的行。只有当我们向上移动到最上面的行或向下移动到最下面的行时，当前方向才会发生改变
    public static String convert2(String s, int numRows) {

        if (numRows == 1) {
            return s;
        }

        // 使用 Math.min(numRows, s.length()) 确定合适的非空行
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++) {
            rows.add(new StringBuilder());
        }

        // 当前行
        int curRow = 0;

        // 向下走还是向上走
        boolean goingDown = false;

        // 遍历字符串，将每一个字符设置到对应行
        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            // 如果移动到最下面行或者最上面行，转换方向
            if (curRow == 0 || curRow == numRows - 1) {
                goingDown = !goingDown;
            }
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) {
            ret.append(row);
        }

        return ret.toString();
    }

    // 暴力遍历，存到二维数组 O(n^2)
    public static String convert(String s, int numRows) {
        // PINALSIGYAHRPI
        // 想法1 奇数次开始隔 numRows 个，偶数次开始隔 numRows 个 行不通

        // 想法2 二维数组一个个设置
        // 只需要关注每一列从上到下然后再到右上角即可，以此为边界

        if (numRows == 1) {
            return s;
        }

        String str = "";

        char[][] arr = new char[numRows][s.length()];
        int index = 0, column = 0, row = 0;
        int len = s.length();
        while (index < len) {
            while (row < numRows && index < len) {
                arr[row][column] = s.charAt(index++);
                row++;
            }
            row--;

            while (row > 0 && index < len) {
                arr[--row][++column] = s.charAt(index++);
            }
            row++;
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.println(arr[i][j]);
            }
        }

        return str;
    }
}
