package linkedlist;

/**
 * https://leetcode-cn.com/problems/middle-of-the-linked-list/
 */
public class T876_链表的中间结点 {

    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
