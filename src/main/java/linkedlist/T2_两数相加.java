package linkedlist;

/**
 * https://leetcode-cn.com/problems/add-two-numbers/
 */
public class T2_两数相加 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode curr = head;
        int carry = 0;
        while (l1 != null && l2 != null) {
            int val = l1.val + l2.val + carry;
            curr.next = new ListNode(val%10);
            curr = curr.next;
            carry = val / 10;
            l1 = l1.next;
            l2 = l2.next;
        }
        if (l1 == null) {
            while (l2 != null) {
                int val = l2.val + carry;
                curr.next = new ListNode(val%10);
                carry = val / 10;
                curr = curr.next;
                l2 = l2.next;
            }
        } else if (l2 == null) {
            while (l1 != null) {
                int val = l1.val + carry;
                curr.next = new ListNode(val%10);
                carry = val / 10;
                curr = curr.next;
                l1 = l1.next;
            }
        }
        if (carry == 1) {
            curr.next = new ListNode(1);
        }
        return head.next;
    }

    // 尝试简化
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode curr = head;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int val = carry;
            if (l1 != null) {
                val += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                val += l2.val;
                l2 = l2.next;
            }
            curr.next = new ListNode(val%10);
            curr = curr.next;
            carry = val / 10;
        }
        if (carry == 1) {
            curr.next = new ListNode(1);
        }
        return head.next;
    }
}
