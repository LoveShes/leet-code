package offer;

import org.junit.jupiter.api.Test;
import tools.Times;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/
 */
public class T29_顺时针打印矩阵 {
    /**
     * 【看成一个个环，先打外圈，再打内圈】
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :41.9 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null) return null;
        int cols = matrix.length;
        if (cols == 0) return new int[]{};
        int rows = matrix[0].length;
        int[] result = new int[cols * rows];

        int[] offset = new int[]{0};
        for (int i = 0; i < ((cols + 1) >> 1); i++) {
            getRound(result, matrix, i, offset);
        }
        return result;
    }

    private void getRound(int[] result, int[][] matrix, int point, int[] offset) {
        int start = offset[0];
        if (start >= result.length) return;
        int endX = matrix.length - point;
        int endY = matrix[0].length - point;

        for (int j = point; j < endY && start < result.length; j++) {
            result[start++] = matrix[point][j];
        }
        for (int i = point + 1; i < endX - 1 && start < result.length; i++) {
            result[start++] = matrix[i][endY - 1];
        }
        for (int j = endY - 1; j >= point && start < result.length; j--) {
            result[start++] = matrix[endX - 1][j];
        }
        for (int i = endX - 2; i >= point + 1 && start < result.length; i--) {
            result[start++] = matrix[i][point];
        }
        offset[0] = start;
    }

    /**
     * 【尝试简化代码】
     * 执行用时 :1 ms, 在所有 Java 提交中击败了96.99% 的用户
     * 内存消耗 :42.4 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int[] spiralOrder2(int[][] matrix) {
        if (matrix == null) return null;
        int rows = matrix.length;
        if (rows == 0) return new int[]{};
        int cols = matrix[0].length;
        int[] result = new int[rows * cols];

        int index = 0;
        int top = 0, bottom = rows - 1;
        int left = 0, right = cols - 1;
        while (top <= bottom && left <= right) {
            // 上方
            for (int j = left; j <= right; j++) {
                result[index++] = matrix[top][j];
            }
            // 右方
            for (int i = top + 1; i <= bottom; i++) { // 这里一定要包含端点，是为了适应单列的情况
                result[index++] = matrix[i][right];
            }
            if (top < bottom && left < right) { // 再判断是为了避免单行或者单列重复
                // 下方
                for (int j = right - 1; j >= left; j--) {
                    result[index++] = matrix[bottom][j];
                }
                // 左方
                for (int i = bottom - 1; i >= top + 1; i--) {
                    result[index++] = matrix[i][left];
                }
            }
            // 调整范围
            top++;
            bottom--;
            left++;
            right--;
        }
        return result;
    }

    @Test
    public void test() {
        int[][] m1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(Arrays.toString(spiralOrder(m1)));
        System.out.println(Arrays.toString(spiralOrder2(m1)));

        int[][] m2 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        System.out.println(Arrays.toString(spiralOrder(m2)));
        System.out.println(Arrays.toString(spiralOrder2(m2)));

        int[][] m3 = {{1, 2, 3, 4}};
        System.out.println(Arrays.toString(spiralOrder(m3)));
        System.out.println(Arrays.toString(spiralOrder2(m3)));

        int[][] m4 = {{1}, {2}, {3}, {4}};
        System.out.println(Arrays.toString(spiralOrder(m4)));
        System.out.println(Arrays.toString(spiralOrder2(m4)));

        int[][] m5 = {{1}};
        System.out.println(Arrays.toString(spiralOrder(m5)));
        System.out.println(Arrays.toString(spiralOrder2(m5)));

        int[][] m6 = null;
        System.out.println(Arrays.toString(spiralOrder(m6)));
        System.out.println(Arrays.toString(spiralOrder2(m6)));

        int[][] m7 = {};
        System.out.println(Arrays.toString(spiralOrder(m7)));
        System.out.println(Arrays.toString(spiralOrder2(m7)));
    }

    @Test
    public void testTime() {
        int length = 999;
        int width = 98;
        int[][] m = new int[length][width];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                m[i][j] = 100 * i + j;
            }
        }
        int count = 999;
        Times.test("方法1", () -> {
            for (int i = 0; i < count; i++) {
                spiralOrder(m);
            }
        });
        Times.test("方法2", () -> {
            for (int i = 0; i < count; i++) {
                spiralOrder2(m);
            }
        });
        // 测试正确性
        assert Arrays.equals(spiralOrder(m), spiralOrder2(m));
    }
}
