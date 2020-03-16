package offer;

import offer.random.Node;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/fu-za-lian-biao-de-fu-zhi-lcof/
 */
public class T35_复杂链表的复制 {
    /**
     * 【哈希表】
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :41.2 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public Node copyRandomList(Node head) {
        if (head == null) return null;

        Node dummy = new Node(0);
        Node curr = head;
        Node copy = dummy;
        Map<Node, Node> map1 = new HashMap<>();
        Map<Node, Node> map2 = new HashMap<>();
        while (curr != null) {
            copy.next = new Node(curr.val);
            Node random = curr.random;
            map1.put(curr, copy.next); // 旧新节点对应关系
            map2.put(copy.next, random); // 这里只能拿到新节点和旧节点的random关系
            curr = curr.next;
            copy = copy.next;
        }

        // 还原
        copy = dummy.next;
        while (copy != null) {
            copy.random = map1.get(map2.get(copy));
            copy = copy.next;
        }
        return dummy.next;
    }

    /**
     * 【哈希表-简化代码】
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :41.2 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public Node copyRandomList2(Node head) {
        if (head == null) return null;

        Map<Node, Node> map = new HashMap<>();
        // 拷贝节点
        for (Node curr = head; curr != null; curr = curr.next) {
            map.put(curr, new Node(curr.val));
        }
        // 连接节点
        for (Node curr = head; curr != null; curr = curr.next) {
            map.get(curr).next = map.get(curr.next);
            map.get(curr).random = map.get(curr.random);
        }
        return map.get(head);
    }

    /**
     * !光提出哈希表面试是没法过关的
     * 【原地修改】把复制出来的节点紧接着放后面
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :41 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public Node copyRandomList3(Node head) {
        if (head == null) return null;

        // 复制节点紧接着放后面
        for (Node curr = head; curr != null; curr = curr.next.next) {
            Node copy = new Node(curr.val);
            copy.next = curr.next;
            curr.next = copy;
        }

        // 连接random
        for (Node curr = head; curr != null; curr = curr.next.next) {
            if (curr.random != null) {
                curr.next.random = curr.random.next;
            } else {
                curr.next.random = null;
            }
        }

        // // 分拆
        // Node dummy = new Node(0);
        // Node copyCurr = dummy;
        // for (Node curr = head; curr != null; curr = curr.next) {
        //     Node copy = curr.next;
        //     copyCurr.next = copy;
        //     copyCurr = copyCurr.next;
        //     curr.next = curr.next.next;
        // }
        // return dummy.next;

        // 分拆做法2——交替分拆
        Node newHead = head.next;
        for (Node curr = head, temp = null; curr != null && curr.next != null; ) {
            temp = curr.next;
            curr.next = temp.next;
            curr = temp;
        }
        return newHead;
    }

    @Test
    public void test() {
        Node head = new Node(1);
        Node node3 = new Node(3);
        Node node2 = new Node(2);
        head.next = node2;
        head.random = node3;
        node2.next = node3;
        System.out.println(head);
        System.out.println(copyRandomList(head));
        System.out.println(copyRandomList2(head));
        System.out.println(copyRandomList3(head));
    }
}
