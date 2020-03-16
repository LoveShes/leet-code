package offer;

import offer.linkedlist.LinkedList;
import offer.linkedlist.ListNode;
import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/
 */
public class T24_反转链表 {
    /**
     * 【迭代】
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :37.8 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public ListNode reverseList(ListNode head) {
        // 0个或者1个节点
        if (head == null || head.next == null) return head;

        // 有2个及以上节点
        ListNode newHead = null;
        while (head != null) {
            ListNode temp = head.next; // 保存下一个，以免丢失
            head.next = newHead;
            newHead = head;
            head = temp; // 进入下一轮
        }
        return newHead;
    }

    @Test
    public void test() {
        ListNode root = new ListNode(1);
        LinkedList list = new LinkedList(root);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println(reverseList(root));
    }
}
