package alogorithm;

import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/max-area-of-island/
 */
public class T695_岛屿的最大面积 {
    /**
     * 【深度优先搜索+递归】
     * 执行用时 :4 ms, 在所有 Java 提交中击败了54.28% 的用户
     * 内存消耗 :42.1 MB, 在所有 Java 提交中击败了89.63%的用户
     */
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int rows = grid.length;
        int cols = grid[0].length;
        int max = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                max = Math.max(max, dfs(grid, i, j));
            }
        }
        return max;
    }

    private int dfs(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != 1) return 0;
        // 到这里grid[i][j]肯定等于1
        grid[i][j] = 0; // 改成非1避免重复遍历
        int ans = 1;
        ans += dfs(grid, i - 1, j);
        ans += dfs(grid, i + 1, j);
        ans += dfs(grid, i, j - 1);
        ans += dfs(grid, i, j + 1);
        return ans;
    }

    /**
     * 【深度优先搜索+栈】
     * 执行用时 :13 ms, 在所有 Java 提交中击败了6.59% 的用户
     * 内存消耗 :41.9 MB, 在所有 Java 提交中击败了89.85%的用户
     */
    public int maxAreaOfIsland2(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int rows = grid.length;
        int cols = grid[0].length;
        int max = 0;
        Stack<Pair<Integer, Integer>> stack = new Stack<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int cur = 0;
                stack.push(new Pair<>(i, j));
                while (!stack.isEmpty()) {
                    Pair<Integer, Integer> pair = stack.pop();
                    int indexI = pair.getKey();
                    int indexJ = pair.getValue();
                    if (indexI < 0 || indexJ < 0 || indexI >= rows || indexJ >= cols || grid[indexI][indexJ] != 1)
                        continue;
                    // 到这里grid[indexI][indexJ]肯定等于1
                    cur++;
                    grid[indexI][indexJ] = 0; // 改成0避免重复遍历
                    stack.push(new Pair<>(indexI - 1, indexJ));
                    stack.push(new Pair<>(indexI + 1, indexJ));
                    stack.push(new Pair<>(indexI, indexJ - 1));
                    stack.push(new Pair<>(indexI, indexJ + 1));
                }
                max = Math.max(max, cur);
            }
        }
        return max;
    }

    /**
     * 【广度优先搜索+队列】
     * 执行用时 :8 ms, 在所有 Java 提交中击败了11.40% 的用户
     * 内存消耗 :42 MB, 在所有 Java 提交中击败了89.85%的用户
     */
    public int maxAreaOfIsland3(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int rows = grid.length;
        int cols = grid[0].length;
        int max = 0;
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int cur = 0;
                queue.offer(new Pair<>(i, j));
                while (!queue.isEmpty()) {
                    Pair<Integer, Integer> pair = queue.poll();
                    int indexI = pair.getKey();
                    int indexJ = pair.getValue();
                    if (indexI < 0 || indexJ < 0 || indexI >= rows || indexJ >= cols || grid[indexI][indexJ] != 1)
                        continue;
                    // 到这里grid[indexI][indexJ]肯定等于1
                    cur++;
                    grid[indexI][indexJ] = 0; // 改成0避免重复遍历
                    queue.offer(new Pair<>(indexI - 1, indexJ));
                    queue.offer(new Pair<>(indexI + 1, indexJ));
                    queue.offer(new Pair<>(indexI, indexJ - 1));
                    queue.offer(new Pair<>(indexI, indexJ + 1));
                }
                max = Math.max(max, cur);
            }
        }
        return max;
    }

    @Test
    public void test() {
        int[][] grid1 = {
                {0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 1, 1, 0, 1, 0},
                {0, 1, 0, 0, 1, 1},
                {0, 1, 0, 0, 1, 1}
        };
        int[][] grid2 = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        };

        System.out.println(maxAreaOfIsland3(grid1)); // 5
        System.out.println(maxAreaOfIsland3(grid2)); // 6
    }
}
