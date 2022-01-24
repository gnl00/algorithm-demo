package com.demo.algorithm;

/**
 * K 个一组翻转链表
 *
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 *
 * 如果节点总数不是k的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 进阶：
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 *
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 *
 * 输入：head = [1,2,3,4,5], k = 1
 * 输出：[1,2,3,4,5]
 *
 * 输入：head = [1], k = 1
 * 输出：[1]
 *
 * 提示：
 *
 * 列表中节点的数量在范围 sz 内
 * 1 <= sz <= 5000
 * 0 <= Node.val <= 1000
 * 1 <= k <= sz
 *
 * @author lgn
 * @since 2022/1/24 15:21
 */

public class LC25_LinkedList {
    public static void main(String[] args) {

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        LC25_LinkedList lc25 = new LC25_LinkedList();
        System.out.println(lc25.reverseKGroup(node1, 2));
    }

    public ListNode reverseKGroup(ListNode head, int k) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;
        ListNode cur = dummy.next;
        ListNode next = null;

        int len = 0;// 保存链表长
        while (head != null) {
            len ++;
            head = head.next;
        }

        for (int i = 0; i < len / k; i++) {
            for (int j = 0; j < k - 1; j++) {
                next = cur.next;
                cur.next = next.next;
                next.next = prev.next;
                prev.next = next;
            }
            prev = cur;
            cur = prev.next;
        }

        return dummy.next;
    }
}
