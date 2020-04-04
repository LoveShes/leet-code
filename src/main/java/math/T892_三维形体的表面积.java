package math;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/surface-area-of-3d-shapes/
 */
public class T892_三维形体的表面积 {
    /**
     * 【输入】[[2]]
     * 【输出】10
     * 【解释】网格大小为1x1，在(0,0)放2个立方体，表面积为10
     *
     * 【输入】[[1,2],[3,4]]
     * 【输出】34
     * 【解释】网格大小为2x2，在(0,0)放1个立方体，在(0,1)放2个立方体，在(1,0)放3个立方体，在(1,1)放4个立方体，表面积为34
     */

    /**
     * 【总面积减去重复面积】
     * 执行用时 :5 ms, 在所有 Java 提交中击败了49.67% 的用户
     * 内存消耗 :41.5 MB, 在所有 Java 提交中击败了75.00%的用户
     */
    public int surfaceArea(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int N = grid.length;
        int total = 0; // 总面积
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int num = grid[i][j];
                total += 6 * num;
                // 减去自己位置上重叠的面积
                if (num > 0) total -= 2 * (num - 1);
                // 减去相邻位置上重叠的面积
                // 减去上面重复的
                if (i > 0) total -= 2 * Math.min(num, grid[i - 1][j]);
                // 减去左边重复的
                if (j > 0) total -= 2 * Math.min(num, grid[i][j - 1]);
            }
        }
        return total;
    }

    /**
     * 【优化代码】
     * 去除不必要的计算
     * <p>
     * 执行用时 :3 ms, 在所有 Java 提交中击败了98.69% 的用户
     * 内存消耗 :41.2 MB, 在所有 Java 提交中击败了88.97%的用户
     */
    public int surfaceArea2(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int N = grid.length;
        int total = 0; // 总面积
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int num = grid[i][j];
                if (num > 0) { // 摆放的立方体数大于0才有必要进行计算
                    total += 6 * num;
                    // 减去自己位置上重叠的面积
                    total -= 2 * (num - 1);
                    // 减去相邻位置上重叠的面积
                    // 减去上面重复的
                    if (i > 0) total -= 2 * Math.min(num, grid[i - 1][j]);
                    // 减去左边重复的
                    if (j > 0) total -= 2 * Math.min(num, grid[i][j - 1]);
                }
            }
        }
        return total;
    }

    @Test
    public void test() {
        System.out.println(surfaceArea(new int[][]{{2}})); // 10
        System.out.println(surfaceArea(new int[][]{{1, 2}, {3, 4}})); // 34
        System.out.println(surfaceArea(new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}})); // 32
        System.out.println(surfaceArea(new int[][]{{2, 2, 2}, {2, 1, 2}, {2, 2, 2}})); // 46
    }
}
