package alogorithm;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/as-far-from-land-as-possible/
 */
public class T1162_地图分析 {
    /**
     * 【分类直接计算】
     * 超出时间限制
     */
    public int maxDistance(int[][] grid) {
        if (grid == null || grid.length == 0) return -1;
        int N = grid.length;
        List<int[]> listSea = new ArrayList<>();
        List<int[]> listLand = new ArrayList<>();
        // 分类
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int[] pair = {i, j};
                if (grid[i][j] == 0) {
                    listSea.add(pair);
                } else {
                    listLand.add(pair);
                }
            }
        }
        // 对于每一个海洋，判断与每一个陆地的距离，海洋与陆地的距离就是与最近的陆地的距离
        // 从最小的中选出一个最大的
        int max = -1; // 没有海洋就返回-1
        for (int i = 0; i < listSea.size(); i++) {
            int seaDistance = Integer.MAX_VALUE;
            // 对于一个海洋来说，离陆地最近的距离就是它与陆地的距离
            for (int j = 0; j < listLand.size(); j++) {
                int dis = distance(listSea.get(i), listLand.get(j));
                if (dis < seaDistance) {
                    seaDistance = dis;
                }
            }
            if (seaDistance > max) {
                max = seaDistance;
            }
        }
        // 没有陆地
        if (max == Integer.MAX_VALUE) return -1;
        return max;
    }


    private int distance(int[] sea, int[] land) {
        return Math.abs(sea[0] - land[0]) + Math.abs(sea[1] - land[1]);
    }

    /**
     * 【BFS】
     * 因为我们只要先把所有的陆地都入队，然后从各个陆地同时开始一层一层(每次向上下左右4个方向)的向海洋扩散，那么最后扩散到的海洋就是最远的海洋！
     * 并且这个海洋肯定是被离他最近的陆地给扩散到的！
     * <p>
     * 执行用时 :16 ms, 在所有 Java 提交中击败了89.75% 的用户
     * 内存消耗 :42.2 MB, 在所有 Java 提交中击败了99.00%的用户
     */
    public int maxDistance2(int[][] grid) {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        Queue<int[]> queue = new ArrayDeque<>(); // 存的x, y坐标
        int m = grid.length;
        int n = grid[0].length;
        // 先将所有陆地入队
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        // 从各个陆地开始，一圈一圈遍历海洋，最后遍历到的海洋就是最远的海洋
        boolean hasSea = false;
        int[] point = null;
        while (!queue.isEmpty()) {
            point = queue.poll();
            int x = point[0];
            int y = point[1];
            // 将陆地四周的海洋入队
            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                // 不能超范围，注意如果前面有陆地遍历过了海洋，则该海洋就不为0了，下次就不会重复遍历了
                if (newX < 0 || newX >= m || newY < 0 || newY >= n || grid[newX][newY] != 0) continue;
                grid[newX][newY] = grid[x][y] + 1; // 遍历后修改，下次不会重复访问
                hasSea = true;
                queue.offer(new int[]{newX, newY}); // 被感染的海洋可以继续感染
            }
        }
        // 没有陆地或者没有海洋
        if (point == null || !hasSea) return -1;
        // 返回最后一次遍历的海洋的距离，陆地初始坐标为1，所以要减1
        return grid[point[0]][point[1]] - 1;
    }

    /**
     * 【动态规划】
     * 题目的意思是求到陆地最远的海洋（海洋到陆地的距离是指到最近的那个陆地的距离），可以2次遍历取最小值
     * 一般dp都是从左上到右下遍历，考虑以下例子
     * 从左上到右下，对于每个海洋找最近的陆地，这时候都是相对于左上角的陆地来说的，
     * 多加一次遍历，从右上角到左下角，2次遍历取最小值
     * <pre>
     *  ┌───────────────┐
     *  │ 1, 0, 0, 0, 0 │
     *  │ 0, 0, 0, 0, 1 │
     *  └───────────────┘
     * </pre>
     * 执行用时 :8 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :42.7 MB, 在所有 Java 提交中击败了99.00%的用户
     */
    public int maxDistance3(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean hasLand = false;
        // grid本身充当了dp数组
        // 第1次从左上往右下↘搜索
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    hasLand = true;
                    continue;
                }
                if (grid[i][j] == 0) grid[i][j] = m + n; // 如果是海洋的话，设为最远距离，便于后面求min
                if (i > 0) grid[i][j] = Math.min(grid[i][j], grid[i - 1][j] + 1); // 上方
                if (j > 0) grid[i][j] = Math.min(grid[i][j], grid[i][j - 1] + 1); // 左方
            }
        }

        int res = 0;
        // 第2次从右上往左下↙搜索
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 1) continue;
                if (i < m - 1) grid[i][j] = Math.min(grid[i][j], grid[i + 1][j] + 1); // 下方
                if (j < n - 1) grid[i][j] = Math.min(grid[i][j], grid[i][j + 1] + 1); // 右方
                res = Math.max(res, grid[i][j]); // 找最远的
            }
        }
        return hasLand ? res - 1 : -1;
    }

    @Test
    public void test() {
        int max1 = maxDistance(new int[][]{{1, 0, 1}, {0, 0, 0}, {1, 0, 1}});
        assert max1 == 2 : max1;
        int max2 = maxDistance(new int[][]{{1, 0, 0}, {0, 0, 0}, {0, 0, 0}});
        assert max2 == 4 : max2;
        int max3 = maxDistance(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}});
        assert max3 == -1 : max3;
        int max4 = maxDistance(new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}});
        assert max4 == -1 : max4;
    }

    @Test
    public void test2() {
        int max1 = maxDistance2(new int[][]{{1, 0, 1}, {0, 0, 0}, {1, 0, 1}});
        assert max1 == 2 : max1;
        int max2 = maxDistance2(new int[][]{{1, 0, 0}, {0, 0, 0}, {0, 0, 0}});
        assert max2 == 4 : max2;
        int max3 = maxDistance2(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}});
        assert max3 == -1 : max3;
        int max4 = maxDistance2(new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}});
        assert max4 == -1 : max4;
    }

    @Test
    public void test3() {
        // int max1 = maxDistance3(new int[][]{{1, 0, 1}, {0, 0, 0}, {1, 0, 1}});
        // assert max1 == 2 : max1;
        // int max2 = maxDistance3(new int[][]{{1, 0, 0}, {0, 0, 0}, {0, 0, 0}});
        // assert max2 == 4 : max2;
        int max3 = maxDistance3(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}});
        assert max3 == -1 : max3;
        // int max4 = maxDistance3(new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}});
        // assert max4 == -1 : max4;
    }
}
