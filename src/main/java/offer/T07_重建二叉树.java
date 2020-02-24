package offer;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * ! 重点复习
 * https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/
 */
public class T07_重建二叉树 {
    /**
     * 【递归】
     * 执行用时 :4 ms, 在所有 Java 提交中击败了77.41% 的用户
     * 内存消耗 :39.5 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) return null;
        if (inorder == null || inorder.length == 0) return null;
        if (preorder.length != inorder.length) return null;

        return buildTree(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    private TreeNode buildTree(int[] preorder, int pStart, int pEnd,
                               int[] inorder, int iStart, int iEnd) { // 左闭右开
        if (pStart >= preorder.length || iEnd == iStart) return null;
        if (iEnd - iStart == 1) return new TreeNode(inorder[iStart]);

        int rootValue = preorder[pStart];
        TreeNode root = new TreeNode(rootValue);
        int rootIndexOfInorder = iStart;
        for (int i = iStart; i < iEnd; i++) {
            if (inorder[i] == rootValue) {
                rootIndexOfInorder = i;
                break;
            }
        }
        int leftCount = rootIndexOfInorder - iStart;
        TreeNode left = buildTree(preorder, pStart + 1, pStart + leftCount + 1, inorder, iStart, rootIndexOfInorder);
        TreeNode right = buildTree(preorder, pStart + leftCount + 1, pEnd, inorder, rootIndexOfInorder + 1, iEnd);
        root.left = left;
        root.right = right;
        return root;
    }

    /**
     * TODO【参考解法】
     * 执行用时 :1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :39.8 MB, 在所有 Java 提交中击败了50.26%的用户
     */
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        return construct(preorder, inorder, new int[]{0}, new int[]{0}, Integer.MAX_VALUE);
    }

    // 注意，这里的index和pre尽管只用到了下标为0的元素，但是不能传int，而要传index[]。
    // 因为参数为基本类型时，传入的是完整的一份拷贝，在方法内部修改不会影响到外面。
    // 传入对象类型（包括基本类型的数组）时，传入的是引用，对引用的对象内容进行修改会反映到外面（但是修改引用后的对象则不会反映到外面）。
    private TreeNode construct(int[] preorder, int[] inorder, int[] index, int[] pre, int max) {
        if (index[0] == inorder.length || inorder[index[0]] == max) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[pre[0]]);
        pre[0]++;
        TreeNode left = construct(preorder, inorder, index, pre, root.val);
        index[0]++;
        TreeNode right = construct(preorder, inorder, index, pre, max);
        root.left = left;
        root.right = right;
        return root;
    }

    @Test
    public void test() {
        System.out.println(buildTree2(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7}));
        // System.out.println(buildTree(new int[]{3}, new int[]{3}));
        // System.out.println(buildTree(new int[]{}, new int[]{}));
        // System.out.println(buildTree(new int[]{1, 2}, new int[]{2, 1}));
        // System.out.println(buildTree(new int[]{1, 2}, new int[]{1, 2}));
        // System.out.println(buildTree(new int[]{1, 2, 3}, new int[]{3, 2, 1}));
    }

    public void test1(int[] a, int[] b) {
        a[0] = 100; // 修改a引用的内容，对外部有效
        b = new int[]{1}; // 重新设定b的引用对象，对外部无效
    }

    @Test
    public void test2() {
        int[] a = {1, 2, 3, 4, 5};
        int[] b = {1, 2, 3, 4, 5};
        test1(a, b);
        System.out.println(Arrays.toString(a)); // a会发生变化
        System.out.println(Arrays.toString(b)); // b不会发生变化
    }
}
