package linkedlist;

/**
 * https://leetcode-cn.com/problems/reverse-linked-list/
 */
public class T206_反转链表 {

    // 递归
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    // 迭代，会对原节点进行修改
    public ListNode reverseList2(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode tmp = head.next;
            head.next = newHead;
            newHead = head;
            head = tmp;
        }
        return newHead;
    }

    // 迭代，不会对原节点进行修改
    public ListNode reverseList3(ListNode head) {
        ListNode newHead = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode tmp = curr.next;
            ListNode add = new ListNode(curr.val);
            add.next = newHead;
            newHead = add;
            curr = tmp;
        }
        return newHead;
    }
}
