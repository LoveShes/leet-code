package offer;

import offer.tools.tree.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/
 */
public class T54_二叉搜索树的第k大节点 {
    /**
     * 【中序遍历】
     * 执行用时 :1 ms, 在所有 Java 提交中击败了66.42% 的用户
     * 内存消耗 :41.1 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int kthLargest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list.get(list.size() - k);
    }

    private void inorder(TreeNode node, List<Integer> list) {
        if (node == null) return;
        inorder(node.left, list);
        list.add(node.val);
        inorder(node.right, list);
    }


    /**
     * 【改进——不用list存】
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :41.4 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int kthLargest2(TreeNode root, int k) {
        int[] count = {0};
        int[] ans = {-1};
        inorder(root, k, count, ans);
        return ans[0];
    }

    private void inorder(TreeNode node, int k, int[] count, int[] ans) {
        if (node.right != null) {
            inorder(node.right, k, count, ans);
        }
        count[0]++; // 访问到当前节点
        if (count[0] == k) {
            ans[0] = node.val;
            return;
        }
        if (node.left != null) {
            inorder(node.left, k, count, ans);
        }
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        System.out.println(kthLargest(root, 1));
        System.out.println(kthLargest(root, 2));
        System.out.println(kthLargest(root, 3));

        System.out.println(kthLargest2(root, 1));
        System.out.println(kthLargest2(root, 2));
        System.out.println(kthLargest2(root, 3));
    }

}
