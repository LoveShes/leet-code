package linkedlist;

/**
 * https://leetcode-cn.com/problems/palindrome-linked-list/
 */
public class T234_回文链表 {

    // TODO 快慢指针，反转前半部分
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode l = slow;
        ListNode r = slow.next;
        if (fast == null) {
            r = slow;
        }
        ListNode newHead = null;
        ListNode tmp;
        while (head != l) {
            tmp = head.next;
            head.next = null;
            newHead = head.next;
            head = tmp;
            head = head.next;
        }
        while (newHead != null) {
            if (newHead.val != r.val) {
                return false;
            }
            newHead = newHead.next;
            r = r.next;
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

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        LinkedList list = new LinkedList(head);
        list.add(new ListNode(2));
        list.add(new ListNode(2));
        list.add(new ListNode(1));
        System.out.println(list.toString());

        boolean flag = new T234_回文链表().isPalindrome2(head);
        System.out.println(flag);
    }
}
