package tree;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
 */
public class T145_二叉树的后序遍历 {

    // 递归
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root != null) {
            postorderTraversal(root, list);
        }
        return list;
    }

    private List<Integer> postorderTraversal(TreeNode node, List<Integer> list) {
        if (node == null) {
            return null;
        }
        if (node.left != null) {
            postorderTraversal(node.left, list);
        }
        if (node.right != null) {
            postorderTraversal(node.right, list);
        }
        list.add(node.val);
        return list;
    }

    /**
     * 迭代
     * 前序遍历是 root -> left -> right，将之前的前序遍历改成 root -> right -> left，再在插入list的时候插到开头，就有 left -> right - >root
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root != null) {
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                list.add(0, node.val);
                if (node.left != null) {
                    stack.push(node.left);
                }
                if (node.right != null) {
                    stack.push(node.right);
                }
            }
        }
        return list;
    }

    /**
     * 迭代
     */
    public List<Integer> postorderTraversal3(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root != null) {
            stack.push(root);
            TreeNode prev = null;
            while (!stack.isEmpty()) {
                TreeNode top = stack.peek();
                if (top.left == null && top.right == null
                        || prev != null && (top.left == prev || top.right == prev)) {
                    prev = stack.pop();
                    list.add(prev.val);
                } else {
                    if (top.right != null) {
                        stack.push(top.right);
                    }
                    if (top.left != null) {
                        stack.push(top.left);
                    }
                }
            }
        }
        return list;
    }

    @Test
    public void test01() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        List<Integer> list = postorderTraversal(root);
        list.forEach(System.out::println);
    }

    @Test
    public void test02() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        List<Integer> list = postorderTraversal2(root);
        list.forEach(System.out::println);
    }

    @Test
    public void test03() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        List<Integer> list = postorderTraversal3(root);
        list.forEach(System.out::println);
    }
}
