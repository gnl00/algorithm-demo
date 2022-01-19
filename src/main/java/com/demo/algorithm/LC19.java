package com.demo.algorithm;

/**
 * 删除链表的倒数第 N 个结点
 * 给你一个链表，删除链表的倒数第n个结点，并且返回链表的头结点。
 *
 * head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 *
 * 输入：head = [1], n = 1
 * 输出：[]
 *
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 *
 * 提示：
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 *
 * @author lgn
 * @since 2022/1/19 10:17
 */

public class LC19 {
    public static void main(String[] args) {
        LC19 lc19 = new LC19();
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        System.out.println(lc19.removeNthFromEnd2(head, 2));
    }

    /**
     * 快慢指针解法
     * 如果要删除倒数第 n 个节点，让 fast 移动 n 步，然后让 fast 和 slow 同时移动，直到 fast 指向链表末尾。删掉 slow 所指向的节点即可
     * @param head
     * @param n
     * @return ListNode
     * @see <a>https://programmercarl.com/0019.%E5%88%A0%E9%99%A4%E9%93%BE%E8%A1%A8%E7%9A%84%E5%80%92%E6%95%B0%E7%AC%ACN%E4%B8%AA%E8%8A%82%E7%82%B9.html<a/>
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {

        /**
         * 1、使用虚拟头节点，这样方便处理删除实际头结点的逻辑
         * 2、定义 fast 指针和 slow 指针，初始值为虚拟头结点
         * 3、fast 首先走 n 步 ，为什么是 n 呢，因为只有这样同时移动的时候 slow 才能指向删除节点的上一个节点（方便做删除操作）
         * 4、fast 和 slow 同时移动，直到 fast 指向末尾，删除 slow 指向的下一个节点
         *
         * 例子：
         * head：1-2-3-4-5
         * n: 2
         *
         * step1:
         * fast
         *  |
         *  1  -  2  -  3  -  4  -  5
         * slow
         *
         * step2: fast 先移动 n
         *             fast
         *              |
         *  1  -  2  -  3  -  4  -  5
         *  |
         * slow
         *
         * step2: fast 和 slow 一起向前移动1，直到 fast 指向 null 时，删除slow 指向的节点
         *                    fast
         *                    |
         *  1  -  2  -  3  -  4  -  5  -  null
         *        |
         *      slow
         *
         *                         fast
         *                          |
         *  1  -  2  -  3  -  4  -  5  -  null
         *              |
         *            slow
         *
         *                                fast
         *                                 |
         *  1  -  2  -  3  -  4  -  5  -  null
         *                    |
         *                  slow
         */

        ListNode dummy = new ListNode(0, head);
        ListNode fast = dummy;
        ListNode slow = dummy;

        // fast先移动 n+1
        while (n > 0) {
            fast = fast.next;
            n--;
        }

        // 记录slow的上一个节点
        ListNode slowPre = null;
        while (fast != null) {
            slowPre = slow;
            slow = slow.next;
            fast = fast.next;
        }

        // 快指针走到null，此时slow节点就是需要删除的节点
        slowPre.next = slow.next;
        // 释放节点
        slow.next = null;

        return dummy.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {

        /**
         * 将链表逆序，再利用循环从逆序链表中删除对应节点，删除完再次逆序
         */
        ListNode revert = revert(head);
        ListNode dummy = new ListNode(0, revert);
        ListNode cur = dummy;

        int count = 1;
        while (cur.next != null) {
            if (count == n) {
                cur.next = cur.next.next;
                break;
            } else {
                cur = cur.next;
            }
            count++;
        }
        ListNode retVal = dummy.next == null ? null : revert(dummy.next);
        return retVal;
    }

    public ListNode revert(ListNode head) {

        ListNode p = head;
        ListNode q = head.next;
        // 需要将第一个节点的next置空，否则会出现一个环
        p.next = null;
        while (q != null) {
            ListNode t = q.next;
            q.next = p;
            p = q;
            q = t;
        }
        return p;
    }
}
