package com.demo.algorithm;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 合并K个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 *
 * 输入：lists = []
 * 输出：[]
 *
 * 输入：lists = [[]]
 * 输出：[]
 *
 * 提示：
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] 按 升序 排列
 * lists[i].length 的总和不超过 10^4
 *
 * @author lgn
 * @since 2022/1/20 14:39
 */

public class LC23_DAC_PQ {
    public static void main(String[] args) {
        LC23_DAC_PQ lc23 = new LC23_DAC_PQ();

        ListNode node11 = new ListNode(1);
        ListNode node12 = new ListNode(4);
        ListNode node13 = new ListNode(5);
        node11.next = node12;
        node12.next = node13;

        ListNode node21 = new ListNode(1);
        ListNode node22 = new ListNode(3);
        ListNode node23 = new ListNode(4);
        node21.next = node22;
        node22.next = node23;

        ListNode node31 = new ListNode(2);
        ListNode node32 = new ListNode(6);
        node31.next = node32;

        ListNode[] lists = {
                node11,
                node21,
                node31,
        };

        System.out.println(lc23.mergeKLists2(lists));
    }

    /**
     * 分治法
     * 将 k 个链表两两配对并将同一对中的链表合并
     * 第一轮合并以后， k 个链表被合并成了 k/2个链表，平均长度为 2n/k 然后是 k/4 个链表，k/8 个链表等等
     * 重复这一过程，直到得到了最终的有序链表
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }

        return split(lists, 0, lists.length - 1);
    }

    public ListNode split(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }

        if (left > right) {
            return null;
        }

        int mid = (left + right) / 2;

        return mergeTwoLists(split(lists, left, mid), split(lists, mid + 1, right));
    }

    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a == null ? b : a;
        }

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        ListNode curA = a;
        ListNode curB =b;

        while (curA != null && curB != null) {
            if (curA.val < curB.val) {
                cur.next = curA;
                curA = curA.next;
            } else {
                cur.next = curB;
                curB = curB.next;
            }
            cur = cur.next;
        }

        if (curA != null) {
            cur.next = curA;
        }

        if (curB != null) {
            cur.next = curB;
        }

        return dummy.next;
    }

    /**
     * 优先队列
     * 将所有的链表放入优先队列，然后出队头节点最小的链表，挂上cur，然后将后面的元素入队，循环以上步骤，直到队列为空
     */
    public ListNode mergeKLists(ListNode[] lists) {

        if (lists.length == 0) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;


        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        for (ListNode list : lists) {
            if (list == null) {
                continue;
            }
            pq.add(list);
        }

        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            cur.next = node;

            cur = cur.next;
            if (node.next != null) {
                pq.add(node.next);
            }
        }

        return dummy.next;
    }

}
