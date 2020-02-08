package linkedlist;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/palindrome-linked-list/
 */
public class T234_回文链表 {

    // 快慢指针，反转前半部分
    public boolean isPalindrome(ListNode head) {
        // 找到中点
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode mid = slow;

        // 反转前半链表
        ListNode tmp = null;
        ListNode newHead = null;
        while (head != mid) {
            tmp = head.next;
            head.next = newHead;
            newHead = head;
            head = tmp;
        }
        if (fast != null) {
            mid = mid.next;
        }
        // 比较
        while (newHead != null) {
            if (newHead.val != mid.val) {
                return false;
            }
            newHead = newHead.next;
            mid = mid.next;
        }
        return true;
    }

    // 直接反转链表，再进行比较
    public boolean isPalindrome2(ListNode head) {
        ListNode newHead = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode tmp = curr.next;
            ListNode add = new ListNode(curr.val);
            add.next = newHead;
            newHead = add;
            curr = tmp;
        }
        while (newHead != null) {
            if (newHead.val != head.val) {
                return false;
            }
            newHead = newHead.next;
            head = head.next;
        }
        return true;
    }

    @Test
    public void test() {
        ListNode head = new ListNode(1);
        LinkedList list = new LinkedList(head);
        list.add(new ListNode(2));
        list.add(new ListNode(3));
        list.add(new ListNode(3));
        list.add(new ListNode(2));
        list.add(new ListNode(1));
        System.out.println(list.toString());

        boolean flag = isPalindrome(head);
        System.out.println(flag);
    }
}
