package linkedlist;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/rotate-list/
 */
public class T61_旋转链表 {

    /**
     * 先找到倒数第k个节点，再把该节点的末尾接到头上
     */
    public ListNode rotateRight(ListNode head, int k) {
        // 计算总长度
        int len = 0;
        ListNode curr = head;
        while (curr != null) {
            ++len;
            curr = curr.next;
        }
        if (len == 0 || k % len == 0) {
            return head;
        }
        // 快慢指针找到倒数第k个节点的前一个节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        for (int i = 0; i < k%len; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        ListNode newHead = slow.next;
        slow.next = null;
        fast.next = head;
        return newHead;
    }

    /**
     * 【不推荐使用该方法】-> 【改进】先计算长度，然后k=k%n，再循环k次。不过相比于方法1意义不大，而且时间复杂度还大
     * 每次旋转一位，旋转k次【k过大时会超时】
     */
    @Deprecated
    public ListNode rotateRight2(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        for (int i = 0; i < k; i++) {
            ListNode curr = dummy;
            while (curr.next != null && curr.next.next != null) {
                curr = curr.next;
            }
            ListNode newHead = curr.next;
            curr.next = null;
            newHead.next = dummy.next;
            dummy.next = newHead;
        }
        return dummy.next;
    }

    @Test
    public void test01() {
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        // list.add(4);
        // list.add(5);

        ListNode result = rotateRight(list.getHead(), 4);
        System.out.println(result);
    }

    @Test
    public void test02() {
        LinkedList list = new LinkedList();
        list.add(1);
        // list.add(2);
        // list.add(3);
        // list.add(4);
        // list.add(5);

        ListNode result = rotateRight2(list.getHead(), 2);
        System.out.println(result);
    }
}
