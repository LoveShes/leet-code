package offer;

import offer.linkedlist.LinkedList;
import offer.linkedlist.ListNode;
import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/
 */
public class T25_合并两个排序的链表 {
    /**
     * 【迭代】
     * 执行用时 :1 ms, 在所有 Java 提交中击败了99.45% 的用户
     * 内存消耗 :41 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
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
        if (l1 == null) curr.next = l2;
        if (l2 == null) curr.next = l1;
        return dummy.next;
    }

    @Test
    public void test() {
        ListNode root1 = new ListNode(1);
        LinkedList list1 = new LinkedList(root1);
        list1.add(2);
        list1.add(4);
        ListNode root2 = new ListNode(1);
        LinkedList list2 = new LinkedList(root2);
        list2.add(3);
        list2.add(4);
        System.out.println(mergeTwoLists(root1, root2));
    }

    @Test
    public void test2() {
        ListNode root1 = new ListNode(1);
        LinkedList list1 = new LinkedList(root1);
        list1.add(2);
        list1.add(4);
        ListNode root2 = new ListNode(1);
        LinkedList list2 = new LinkedList(root2);
        list2.add(3);
        System.out.println(mergeTwoLists(root1, root2));
    }

}
