package tree;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/diameter-of-binary-tree/
 */
public class T543_二叉树的直径 {
    /**
     * 【递归】
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :39 MB, 在所有 Java 提交中击败了5.08%的用户
     */
    private int ans = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        deepth(root);
        return ans - 1;
    }

    /**
     * @param node 节点
     * @return 经过该节点的单边路径的最大节点数
     */
    private int deepth(TreeNode node) {
        if (node == null) return 0;
        int left = deepth(node.left);
        int right = deepth(node.right);
        // left + right + 1 为经过当前节点的双边路径的节点数
        ans = Math.max(ans, left + right + 1);
        return Math.max(left, right) + 1;
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0);
        root.left.left = new TreeNode(0);
        root.left.left.left = new TreeNode(0);
        root.left.left.left.left = new TreeNode(0);
        root.left.right = new TreeNode(0);
        root.left.right.right = new TreeNode(0);
        root.left.right.right.right = new TreeNode(0);
        root.right = new TreeNode(0);
        System.out.println(diameterOfBinaryTree(root));
    }
}
