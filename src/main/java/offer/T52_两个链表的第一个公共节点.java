package offer;

import offer.tools.linkedlist.ListNode;
import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/
 */
public class T52_两个链表的第一个公共节点 {
    /**
     * 【使用栈】
     * 如果有交汇，则尾部一段一定是重合的，然后从尾部一直往前得到重合点
     * 执行用时 :3 ms, 在所有 Java 提交中击败了17.77% 的用户
     * 内存消耗 :43.4 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        Stack<ListNode> stackA = new Stack<>();
        Stack<ListNode> stackB = new Stack<>();
        while (headA != null) {
            stackA.push(headA);
            headA = headA.next;
        }
        while (headB != null) {
            stackB.push(headB);
            headB = headB.next;
        }
        ListNode ans = null;
        while (!stackA.isEmpty() && !stackB.isEmpty() && stackA.peek() == stackB.peek()) {
            ans = stackA.pop();
            stackB.pop();
        }
        return ans;
    }

    /**
     * 【先计算出长度(对齐)，然后再比较】
     * 执行用时 :1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :43 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        int lenA = getLength(headA);
        int lenB = getLength(headB);
        if (lenA > lenB) { // headA先走
            for (int i = 0; i < lenA - lenB; i++) {
                headA = headA.next;
            }
        } else { // headB先走
            for (int i = 0; i < lenB - lenA; i++) {
                headB = headB.next;
            }
        }
        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }

    private int getLength(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }

    /**
     * 【2次遍历】
     * headA: 10->11->12->100->200
     * headB:     20->21->100->200
     * A走完headA后接着走headB，即 10->11->12->100->200-|->20->21->100->200
     * B走完headB后接着走headA，即 20->21->100->200-|->10->11->12->100->200
     * 可以发现第2次相遇的时候一定是第一个交汇点
     * 为什么第2次遍历可以找到交汇点，简单证明如下：
     * 设headA的长度为a，headB的长度为b，最后交汇的长度为x，headA的独立长度为a-x，headB的独立长度为b-x
     * 有a+(b-x)==b+(a-x)，即走过这段距离后，一定是同时到达交汇点
     * <p>
     * 执行用时 :1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :42.9 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode p = headA;
        ListNode q = headB;
        while (p != q) {
            p = p == null ? headB : p.next;
            q = q == null ? headA : q.next;
        }
        return p;
    }

    @Test
    public void test() {
        ListNode headA = new ListNode(10);
        ListNode headB = new ListNode(20);
        ListNode common1 = new ListNode(100);
        ListNode common2 = new ListNode(200);
        headA.next = new ListNode(11);
        headA.next.next = new ListNode(12);
        headA.next.next.next = common1;
        headA.next.next.next.next = common2;
        headB.next = new ListNode(21);
        headB.next.next = common1;
        headB.next.next.next = common2;
        // System.out.println(headA);
        // System.out.println(headB);
        assert getIntersectionNode(headA, headB) == common1;
        assert getIntersectionNode(headA, headA) == headA;
        assert getIntersectionNode(headA, common1) == common1;
        assert getIntersectionNode(headA, new ListNode(123)) == null;

        assert getIntersectionNode2(headA, headB) == common1;
        assert getIntersectionNode2(headA, headA) == headA;
        assert getIntersectionNode2(headA, common1) == common1;
        assert getIntersectionNode2(headA, new ListNode(123)) == null;

        assert getIntersectionNode3(headA, headB) == common1;
        assert getIntersectionNode3(headA, headA) == headA;
        assert getIntersectionNode3(headA, common1) == common1;
        assert getIntersectionNode3(headA, new ListNode(123)) == null;

        System.out.println(headA);
        System.out.println(headB);
    }
}
