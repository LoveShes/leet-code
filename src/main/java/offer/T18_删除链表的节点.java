package offer;

import offer.linkedlist.LinkedList;
import offer.linkedlist.ListNode;
import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof/
 */
public class T18_删除链表的节点 {
    /**
     * 【哑结点】
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :38.6 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public ListNode deleteNode(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = dummy;
        while (curr != null && curr.next != null) {
            if (curr.next.val == val) {
                // 删除
                curr.next = curr.next.next;
                break;
            }
            curr = curr.next;
        }
        return dummy.next;
    }

    @Test
    public void test() {
        ListNode root = new ListNode(1);
        LinkedList list = new LinkedList(root);
        list.add(new ListNode(2));
        list.add(new ListNode(3));
        list.add(new ListNode(4));
        list.add(new ListNode(5));

        System.out.println(deleteNode(root, 12));
        
        System.out.println(deleteNode(root, 3));

        System.out.println(deleteNode(root, 5));

        System.out.println(deleteNode(root, 1));

    }
}
