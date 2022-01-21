package com.demo.algorithm;

/**
 * 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 *
 * 输入：l1 = [], l2 = []
 * 输出：[]
 *
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 *
 * 提示：
 * 两个链表的节点数目范围是 [0, 50]
 * -100 <= Node.val <= 100
 * l1 和 l2 均按 非递减顺序 排列
 *
 * @author lgn
 * @since 2022/1/20 9:55
 */

public class LC21_LinkedList {
    public static void main(String[] args) {
        LC21_LinkedList lc21 = new LC21_LinkedList();

        ListNode list1 = new ListNode(1);
        ListNode node12 = new ListNode(3);
        ListNode node13 = new ListNode(4);
        list1.next = node12;
        node12.next = node13;

        ListNode list2 = new ListNode(2);
        ListNode node22 = new ListNode(4);
        ListNode node23 = new ListNode(6);
        list2.next = node22;
        node22.next = node23;

        // System.out.println(lc21.mergeTwoLists(list1, list2));
        System.out.println(lc21.mergeTwoLists2(list1, list2));
    }

    public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }

        if (list2 == null) {
            return list1;
        }

        ListNode node = list1.val < list2.val ? list1 : list2;
        node.next = mergeTwoLists2(node.next, list1.val >= list2.val ? list1 : list2);
        return node;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        if (list1 == null) {
            return list2;
        }

        if (list2 == null) {
            return list1;
        }

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }

        // 任一个不为空，直接加在后面
        if (list1 == null) {
            cur.next = list2;
        }

        if (list2 == null) {
            cur.next = list1;
        }

        return dummy.next;
    }
}
