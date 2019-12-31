package linkedlist;

/**
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/
 */
public class T21_合并两个有序链表 {

    // 拼接原有节点
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode curr = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        if (l1 == null) {
            curr.next = l2;
        } else {
            curr.next = l1;
        }
        return head.next;
    }

    // 创建新节点
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode curr = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                curr.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            curr = curr.next;
        }
        if (l1 == null) {
            while (l2 != null) {
                curr.next = new ListNode(l2.val);
                l2 = l2.next;
                curr = curr.next;
            }
        } else {
            while (l1 != null) {
                curr.next = new ListNode(l1.val);
                l1 = l1.next;
                curr = curr.next;
            }
        }
        return head.next;
    }
}
