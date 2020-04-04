package offer;

import offer.tools.tree.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof/
 */
public class T32III_从上到下打印二叉树III {
    /**
     * 【层序遍历】记录层数，然后分奇偶层决定正插还是反插
     * 执行用时 :1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :39.1 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) return lists;
        int level = 1;
        int levelNum = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        LinkedList<Integer> list = new LinkedList<>(); // 由于要往头插，所有使用LinkedList比较好
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if ((level & 1) == 1) {
                list.addLast(node.val);
            } else {
                list.addFirst(node.val);
            }
            levelNum--;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            if (levelNum == 0) {
                lists.add(list);
                levelNum = queue.size();
                level++;
                list = new LinkedList<>();
            }
        }
        return lists;
    }

    /**
     * 【递归】
     * 执行用时 :1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :38.8 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) return lists;
        levelOrder(root, lists, 0);
        return lists;
    }

    private void levelOrder(TreeNode node, List<List<Integer>> lists, int level) {
        if (node == null) return;
        if (lists.size() == level) { // 说明是新的一层
            lists.add(new LinkedList<>());
        }
        if ((level & 1) == 0) { // level从0开始计数
            lists.get(level).add(node.val);
        } else {
            lists.get(level).add(0, node.val);
        }

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
