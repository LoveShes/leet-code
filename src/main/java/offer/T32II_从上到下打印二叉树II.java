package offer;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof/
 */
public class T32II_从上到下打印二叉树II {
    /**
     * 【层序遍历】
     * 执行用时 :1 ms, 在所有 Java 提交中击败了95.94% 的用户
     * 内存消耗 :38.6 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) return lists;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int levelNum = 1;
        List<Integer> levelList = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            levelList.add(node.val);
            --levelNum;

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }

            // 一层遍历完了，下一层的节点数量恰好等于队列的size
            if (levelNum == 0) {
                levelNum = queue.size();
                lists.add(levelList);
                levelList = new ArrayList<>(); // 注意，这里不能clear，不然会对原list有影响
            }
        }
        return lists;
    }

    /**
     * 【递归】
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :39.1 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) return lists;
        levelOrder(root, lists, 0);
        return lists;
    }

    private void levelOrder(TreeNode node, List<List<Integer>> lists, int level) {
        if (node == null) return;
        if (lists.size() == level) {
            lists.add(new ArrayList<>());
        }
        lists.get(level).add(node.val);
        levelOrder(node.left, lists, level + 1);
        levelOrder(node.right, lists, level + 1);
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
        System.out.println((levelOrder(root)));
        System.out.println((levelOrder2(root)));
    }
}
