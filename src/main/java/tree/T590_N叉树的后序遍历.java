package tree;

import org.junit.jupiter.api.Test;
import tools.Times;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/
 * 该题在leetcode上创建N叉数时不管有没有child都会创建一个空的List
 */
public class T590_N叉树的后序遍历 {

    // 递归
    public List<Integer> postorder(Node root) {
        List<Integer> list = new ArrayList<>();
        postorder(root, list);
        return list;
    }

    private void postorder(Node node, List<Integer> list) {
        if (node == null || node.children == null) {
            return;
        }
        int size = node.children.size();
        for (int i = 0; i < size; i++) {
            postorder(node.children.get(i), list);
        }
        list.add(node.val);
    }

    // TODO 超时
    public List<Integer> postorder2(Node root) {
        List<Integer> list = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        if (root != null) {
            stack.push(root);
            Node prev = null;
            while (!stack.isEmpty()) {
                Node top = stack.peek();
                List<Node> childList = top.children;
                if (childList == null || contain(childList, prev)) {
                    prev = stack.pop();
                    list.add(prev.val);
                } else {
                    for (int i = childList.size() - 1; i >= 0; i--) {
                        Node node = childList.get(i);
                        stack.push(node);
                    }
                }
            }
        }
        return list;
    }

    private boolean contain(List<Node> list, Node node) {
        if (node == null) {
            return false;
        }
        for (Node node1 : list) {
            if (node1.val == node.val) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void test01() {
        Node root = new Node(1, new ArrayList<>());

        List<Node> list1 = new ArrayList<>();
        Node node1a = new Node(2, new ArrayList<>());
        Node node1b = new Node(3, new ArrayList<>());
        Node node1c = new Node(4, new ArrayList<>());
        list1.add(node1a);
        list1.add(node1b);
        list1.add(node1c);
        root.children = list1;

        List<Node> list1a = new ArrayList<>();
        Node node1aa = new Node(5, new ArrayList<>());
        Node node1ab = new Node(6, new ArrayList<>());
        list1a.add(node1aa);
        list1a.add(node1ab);
        node1a.children = list1a;

        List<Node> list1c = new ArrayList<>();
        Node node1ca = new Node(7, new ArrayList<>());
        Node node1cb = new Node(8, new ArrayList<>());
        list1c.add(node1ca);
        list1c.add(node1cb);
        node1c.children = list1c;

        List<Integer> list = postorder(root);
        System.out.println(list);

        List<Integer> list2 = postorder2(root);
        System.out.println(list2);
    }

    // 测试for循环与while循环性能
    @Test
    public void test02() {
        int size = 5999;

        Times.test("while循环", () -> {
            long times = 0;
            int len = size;
            int i = 0;
            while (i < len) {
                int j = 0;
                while (j < len) {
                    int k = 0;
                    while (k < len) {
                        times++;
                        k++;
                    }
                    j++;
                }
                i++;
            }
            System.out.println(times);
        });

        Times.test("while循环——前置++", () -> {
            long times = 0;
            int len = size;
            int i = 0;
            while (i < len) {
                int j = 0;
                while (j < len) {
                    int k = 0;
                    while (k < len) {
                        ++times;
                        ++k;
                    }
                    ++j;
                }
                ++i;
            }
            System.out.println(times);
        });

        Times.test("for循环", () -> {
            long times = 0;
            int len = size;
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    for (int k = 0; k < len; k++) {
                        times++;
                    }
                }
            }
            System.out.println(times);
        });

        Times.test("for循环——前置++", () -> {
            long times = 0;
            int len = size;
            for (int i = 0; i < len; ++i) {
                for (int j = 0; j < len; ++j) {
                    for (int k = 0; k < len; ++k) {
                        ++times;
                    }
                }
            }
            System.out.println(times);
        });
    }
}
