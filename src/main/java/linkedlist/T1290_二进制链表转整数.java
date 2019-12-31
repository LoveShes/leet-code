package linkedlist;

/**
 * https://leetcode-cn.com/problems/convert-binary-number-in-a-linked-list-to-integer/
 */
public class T1290_二进制链表转整数 {

    // 先计算长度
    public int getDecimalValue(ListNode head) {
        int sum = 0;
        int len = 0;
        ListNode curr = head;
        while (curr != null) {
           len += 1;
           curr = curr.next;
        }
        for (int i = len-1; i >= 0; i--) {
            sum += head.val << i;
            head = head.next;
        }
        return sum;
    }

    // 不用计算长度
    public int getDecimalValue2(ListNode head) {
        int sum = 0;
        ListNode curr = head;
        while (head != null) {
            // 注意，+、- 的优先级大于移位运算符
            sum = (sum << 1) + head.val;
            head = head.next;
        }
        return sum;
    }
}
