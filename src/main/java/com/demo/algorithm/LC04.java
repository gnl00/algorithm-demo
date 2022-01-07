package com.demo.algorithm;

/**
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组nums1 和nums2。请你找出并返回这两个正序数组的 中位数 。
 *
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 *
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 *
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 *
 * @author lgn
 * @since 2021/12/24 11:16
 */

public class LC04 {
    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};

        System.out.println(getMiddle(nums1, nums2));
    }

    public static double getMiddle(int[] nums1, int[] nums2) {

        int[] arr = mergeArr(nums1, nums2);

        int len = arr.length;

        double res = 0;

        if (len % 2 != 0) {
            // 长度为奇数

            int mid = len / 2;

            res = arr[mid];

        } else {
            // 长度为偶数

            int mid1 = len / 2;
            int mid2 = mid1 - 1;

            double tmp1 = arr[mid1];
            double tmp2 = arr[mid2];

            res = (tmp1 + tmp2) / 2;

        }

        return res;
    }

    // O(max(m, n)) ===> O(n) 合并数组
    public static int[] mergeArr(int[] a, int[] b) {

        int[] arr = new int[a.length + b.length];

        int i = 0;
        int j = 0;
        int n = 0;

        while ( i < a.length && j < b.length) {
            if (a[i] <= b[j]) {
                arr[n++] = a[i++];
            } else {
                arr[n++] = b[j++];
            }
        }

        while (i < a.length) {
            arr[n++] = a[i++];
        }

        while (j < b.length) {
            arr[n++] = b[j++];
        }

        return arr;
    }
}
