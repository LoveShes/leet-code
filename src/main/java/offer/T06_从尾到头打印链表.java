package offer;

import offer.linkedlist.ListNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/
 */
public class T06_从尾到头打印链表 {

    /**
     * 【使用数组】先求出链表的长度，再依次倒序放入数组中。
     * ! 事实上，对于Java而言，这种方式最高效
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :39.3 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int[] reversePrint(ListNode head) {
        if (head == null) return new int[]{};

        ListNode curr = head;
        int count = 0;
        while (curr != null) {
            count++;
            curr = curr.next;
        }

        int[] result = new int[count];
        curr = head;
        while (curr != null) {
            result[--count] = curr.val;
            curr = curr.next;
        }
        return result;
    }

    /**
     * 【使用栈】由于后遍历的先输出，符合后进先出，可以使用栈
     * 使用复杂数据结构可能会更耗时
     * 执行用时 :3 ms, 在所有 Java 提交中击败了37.39% 的用户
     * 内存消耗 :39.5 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int[] reversePrint2(ListNode head) {
        if (head == null) return new int[]{};

        ListNode curr = head;
        Stack<Integer> stack = new Stack<>();
        int len = 0;
        while (curr != null) {
            stack.push(curr.val);
            curr = curr.next;
            len++;
        }

        int[] result = new int[len];
        int index = 0;
        while (!stack.isEmpty()) {
            result[index++] = stack.pop();
        }

        return result;
    }

    /**
     * 【使用递归】可以使用递归，先递归，再输出
     * 执行用时 :1 ms, 在所有 Java 提交中击败了81.22% 的用户
     * 内存消耗 :40.6 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int[] reversePrint3(ListNode head) {
        if (head == null) return new int[]{};

        List<ListNode> list = new ArrayList<>();
        list = reversePrint3(head, list);
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i).val;
        }
        return result;
    }

    private List<ListNode> reversePrint3(ListNode node, List list) {
        if (node == null) return list;
        list = reversePrint3(node.next, list);
        list.add(node);
        return list;
    }

    @Test
    public void test() {
        ListNode root = new ListNode(1);
        root.next = new ListNode(2);
        System.out.println(Arrays.toString(reversePrint(root)));
        System.out.println(Arrays.toString(reversePrint2(root)));
        System.out.println(Arrays.toString(reversePrint3(root)));
    }
}
