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

    public ListNode getHead() {
        return head;
    }

    // 直接添加节点
    public void add(ListNode node) {
        if (head == null) {
            head = node;
        } else {
            ListNode curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = node;
        }
        ++size;
    }

    // 添加元素
    public void add(Integer element) {
        add(new ListNode(element));
    }

    @Override
    public String toString() {
        ListNode node = head;
        StringBuilder string = new StringBuilder();
        string.append("size=").append(size);
        for (int i = 0; i < size; i++) {
            if (i == 0) {
                string.append(", ").append(node.val);
            } else {
                string.append(" -> ").append(node.val);
            }
            node = node.next;
        }
        return string.toString();
    }
}
