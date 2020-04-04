package offer;

import offer.tools.tree.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof/
 */
public class T28_对称的二叉树 {
    /**
     * 【递归】
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :37.3 MB, 在所有 Java 提交中击败了40.05%的用户
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        return left.val == right.val
                && isSymmetric(left.left, right.right)
                && isSymmetric(left.right, right.left);
    }

    /**
     * 【迭代】
     * 借助队列，注意这里入队的顺序
     * 执行用时 :1 ms, 在所有 Java 提交中击败了39.70% 的用户
     * 内存消耗 :37.3 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public boolean isSymmetric2(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);
        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();
            if (left == null && right == null) continue; // 这里不能提前返回
            if (left == null || right == null) return false;
            if (left.val != right.val) {
                return false;
            }
            // 注意这里入队的顺序，是left.left与right.right比，left.right与right.left比
            queue.offer(left.left);
            queue.offer(right.right);
            queue.offer(left.right);
            queue.offer(right.left);
        }
        return true;
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(0);
        TreeNode root1 = new TreeNode(1);
        TreeNode root2 = new TreeNode(2);
        TreeNode root3 = new TreeNode(3);
        TreeNode root4 = new TreeNode(4);
        root.left = root1;
        root.left.left = root2;
        root.right = root3;
        root.right.left = root4;
        System.out.println(root);

        assert !isSymmetric(root);
    }

    @Test
    public void test2() {
        TreeNode root = new TreeNode(0);
        TreeNode root1 = new TreeNode(1);
        TreeNode root2 = new TreeNode(2);
        TreeNode root3 = new TreeNode(1);
        TreeNode root4 = new TreeNode(2);
        root.left = root1;
        root.left.left = root2;
        root.right = root3;
        root.right.right = root4;
        System.out.println(root);

        assert isSymmetric(root);
        assert isSymmetric2(root);
    }
}
