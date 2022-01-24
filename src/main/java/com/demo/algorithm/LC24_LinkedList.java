package com.demo.algorithm;

/**
 * 两两交换链表中的节点
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 *
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 *
 * 输入：head = []
 * 输出：[]
 *
 * 输入：head = [1]
 * 输出：[1]
 *
 * 提示：
 * 链表中节点的数目在范围 [0, 100] 内
 * 0 <= Node.val <= 100
 *
 * @author lgn
 * @since 2022/1/24 13:33
 */

public class LC24_LinkedList {
    public static void main(String[] args) {
        LC24_LinkedList lc24 = new LC24_LinkedList();

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        System.out.println(lc24.swapPairs(node1));
    }

    public ListNode swapPairs(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        // 获取当前节点的下一个节点
        ListNode next = head.next;
        // 进行递归
        ListNode node = swapPairs(next.next);
        next.next = head;
        head.next = node;

        return next;
    }
}
