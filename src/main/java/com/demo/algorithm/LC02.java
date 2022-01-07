package com.demo.algorithm;

/**
 * 给你两个非空的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0开头
 *
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 *
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 *
 * l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 *
 * @author lgn
 * @since 2022/1/4 13:36
 */

public class LC02 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
         ListNode l12 = new ListNode(8);
         ListNode l13 = new ListNode(4);
         ListNode l14 = new ListNode(9);
         l1.next = l12;
//         l12.next = l13;
//         l13.next = l14;

        ListNode l2 = new ListNode(0);
        ListNode l21 = new ListNode(4);
        ListNode l22 = new ListNode(9);
//        ListNode l23 = new ListNode(9);
//        ListNode l24 = new ListNode(9);
//        ListNode l25 = new ListNode(9);
//        ListNode l26 = new ListNode(9);
//        ListNode l27 = new ListNode(9);
//        ListNode l28 = new ListNode(9);
//        ListNode l29 = new ListNode(9);
//        l2.next = l21;
//        l21.next = l22;
//        l22.next = l23;
//        l23.next = l24;
//        l24.next = l25;
//        l25.next = l26;
//        l26.next = l27;
//        l27.next = l28;
//        l28.next = l29;

        System.out.println(addTwoNumbers2(l1, l2));
    }

    public static ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
        ListNode root = new ListNode(0);
        ListNode cursor = root;
        int carry = 0;
        while(l1 != null || l2 != null || carry != 0) {
            int l1Val = l1 != null ? l1.val : 0;
            int l2Val = l2 != null ? l2.val : 0;
            int sumVal = l1Val + l2Val + carry;
            carry = sumVal / 10;

            ListNode sumNode = new ListNode(sumVal % 10);
            cursor.next = sumNode;
            cursor = sumNode;

            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }

        return root.next;
    }

    // 10000000000
    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {

        ListNode head = new ListNode();
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int val1 = l1 == null ? 0 : l1.val;
            int val2 = l2 == null ? 0 : l2.val;
            int currentSum = val1 + val2 + carry;
            carry = currentSum / 10;

            ListNode currentNode = new ListNode(currentSum % 10);
            insertFromTail(currentNode, head);

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        return head.next;
    }

    public static void insertFromTail(ListNode node, ListNode head) {
        ListNode tmp = head;
        while (true) {
            if (tmp.next == null) {
                tmp.next = node;
                break;
            }
            tmp = tmp.next;
        }
    }

    // O(n) val1 || val2 || sum 超出 long 的范围报错
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        int count = 0;
        long val1 = 0, val2 = 0;

        // O(max(m, n)) ==> O(n)
        while (l1 != null && l2 != null) {
            val1 += l1.val * Math.pow(10, count);
            val2 += l2.val * Math.pow(10, count);
            l1 = l1.next;
            l2 = l2.next;
            count++;
        }

        while (l1 != null) {
            val1 += l1.val * Math.pow(10, count);
            l1 = l1.next;
            count++;
        }

        while (l2 != null) {
            val2 += l2.val * Math.pow(10, count);
            l2 = l2.next;
            count++;
        }

        long sum = val1 + val2;

        if (sum == 0) {
            return new ListNode(0);
        }

        // O(n) 将数字逆序
//        double n = 0;
//        while (sum > 0) {
//            n = n * 10 + sum % 10;
//            sum /= 10;
//        }

        // 定义一个头节点
        ListNode head = new ListNode();
        ListNode tmp = head;

        // O(n) 求一个数字的每一位数
        while (sum > 0) {
            long nodeVal = sum % 10;

            ListNode currentNode = new ListNode((int)nodeVal);

            // 头插法
//            if (resList.next != null) {
//                tmpList.next = resList.next;
//            }
//            resList.next = tmpList;

            // 尾插法
            while (true) {

                if (tmp.next == null) {
                    tmp.next = currentNode;
                    break;
                }

                tmp = tmp.next;
            }


            sum /= 10;
        }

        return head.next;
    }
}

class ListNode {

     int val;

     ListNode next;

     ListNode() {}

     ListNode(int val) { this.val = val; }

     ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
