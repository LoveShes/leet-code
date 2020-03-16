package alogorithm;

import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/rotting-oranges/
 */
public class T994_腐烂的橘子 {
    /**
     * 【广度优先搜索】
     * 执行用时 :5 ms, 在所有 Java 提交中击败了26.38% 的用户
     * 内存消耗 :38.8 MB, 在所有 Java 提交中击败了50.58%的用户
     */
    public int orangesRotting(int[][] grid) {
        final int FRESH = 1;
        final int ROTTEN = 2;
        int rows = grid.length;
        int cols = grid[0].length;
        int[] x = {1, -1, 0, 0};
        int[] y = {0, 0, 1, -1};
        int minutes = 0;

        // 先把所有的腐烂橘子入队
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        Map<Pair<Integer, Integer>, Integer> map = new HashMap<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == ROTTEN) {
                    Pair<Integer, Integer> pair = new Pair<>(i, j);
                    queue.offer(pair);
                    map.put(pair, 0);
                }
            }
        }

        Pair<Integer, Integer> pair;
        while (!queue.isEmpty()) {
            pair = queue.poll();
            int i = pair.getKey();
            int j = pair.getValue();
            // 取出来的肯定是腐烂的，开始上下左右感染
            for (int index = 0; index < 4; index++) {
                if (i + x[index] >= 0 && i + x[index] < rows
                        && j + y[index] >= 0 && j + y[index] < cols
                        && grid[i + x[index]][j + y[index]] == FRESH) {
                    grid[i + x[index]][j + y[index]] = ROTTEN;
                    Pair<Integer, Integer> newPair = new Pair<>(i + x[index], j + y[index]);
                    queue.offer(newPair);
                    minutes = map.get(pair) + 1;
                    map.put(newPair, minutes);
                }
            }
        }
        // System.out.println(Arrays.deepToString(grid));
        // 最后再检查有没有不新鲜的
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == FRESH) return -1;
            }
        }
        return minutes;
    }


    /**
     * 【递归-广度优先搜索】
     * 执行用时 :1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :38.1 MB, 在所有 Java 提交中击败了61.96%的用户
     */
    public int orangesRotting2(int[][] grid) {
        boolean has2 = false;
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    has2 = true;
                    bfs(grid, i, j, 2); // 对每个腐烂的橘子都进行广搜，同一个橘子有可能是被不同的腐烂橘子感染的
                }
            }
        }
        int res = 0;
        // 找出最大腐烂值
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) return -1;
                res = Math.max(res, grid[i][j]); // 要感染全部橘子，要取最长的路径
            }
        }
        if (!has2) { // 没有2也没有1
            return 0;
        }
        return res - 2;
    }

    // 每传播一层就加1
    private void bfs(int[][] grid, int x, int y, int value) {
        grid[x][y] = value;
        // 上
        // 如果是单源，可以不用加grid[x - 1][y] > value + 1，但是由于可以多源，每个烂橘子有可能是由其它不同的烂橘子传播得到的，故取最短的路径
        if (x >= 1 && (grid[x - 1][y] == 1 || grid[x - 1][y] > value + 1)) {
            bfs(grid, x - 1, y, value + 1);
        }
        // 下
        if (x + 1 < grid.length && (grid[x + 1][y] == 1 || grid[x + 1][y] > value + 1)) {
            bfs(grid, x + 1, y, value + 1);
        }
        // 左
        if (y >= 1 && (grid[x][y - 1] == 1 || grid[x][y - 1] > value + 1)) {
            bfs(grid, x, y - 1, value + 1);
        }
        // 右
        if (y + 1 < grid[0].length && (grid[x][y + 1] == 1 || grid[x][y + 1] > value + 1)) {
            bfs(grid, x, y + 1, value + 1);
        }
    }


    @Test
    public void test() {
        System.out.println(orangesRotting(new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}})); // 4
        System.out.println(orangesRotting(new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 2, 1}})); // 2
        System.out.println(orangesRotting(new int[][]{{2, 1, 1}, {0, 1, 1}, {1, 0, 1}})); // -1
        System.out.println(orangesRotting(new int[][]{{0, 2}})); // 0
        System.out.println(orangesRotting(new int[][]{{0, 1}})); // -1
        System.out.println(orangesRotting(new int[][]{{0}})); // 0
        System.out.println(orangesRotting(new int[][]{{0, 0}, {0, 0}})); // 0
    }

    @Test
    public void test2() {
        System.out.println(orangesRotting2(new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}})); // 4
        System.out.println(orangesRotting2(new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 2, 1}})); // 2
        System.out.println(orangesRotting2(new int[][]{{2, 1, 1}, {0, 1, 1}, {1, 0, 1}})); // -1
        System.out.println(orangesRotting2(new int[][]{{0, 2}})); // 0
        System.out.println(orangesRotting2(new int[][]{{0, 1}})); // -1
        System.out.println(orangesRotting2(new int[][]{{0}})); // 0
        System.out.println(orangesRotting2(new int[][]{{0, 0}, {0, 0}})); // 0
    }
}
