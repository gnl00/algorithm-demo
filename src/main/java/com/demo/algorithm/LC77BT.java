package com.demo.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。你可以按 任何顺序 返回答案
 *
 * 输入：n = 4, k = 2
 * 输出：
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 *
 * 输入：n = 1, k = 1
 * 输出：[[1]]
 *
 * 提示：
 *
 * 1 <= n <= 20
 * 1 <= k <= n
 *
 * @author lgn
 * @since 2022/1/18 14:52
 */

public class LC77BT {
    public static void main(String[] args) {
        LC77BT lc77BT = new LC77BT();
        System.out.println(lc77BT.combine(4, 2));
    }

    /**
     * 优化解法
     * https://programmercarl.com/0077.%E7%BB%84%E5%90%88%E4%BC%98%E5%8C%96.html
     */
    public void backTracking2(int n, int k, int startIndex) {
        if (currentVal.size() == k) {
            retVal.add(new ArrayList<>(currentVal));
            return;
        }

        /**
         * 可以优化的地方就在每一层for循环中的结束位置
         * 如果for循环选择的起始位置之后的元素个数 已经不足 我们需要的元素个数了，那么就没有必要搜索了
         * 当前起始位置 = i
         * 总元素个数 = n
         * 当前已经遍历得到的元素个数 = currentVal.size()
         * 还需要的元素个数 = k - currentVal.size()
         * 需要满足 i <= n - (k - currentVal.size()) + 1
         */

        for (int i = startIndex; i <= n ; i++) {
            currentVal.add(i);
            backTracking(n, k, i + 1);
            currentVal.remove(currentVal.size() - 1);
        }
    }

    // 1、回溯的返回值以及参数
    // startIndex 记录下一层遍历的开始位置

    // 存放符合条件结果的集合
    private List<List<Integer>> retVal = new ArrayList<>();
    // 存放符合条件结果
    private List<Integer> currentVal = new ArrayList<>();

    /**
     * 2、回溯终止条件
     * 当currentVal.size() == k，说明找到满足条件的大小为k的集合了，将currentVal保存，结束本次递归
     * if(currentVal.size() == k) {
     *     retVal.add(currentVal);
     *     return;
     * }
     */

    /**
     * 3、遍历过程，for从startIndex开始横向，递归纵向
     * for(int i = startIndex; i<=n;i++) {
     *     currentVal.add(i); 处理节点
     *     combine(n, k, i + 1); 递归
     *     currentVal.remove(i); 回溯
     * }
     * combine 函数通过不断向深处递归遍历，总会遇到叶子节点，当遇到叶子节点时，就返回
     */

    public void backTracking(int n, int k, int startIndex) {
        if (currentVal.size() == k) {
            retVal.add(new ArrayList<>(currentVal));
            return;
        }

        for (int i = startIndex; i <= n ; i++) {
            currentVal.add(i);
            backTracking(n, k, i + 1);
            currentVal.remove(currentVal.size() - 1);
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        // 返回范围 [1, n]
        backTracking(n, k, 1);
        return retVal;
    }
}
