package linkedlist;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 */
public class T19_删除链表的倒数第N个节点 {

    // 先算出长度，再正序找
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int len = 0;
        int pos = 0;
        ListNode curr = head;
        while (curr != null) {
            ++len;
            curr = curr.next;
        }
        if (n == len) {
            return head.next;
        }
        curr = head;
        while (curr != null) {
            if (pos == len - n - 1) {
                curr.next = curr.next.next;
                break;
            }
            ++pos;
            curr = curr.next;
        }
        return head;
    }

    // 快慢指针，第一次快指针走n步，慢指针走1步，后面每次都走1步
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode fast = head;
        ListNode slow = head;
        // 快指针先走
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        // 一下子就走到头，说明删除的是头节点
        if (fast == null) {
            return head.next;
        } else if (fast.next == null) {
            head.next = head.next.next;
            return head;
        } else {
            slow = slow.next;
        }
        // 找到被删除节点的前一个节点
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    /**
     * 【很巧妙，但是效率没有任何提升】
     * 快慢指针，第一次快指针走n+1步，拉开2个指针的差距（隔了n个结点），后面2个指针每次都走1步，
     * 这样当快指针到末尾的时候（null），慢指针在倒数第n个位置的前一个节点
     * 使用头结点（哑结点）来保证链表中至少有2个节点
     */
    public ListNode removeNthFromEnd3(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        // 快指针先走n+1步
        for (int i = 0; i < n + 1; i++) {
            fast = fast.next;
        }
        // 找到被删除节点的前一个节点
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    // 测试第1种写法
    @Test
    public void test01() {
        ListNode head = new ListNode(0);
        LinkedList list = new LinkedList(head);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        ListNode result = new T19_删除链表的倒数第N个节点().removeNthFromEnd(head, 1);
        System.out.println(result);
    }

    // 测试第2种写法
    @Test
    public void test02() {
        ListNode head = new ListNode(5);
        LinkedList list = new LinkedList(head);
        list.add(4);
        list.add(3);
        list.add(2);
        list.add(1);
        ListNode result = new T19_删除链表的倒数第N个节点().removeNthFromEnd2(head, 4);
        System.out.println(result);
    }

    // 测试第3种写法
    @Test
    public void test03() {
        ListNode head = new ListNode(5);
        LinkedList list = new LinkedList(head);
        list.add(4);
        // list.add(3);
        // list.add(2);
        // list.add(1);
        ListNode result = new T19_删除链表的倒数第N个节点().removeNthFromEnd3(head, 2);
        System.out.println(result);
    }
}
