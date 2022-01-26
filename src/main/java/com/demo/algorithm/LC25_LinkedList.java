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
        System.out.println(lc25.reverseKGroup(node1, 3));
    }

    public ListNode reverseKGroup(ListNode head, int k) {

        // 特殊情况直接返回
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0, head);

        ListNode start = dummy;
        ListNode tail = dummy.next;

        while (tail != null) {

            // 判断子链表是否满足反转条件，即子链表长为k
            int count = 0;
            while (count < k && tail != null) {
                tail = tail.next;
                count++;
            }

            // count 小于 k，tail 为 null，
            if (count < k) {
                break;
            }

            // 保存下一次的头节点
            ListNode node = start.next;
            reverse(start, tail);
            start = node;
        }
        return dummy.next;
    }

    /**
     * 反转start到tail之间的元素
     */
    public void reverse(ListNode start, ListNode tail){
        if (start == tail || start.next == tail) {
            return;
        }

        ListNode head = start.next;
        ListNode cur = start.next.next;
        while (cur != tail && cur != null) {
            ListNode node = cur.next;
            cur.next = head;
            head = cur;
            cur = node;
        }

        start.next.next = tail;
        start.next = head;
    }
}
