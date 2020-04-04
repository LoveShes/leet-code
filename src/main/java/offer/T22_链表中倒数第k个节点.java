package offer;

import offer.tools.linkedlist.LinkedList;
import offer.tools.linkedlist.ListNode;
import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/
 */
public class T22_链表中倒数第k个节点 {
    /**
     * 【双指针】
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :37.5 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null || k < 1) return null;

        ListNode fast = head;
        ListNode slow = head;
        while (k-- > 0 && fast != null) {
            fast = fast.next;
        }
        if (k >= 0) return null; // 超范围

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    @Test
    public void test() {
        ListNode root = new ListNode(1);
        LinkedList list = new LinkedList(root);
        for (int i = 2; i <= 5; i++) {
            list.add(new ListNode(i));
        }
        System.out.println(root);

        for (int i = 1; i <= 5; i++) {
            System.out.println(getKthFromEnd(root, i));
        }

        System.out.println(getKthFromEnd(root, 0));
        System.out.println(getKthFromEnd(root, 6));
        System.out.println(getKthFromEnd(root, 7));
    }

    @Test
    public void test2() {
        ListNode root = new ListNode(1);
        System.out.println(getKthFromEnd(root, 0));
        System.out.println(getKthFromEnd(root, 1));
        System.out.println(getKthFromEnd(root, 2));
    }
}
