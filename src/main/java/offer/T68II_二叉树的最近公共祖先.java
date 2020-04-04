package offer;


import offer.tools.tree.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/
 */
public class T68II_二叉树的最近公共祖先 {
    /**
     * 【递归】
     * 执行用时 :8 ms, 在所有 Java 提交中击败了99.84% 的用户
     * 内存消耗 :41.6 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) return root;
        if (root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;
        return left == null ? right : left;
    }

    /**
     * 【找出路径】
     * 真难想，还慢
     * <p>
     * 执行用时 :287 ms, 在所有 Java 提交中击败了5.39% 的用户
     * 内存消耗 :41.2 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) return root;

        List<TreeNode> path1 = getPath(root, p.val);
        List<TreeNode> path2 = getPath(root, q.val);
        int len = Math.min(path1.size(), path2.size());
        TreeNode last = null;
        for (int i = 0; i < len; i++) {
            if (path1.get(i).val != path2.get(i).val) return last;
            last = path1.get(i);
        }
        return last;
    }

    // ![务必记住]
    // 求root到指定节点之间的路径
    private List<TreeNode> getPath(TreeNode root, int val) {
        LinkedList<TreeNode> path = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                path.add(node);
                if (node.val == val) return path;
                node = node.left;
            }
            node = stack.peek();
            if (node.right == null || node.right == prev) {
                path.removeLast();
                stack.pop();
                prev = node;
                node = null;
            } else {
                node = node.right;
            }
        }
        return path;
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(3);
        TreeNode p = new TreeNode(5);
        root.left = p;
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        TreeNode q = new TreeNode(1);
        root.right = q;
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        System.out.println(lowestCommonAncestor(root, p, q));
        System.out.println(lowestCommonAncestor2(root, p, q));

        System.out.println(getPath(root, p.val));
        System.out.println(getPath(root, q.val));
        System.out.println(getPath(root, 4));
        System.out.println(getPath(root, 2));
    }
}
