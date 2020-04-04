package offer;

import offer.tools.tree.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof/
 */
public class T27_二叉树的镜像 {
    /**
     * 【递归】
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :36.6 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) return root;
        TreeNode right = mirrorTree(root.right);
        TreeNode left = mirrorTree(root.left);
        root.left = right;
        root.right = left;
        return root;
    }

    /**
     * 【迭代】
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :36.4 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public TreeNode mirrorTree2(TreeNode root) {
        if (root == null) return root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
        }
        return root;
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

        System.out.println(mirrorTree(root));
        System.out.println(mirrorTree(null));
        System.out.println(mirrorTree(new TreeNode(1)));
    }

    @Test
    public void test2() {
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

        System.out.println(mirrorTree2(root));
        System.out.println(mirrorTree2(null));
        System.out.println(mirrorTree2(new TreeNode(1)));
    }
}
