package tree;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/balanced-binary-tree/
 */
public class T110_平衡二叉树 {

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        // root.left.left = new TreeNode(3);
        // root.left.left.left = new TreeNode(4);
        // root.left.left.left.left = new TreeNode(5);
        // root.left.left.left.left.left = new TreeNode(6);
        root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(8);
        System.out.println(root);
        System.out.println(isBalanced(root));
    }

}
