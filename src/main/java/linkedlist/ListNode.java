package linkedlist;

public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

    public static String toString(ListNode head) {
        StringBuilder sting = new StringBuilder();
        while (head != null) {
            sting.append(head.val);
            if (head.next != null) {
                sting.append(" -> ");
            }
            head = head.next;
        }
        return sting.toString();
    }
}
