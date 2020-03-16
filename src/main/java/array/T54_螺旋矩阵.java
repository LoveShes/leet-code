package array;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/spiral-matrix/
 */
public class T54_螺旋矩阵 {
    /**
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :37.7 MB, 在所有 Java 提交中击败了5.12%的用户
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null) return null;
        int rows = matrix.length;
        if (rows == 0) return new ArrayList<>();
        int cols = matrix[0].length;
        List<Integer> list = new ArrayList<>(rows * cols);

        int top = 0, bottom = rows - 1;
        int left = 0, right = cols - 1;
        while (top <= bottom && left <= right) {
            // 上方
            for (int j = left; j <= right; j++) {
                list.add(matrix[top][j]);
            }
            // 右方
            for (int i = top + 1; i <= bottom; i++) { // 选择包不包含端点很重要
                list.add(matrix[i][right]);
            }

            if (top < bottom && left < right) { // 再判断是为了避免单行或者单列重复
                // 下方
                for (int j = right - 1; j >= left; j--) {
                    list.add(matrix[bottom][j]);
                }
                // 左方
                for (int i = bottom - 1; i >= top + 1; i--) {
                    list.add(matrix[i][left]);
                }
            }

            // 调整范围
            top++;
            bottom--;
            left++;
            right--;
        }
        return list;
    }

    @Test
    public void test() {
        int[][] m1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(spiralOrder(m1));

        int[][] m2 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        System.out.println(spiralOrder(m2));

        int[][] m3 = {{1, 2, 3, 4}};
        System.out.println(spiralOrder(m3));

        int[][] m4 = {{1}, {2}, {3}, {4}};
        System.out.println(spiralOrder(m4));

        int[][] m5 = {{1}};
        System.out.println(spiralOrder(m5));

        int[][] m6 = null;
        System.out.println(spiralOrder(m6));

        int[][] m7 = {};
        System.out.println(spiralOrder(m7));
    }
}
