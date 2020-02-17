package linkedlist;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/
 */
public class T23_合并K个排序链表 {

    // 转化成合并2个排序链表，共进行k-1次
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        ListNode head = new ListNode(0);

        for (ListNode node : lists) {
            head.next = mergeTwoLists(head.next, node);
        }
        return head.next;
    }

    // 【优化/分治】mergeKLists2的优化，两两合并的次数没减少，但是不需要对节点遍历多遍
    public ListNode mergeKLists3(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        int len = lists.length;
        while (len > 1) {
            for (int i = 0; i < (len >> 1); i++) {
                lists[i] = mergeTwoLists(lists[i], lists[len - 1 - i]);
            }
            len = (len + 1) >> 1; // 注意单数的情况
        }

        return lists[0];
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode curr = head;
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
        if (l1 == null) {
            curr.next = l2;
        } else {
            curr.next = l1;
        }
        return head.next;
    }


    // 采用优先队列进行比较
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        Comparator<ListNode> cmp = Comparator.comparingInt(o -> o.val); // 小根堆
        ListNode head = new ListNode(0);
        ListNode curr = head;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(cmp);
        for (ListNode node : lists) {
            if (node != null) {
                queue.add(node);
            }
        }
        while (!queue.isEmpty()) {
            ListNode top = queue.poll();
            curr.next = top;
            curr = curr.next;
            if (top.next != null) {
                queue.add(top.next);
            }
        }
        return head.next;
    }

    @Test
    public void test() {
        ListNode node1a = new ListNode(1);
        ListNode node1b = new ListNode(4);
        ListNode node1c = new ListNode(5);
        node1a.next = node1b;
        node1b.next = node1c;

        ListNode node2a = new ListNode(1);
        ListNode node2b = new ListNode(3);
        ListNode node2c = new ListNode(4);
        node2a.next = node2b;
        node2b.next = node2c;

        ListNode node3a = new ListNode(3);
        ListNode node3b = new ListNode(6);
        node3a.next = node3b;

        ListNode[] lists = {node1a, node2a, node3a};

        // System.out.println(mergeKLists(lists));
        // System.out.println(mergeKLists2(lists));
        System.out.println(mergeKLists3(lists));

    }
}
