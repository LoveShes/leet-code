package tree;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 */
public class T102_二叉树的层次遍历 {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int levelNum = 1;
        List<Integer> levelList = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            --levelNum;
            levelList.add(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            // 一层遍历完了
            if (levelNum == 0) {
                levelNum = queue.size();
                list.add(levelList);
                levelList = new ArrayList<>();
            }
        }
        return list;
    }

    @Test
    public void test01() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        List<List<Integer>> lists = levelOrder(root);
        for (List<Integer> list : lists) {
            for (Integer integer : list) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void test02() {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        lists.add(list);
        for (List<Integer> integers : lists) {
            for (Integer integer : integers) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }

        list.clear();
        for (List<Integer> integers : lists) {
            for (Integer integer : integers) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }
}
