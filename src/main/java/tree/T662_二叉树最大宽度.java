package tree;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/maximum-width-of-binary-tree/
 */
public class T662_二叉树最大宽度 {

    // TODO 注意，端点间的null也算
    public int widthOfBinaryTree(TreeNode root) {
        int width = 0;
        if (root == null) {
            return width;
        }

        return width;
    }

    @Test
    public void test01() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(5);
        root.left.left.left = new TreeNode(6);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(9);
        root.right.right.right = new TreeNode(9);
        System.out.println(widthOfBinaryTree(root)); // 8
    }
}
