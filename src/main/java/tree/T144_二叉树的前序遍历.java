package tree;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 */
public class T144_二叉树的前序遍历 {

    // 递归
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;

        return preorderTraversal(root, list);
    }

    private List<Integer> preorderTraversal(TreeNode node, List<Integer> list) {
        if (node == null) {
            return null;
        }
        list.add(node.val);
        if (node.left != null) {
            preorderTraversal(node.left, list);
        }
        if (node.right != null) {
            preorderTraversal(node.right, list);
        }
        return list;
    }

    // 迭代，利用栈
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root != null) {
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                list.add(node.val);
                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
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
        List<Integer> list = preorderTraversal(root);
        list.forEach((x) -> System.out.println(x));
    }

    @Test
    public void test02() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        List<Integer> list = preorderTraversal2(root);
        list.forEach((x) -> System.out.println(x));
    }
}
