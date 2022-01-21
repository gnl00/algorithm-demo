package com.demo.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 移除链表元素
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点。
 *
 * 输入：head = [1,2,6,3,4,5,6], val = 6
 * 输出：[1,2,3,4,5]
 *
 * 输入：head = [], val = 1
 * 输出：[]
 *
 * 输入：head = [7,7,7,7], val = 7
 * 输出：[]
 *
 * 提示：
 *
 * 列表中的节点数目在范围 [0, 10^4] 内
 * 1 <= Node.val <= 50
 * 0 <= val <= 50
 *
 * @author lgn
 * @since 2022/1/19 13:52
 */

public class LC203_LinkedList {
    public static void main(String[] args) {
        LC203_LinkedList lc203 = new LC203_LinkedList();
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(6);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);

        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        System.out.println(lc203.removeElements3(head,6));
    }

    public ListNode removeElements3(ListNode head, int val) {

        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;

        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }

        return dummy.next;
    }

    /**
     * 虚拟头节点解法
     * @param head
     * @param val
     * @return ListNode
     */
    public ListNode removeElements2(ListNode head, int val) {
        // 因为删除可能涉及到头节点，所以设置虚拟头节点节点，统一操作
        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy;
        ListNode cur = pre.next;

        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return dummy.next;
    }

    public ListNode removeElements(ListNode head, int val) {

        ListNode node = head;

        List<Integer> list = new ArrayList<>();

        while (node != null) {
            list.add(node.val);
            node = node.next;
        }

        while (list.contains(val)) {
            int i = list.indexOf(val);
            list.remove(i);
        }

        head = new ListNode(0);
        for (int i = list.size() - 1; i >= 0; i--) {
            ListNode currentNode = new ListNode(list.get(i));
            currentNode.next = head.next;
            head.next = currentNode;
        }

        return head.next;
    }
}
