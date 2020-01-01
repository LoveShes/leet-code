package linkedlist;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 */
public class T24_两两交换链表中的节点 {

    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = dummy;
        ListNode next = curr.next;
        while (next != null && next.next != null) {
            curr.next = curr.next.next;
            next.next = curr.next.next;
            curr.next.next = next;
            curr = next;
            next = next.next;
        }
        return dummy.next;
    }

    @Test
    public void test01() {
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        ListNode result = new T24_两两交换链表中的节点().swapPairs(list.getHead());
        System.out.println(result);
    }
}
