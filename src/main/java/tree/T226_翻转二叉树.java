package tree;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/invert-binary-tree/
 */
public class T226_翻转二叉树 {

    // 递归
    public TreeNode invertTree(TreeNode root) {
        TreeNode temp = new TreeNode(0);
        if (root == null) {
            return null;
        }
        temp = invertTree(root.left);
        root.left = invertTree(root.right);
        root.right = temp;
        return root;
    }

    // 迭代（层序遍历）
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return root;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return root;
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        System.out.println(root);

        System.out.println(invertTree(root));
        System.out.println(invertTree2(root));
    }
}
