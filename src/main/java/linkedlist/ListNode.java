package linkedlist;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        StringBuilder sting = new StringBuilder();
        ListNode node = this;
        while (node != null) {
            sting.append(node.val);
            if (node.next != null) {
                sting.append(" -> ");
            }
            node = node.next;
        }
        return sting.toString();
    }
}
