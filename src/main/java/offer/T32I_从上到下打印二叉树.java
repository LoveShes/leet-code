package offer;

import offer.tools.tree.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.*;

/**
 * https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof/
 */
public class T32I_从上到下打印二叉树 {
    /**
     * 【层序遍历】
     * 执行用时 :1 ms, 在所有 Java 提交中击败了97.35% 的用户
     * 内存消耗 :38.3 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int[] levelOrder(TreeNode root) {
        if (root == null) return new int[]{};
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0, size = list.size(); i < size; i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        System.out.println(Arrays.toString(levelOrder(root)));
    }
}
