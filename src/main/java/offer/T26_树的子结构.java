package offer;

import offer.tools.tree.TreeNode;
import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/
 */
public class T26_树的子结构 {
    /**
     * 【遍历树A找到B节点，然后再遍历子结构】
     * 注意，child遍历完了就行，子结构只用是整体的一部分
     * <p>
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :42.2 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) return false;
        TreeNode node = findNode(A, B);
        // 没找到
        if (node == null) return false;
        // 找到了，接着判断左子树与右子树是否相等
        return isEqual(node, B);
    }

    // 注意，这里child遍历完了就行
    private boolean isEqual(TreeNode father, TreeNode child) {
        if (child == null) return true;
        if (father == null || father.val != child.val) return false;
        return isEqual(father.left, child.left) && isEqual(father.right, child.right);
    }

    private TreeNode findNode(TreeNode root, TreeNode node) {
        if (root == null) return null;
        if (root.val == node.val) return root;
        TreeNode left = findNode(root.left, node);
        TreeNode right = findNode(root.right, node);
        return left == null ? right : left;
    }

    /**
     * 【更为简洁的做法】
     * 每次从判断当前节点是否与B节点相等，不相等就去左右子树中找
     */
    public boolean isSubStructure2(TreeNode A, TreeNode B) {
        if (A == null || B == null) return false;
        return recur(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    // 判断从A、B开始的子树是否相等
    private boolean recur(TreeNode A, TreeNode B) {
        if (B == null) return true;
        if (A == null || A.val != B.val) return false;
        return recur(A.left, B.left) && recur(A.right, B.right);
    }

    @Test
    public void test() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        TreeNode A = node1;
        TreeNode B = node3;

        System.out.println(A);
        System.out.println(B);
        assert isSubStructure(A, B);
        assert isSubStructure2(A, B);
    }
}
