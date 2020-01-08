package tree;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 */
public class T94_二叉树的中序遍历 {

    // 递归
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root != null) {
            inorderTraversal(root, list);
        }
        return list;
    }

    private List<Integer> inorderTraversal(TreeNode node, List<Integer> list) {
        if (node == null) {
            return null;
        }
        if (node.left != null) {
            inorderTraversal(node.left, list);
        }
        list.add(node.val);
        if (node.right != null) {
            inorderTraversal(node.right, list);
        }
        return list;
    }

    // 迭代
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        // 这题挺难想到的
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            list.add(node.val);
            node = node.right;
        }
        return list;
    }

    @Test
    public void test01() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        List<Integer> list = inorderTraversal(root);
        list.forEach((x) -> System.out.println(x));
    }

    @Test
    public void test02() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        List<Integer> list = inorderTraversal2(root);
        list.forEach((x) -> System.out.println(x));
    }
}
