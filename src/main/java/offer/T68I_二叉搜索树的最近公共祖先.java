package offer;

import offer.tools.tree.TreeNode;
import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-lcof/
 */
public class T68I_二叉搜索树的最近公共祖先 {
    /**
     * 【从根节点往下找】
     * 根据值的不同，有3种情况：
     * 1. 当前节点的值比p、q的都大，说明p、q在左子树上，接下来应该往左子树走
     * 2. 当前节点的值比p、q的都小，说明p、q在右子树上，接下来应该往右子树走
     * 3. 当前节点的值在p、q之间，说明该点一定是公共祖先
     * 执行用时 :6 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :42.4 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) return root;
        TreeNode curr = root;
        // 缓存结果更快
        int pVal = p.val;
        int qVal = q.val;
        while (curr != null) {
            int currVal = curr.val;
            if (currVal < pVal && currVal < qVal) {
                curr = curr.right;
            } else if (currVal > pVal && currVal > qVal) {
                curr = curr.left;
            } else {
                return curr;
            }
        }
        return null;
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        TreeNode p = new TreeNode(0);
        TreeNode q = new TreeNode(2);
        root.left = p;
        root.right = q;
        System.out.println(lowestCommonAncestor(root, p, q));
    }
}
