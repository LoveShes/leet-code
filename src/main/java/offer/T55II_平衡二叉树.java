package offer;

import offer.tools.tree.TreeNode;
import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/ping-heng-er-cha-shu-lcof/
 */
public class T55II_平衡二叉树 {
    /**
     * 【先序遍历——有重复遍历】
     * 执行用时 :1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :41.4 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        // 因为比较的是左右子树的差值，所以每个节点都可以当做是在第0层
        if (Math.abs(deepth(root.left, 0) - deepth(root.right, 0)) > 1) {
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);
    }

    private int deepth(TreeNode node, int deepth) {
        if (node == null) return deepth;
        return Math.max(deepth(node.left, deepth), deepth(node.right, deepth)) + 1;
    }

    /**
     * 【后序遍历——无重复遍历】
     * 当我们后序遍历到一个节点的时候，我们已经知道了它的左右子树的深度
     * 执行用时 :1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :41.6 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public boolean isBalanced2(TreeNode root) {
        return deepth(root) != -1;
    }

    private int deepth(TreeNode node) {
        if (node == null) return 0;
        int left = deepth(node.left);
        if (left == -1) return -1; // 剪枝
        int right = deepth(node.right);
        if (right == -1) return -1; // 剪枝
        return Math.abs(left - right) <= 1 ? Math.max(left, right) + 1 : -1;
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0);
        root.left.left = new TreeNode(0);
        root.right = new TreeNode(0);
        System.out.println(isBalanced(root));
        System.out.println(isBalanced2(root));

        root.left.left.left = new TreeNode(0);
        root.right.left = new TreeNode(0);
        System.out.println(isBalanced(root));
        System.out.println(isBalanced2(root));
    }
}
