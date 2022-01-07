package com.demo.algorithm;


/**
 * 有一棵特殊的苹果树，一连 n 天，每天都可以长出若干个苹果。
 * 在第 i 天，树上会长出 apples[i] 个苹果，这些苹果将会在 days[i] 天后（也就是说，第 i + days[i] 天时）腐烂，变得无法食用。
 * 也可能有那么几天，树上不会长出新的苹果，此时用 apples[i] == 0 且 days[i] == 0 表示。
 * 你打算每天 最多 吃一个苹果来保证营养均衡。注意，你可以在这 n 天之后继续吃苹果。
 * 给你两个长度为 n 的整数数组 days 和 apples ，返回你可以吃掉的苹果的最大数目。
 *
 * apples = [1, 2, 3, 5, 2], days = [3, 2, 1, 4, 2]
 *
 * @author lgn
 * @since 2021/12/24 10:33
 */

public class Demo2Greed {
    public static void main(String[] args) {
        int[] apples = {1, 2, 3, 5, 2};
        int[] days = {3, 2, 1, 4, 2};

        System.out.println(eatApple(apples, days));
    }

    /**
     * 贪心 + 优先队列
     *
     * 为了将吃苹果的数目最大化，应该使用贪心策略，
     * 在尚未腐烂的苹果中优先选择腐烂日期最早的苹果.
     * 为了得到腐烂日期最早的苹果，可以使用优先队列存储每个苹果的腐烂日期，
     * 优先队列中最小的元素（即最早的腐烂日期）会最先被取出
     *
     */
    public static int eatApple(int[] apples, int[] days) {

        int count = 0;


        for (int i = 0; i < apples.length; i++) {

        }

        return 0;
    }

    public static int eatApple1(int[] apples, int[] days) {

        int count = 0;

        int[] dp = new int[apples.length];

        for (int i = apples.length - 1; i >= 0; i--) {
            for (int j = i + days[i] - 1; j > i && apples[i] > 0; j--) {
            }
        }

        return 0;
    }
}
