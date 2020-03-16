package offer;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/
 */
public class T34_二叉树中和为某一值的路径 {
    /**
     * 【前序遍历】递归
     * 注意，该题一定要是root到leaf节点才算
     * 执行用时 :1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :41.1 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) return lists;

        pathSum(root, sum, 0, lists, new ArrayList<>());
        return lists;
    }

    private void pathSum(TreeNode node, int sum, int currSum, List<List<Integer>> lists, ArrayList<Integer> list) {
        currSum += node.val;
        list.add(node.val);

        // 判断是否为叶子节点
        boolean isLeaf = node.left == null && node.right == null;
        if (currSum == sum && isLeaf) {
            // lists.add((List<Integer>) list.clone());
            lists.add(new ArrayList<>(list));
        }

        // 不是叶子节点则遍历子节点
        if (node.left != null) {
            pathSum(node.left, sum, currSum, lists, list);
        }
        if (node.right != null) {
            pathSum(node.right, sum, currSum, lists, list);
        }
        // 返回父节点之前，删除当前叶子节点
        list.remove(list.size() - 1);
    }

    /**
     * TODO 有错误待修正
     */
    public List<List<Integer>> pathSum2(TreeNode root, int sum) {
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) return lists;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int target = 0;
        ArrayList<Integer> list = new ArrayList<>();

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop(); // 这里pop后就找不到原来的呢
            list.add(node.val);
            target += node.val;
            if (target == sum) {
                lists.add((List<Integer>) list.clone());
            }
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right == null && node.left == null && !list.isEmpty()) {
                int remove = list.remove(list.size() - 1);
                target -= remove;
                stack.pop();
            }
        }
        return lists;
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(1);
        root.left.left.left.left = new TreeNode(1);
        root.left.left.right = new TreeNode(2);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);

        // System.out.println(root);
        System.out.println(pathSum(root, 22));
        System.out.println(pathSum(root, 2));
        // System.out.println(pathSum2(root, 22));
    }
}
