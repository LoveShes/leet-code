package linkedlist;

/**
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 */
public class T160_相交链表 {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode p = headA;
        ListNode q = headB;
        while (p != null || q != null) {
            if (p == q) {
                return p;
            }
            p = p==null ? headB : p.next;
            q = q==null ? headA : q.next;
        }
        return null;
    }

    // 更精简的写法
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode p = headA;
        ListNode q = headB;
        while (p != q) {
            p = p==null ? headB : p.next;
            q = q==null ? headA : q.next;
        }
        return p;
    }
}
