package offer;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/
 */
public class T33_二叉搜索树的后序遍历序列 {
    /**
     * 【递归】先找根节点，然后再左右子树的分界线，分界线凑不到一起肯定就有问题
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :37.2 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public boolean verifyPostorder(int[] postorder) {
        if (postorder == null || postorder.length == 0) return true;
        return verifyPostorder(postorder, 0, postorder.length - 1); // 包括首尾
    }

    private boolean verifyPostorder(int[] postorder, int start, int end) {
        if (start >= end) return true; // 遍历完了，说明找到的都是符合要求的
        int root = postorder[end];
        int left = start; // 分界线
        int right = end - 1; // 分界线
        // 左子树
        while (left < end) {
            if (postorder[left] > root) break; // 左子树都是小于根节点的，第一个大于的就是右子树的首
            left++;
        }
        // 右子树
        while (right >= start) {
            if (postorder[right] < root) break; // 右子树都是大于根节点的，第一个小于的就是左子树的尾
            right--;
        }
        if (left - right != 1) return false; // “右子树的首”的前一个就是“左子树的尾”，说明能够分清左右子树
        return verifyPostorder(postorder, start, left - 1) && verifyPostorder(postorder, right + 1, end - 1);
    }

    @Test
    public void test() {
        assert verifyPostorder(new int[]{1, 3, 2, 6, 5});
        assert !verifyPostorder(new int[]{1, 6, 3, 2, 5});
        assert verifyPostorder(new int[]{1, 6});
        assert verifyPostorder(new int[]{6, 1});
        assert verifyPostorder(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
    }
}
