package offer;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/
 */
public class T04_二维数组中的查找 {

    /**
     * 【暴力法】
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :48.7 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null) return false;
        for (int[] ints : matrix) {
            for (int num : ints) {
                if (target == num) return true;
            }
        }
        return false;
    }

    /**
     * 【二叉搜索树】
     * 站在右上角看，就是一颗二叉搜索树
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :48.3 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public boolean findNumberIn2DArray2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        int rows = matrix.length;
        int cols = matrix[0].length;
        // 从右上角搜索
        int i = 0, j = cols - 1;
        while (i < rows && j >= 0) {
            int num = matrix[i][j];
            if (target == num) return true;
            if (target < num) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }

    @Test
    public void test() {
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        int[] trueData = {1, 7, 11, 30};
        int[] falseData = {0, 40, 20};
        for (int data : trueData) {
            assert findNumberIn2DArray2(matrix, data);
        }
        for (int data : falseData) {
            assert !findNumberIn2DArray2(matrix, data);
        }
        assert !findNumberIn2DArray2(null, 1);
        assert !findNumberIn2DArray2(new int[][]{}, 1);
    }
}
