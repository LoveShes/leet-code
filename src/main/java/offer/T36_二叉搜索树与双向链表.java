package offer;

import offer.tools.doubly.Node;
import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/
 */
public class T36_二叉搜索树与双向链表 {
    /**
     * 【中序遍历——递归】
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :38.3 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    private Node pre;
    private Node head, tail;

    public Node treeToDoublyList(Node root) {
        if (root == null) return root;
        precess(root);
        // 形成循环链表
        tail.right = head;
        head.left = tail;
        return head;
    }

    private void precess(Node node) {
        if (node == null) return;
        precess(node.left);

        if (pre == null) { // 处理头结点
            head = node;
        } else {
            node.left = pre;
            pre.right = node;
        }
        pre = node;
        tail = node;

        precess(node.right);
    }

    /**
     * 【中序遍历——非递归（使用栈）】
     * 执行用时 :2 ms, 在所有 Java 提交中击败了13.50% 的用户
     * 内存消耗 :38.2 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public Node treeToDoublyList2(Node root) {
        if (root == null) return root;
        Node current = root;
        Node pre = null, head = null, tail = null;
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || current != null) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            // 实际操作
            if (pre == null) { // 处理头结点
                head = current;
            } else {
                current.left = pre;
                pre.right = current;
            }
            pre = current;
            tail = current;
            current = current.right;
        }
        head.left = tail;
        tail.right = head;
        return head;
    }

    @Test
    public void test() {
        Node root = new Node(4);
        root.left = new Node(2);
        root.left.left = new Node(1);
        root.left.right = new Node(3);
        root.right = new Node(5);
        Node head = treeToDoublyList(root);
        System.out.println(head);
    }

    @Test
    public void test2() {
        Node root = new Node(4);
        root.left = new Node(2);
        root.left.left = new Node(1);
        root.left.right = new Node(3);
        root.right = new Node(5);
        Node head = treeToDoublyList2(root);
        System.out.println(head);
    }
}
