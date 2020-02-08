package tree;

import org.junit.jupiter.api.Test;
import tools.Times;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 */
public class T104_二叉树的最大深度 {

    // 递归
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left) + 1, maxDepth(root.right) + 1);
    }

    // 迭代（使用层序遍历）
    public int maxDepth2(TreeNode root) {
        int depth = 0;
        if (root == null) {
            return depth;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int levelNum = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            --levelNum;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            if (levelNum == 0) {
                levelNum = queue.size();
                ++depth;
            }
        }
        return depth;
    }

    @Test
    public void testTime() {
        int num = 999999;
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.left.left.left = new TreeNode(1);
        root.left.left.left.left = new TreeNode(1);
        root.left.left.left.right = new TreeNode(1);
        root.left.left.left.left.left = new TreeNode(1);
        root.left.left.left.left.left.left = new TreeNode(1);
        root.left.left.left.left.left.left.left = new TreeNode(1);
        root.left.left.left.left.left.left.right = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(2);
        root.right.left.left = new TreeNode(2);
        root.right.left.right = new TreeNode(2);
        root.right.left.right.left = new TreeNode(2);
        root.right.left.right.right = new TreeNode(2);
        Times.test("递归", () -> {
            for (int i = 0; i < num; i++) {
                maxDepth(root);
            }
        });
        Times.test("迭代", () -> {
            for (int i = 0; i < num; i++) {
                maxDepth2(root);
            }
        });
    }
}
