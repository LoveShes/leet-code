package offer;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/li-wu-de-zui-da-jie-zhi-lcof/
 */
public class T47_礼物的最大价值 {
    /**
     * 【动态规划】
     * 执行用时 :3 ms, 在所有 Java 提交中击败了85.68% 的用户
     * 内存消耗 :42.4 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int maxValue(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dp = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int top = i >= 1 ? dp[i - 1][j] : 0;
                int left = j >= 1 ? dp[i][j - 1] : 0;
                dp[i][j] = grid[i][j] + Math.max(top, left);
            }
        }
        return dp[rows - 1][cols - 1];
    }

    /**
     * 【动态规划——优化dp数组】
     * 执行用时 :2 ms, 在所有 Java 提交中击败了97.99% 的用户
     * 内存消耗 :42.3 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int maxValue2(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int rows = grid.length;
        int cols = grid[0].length;
        int[] dp = new int[cols];
        for (int i = 0; i < rows; i++) {
            dp[0] += grid[i][0]; // 每列的第1个一定是由上一列垂直走下来的
            for (int j = 1; j < cols; j++) {
                dp[j] = grid[i][j] + Math.max(dp[j - 1], dp[j]);
            }
        }
        return dp[cols - 1];
    }

    @Test
    public void test() {
        int[][] gift = new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        assert maxValue(gift) == 12 : maxValue(gift);
        assert maxValue2(gift) == 12 : maxValue2(gift);
    }
}
