package linkedlist;

public class LinkedList {

    private int size;
    private ListNode head;

    public LinkedList() {
    }

    public LinkedList(ListNode head) {
        this.head = head;
        ++size;
    }

    public void add(ListNode node) {
        if (head == null) {
            head = node;
        } else {
            ListNode curr = head;
            for (int i = 0; i < size-1; i++) {
                curr = curr.next;
            }
            curr.next = node;
        }
        ++size;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        ListNode node = head;
        for (int i = 0; i < size; i++) {
            if (i == 0) {
                string.append(node.val);
            } else {
                string.append(" -> " + node.val);
            }
            node = node.next;
        }
        return string.toString();
    }
}
