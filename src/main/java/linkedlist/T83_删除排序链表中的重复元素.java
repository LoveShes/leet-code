package linkedlist;

/**
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
 */
public class T83_删除排序链表中的重复元素 {

    public ListNode deleteDuplicates(ListNode head) {
        ListNode curr = head;
        while (curr != null && curr.next != null) {
            if (curr.next.val == curr.val) { // 下一个元素和当前元素相等则跳过下一个元素
                curr.next = curr.next.next;
            } else { // 不相等则查找下一个元素
                curr = curr.next;
            }
        }
        return head;
    }
}
