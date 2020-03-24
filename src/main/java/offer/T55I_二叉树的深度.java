package offer;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/er-cha-shu-de-shen-du-lcof/
 */
public class T55I_二叉树的深度 {
    /**
     * 【dfs】
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :39.1 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return dfs(root, 0);
    }

    private int dfs(TreeNode node, int deepth) {
        if (node == null) return deepth;
        deepth = Math.max(dfs(node.left, deepth), dfs(node.right, deepth)) + 1;
        return deepth;
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0);
        root.left.left = new TreeNode(0);
        root.right = new TreeNode(0);
        System.out.println(maxDepth(root)); // 3

        root.left.left.left = new TreeNode(0);
        root.right.left = new TreeNode(0);
        System.out.println(maxDepth(root)); // 4
    }
}
