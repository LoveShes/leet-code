package bit;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/game-of-life/
 * 要求同时更新，不能用更新后的值再去更新其它值
 * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
 * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
 * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
 * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
 */
public class T289_生命游戏 {
    /**
     * 【标记数组】
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :37.9 MB, 在所有 Java 提交中击败了5.71%的用户
     */
    public void gameOfLife(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        boolean[][] mark = new boolean[rows][cols]; // 默认为0（死亡）
        int[] dx = {-1, 1, 0, 0, -1, 1, -1, 1};
        int[] dy = {0, 0, -1, 1, -1, 1, 1, -1};
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int life = 0;
                // 8个方向
                for (int k = 0; k < 8; k++) {
                    int x = row + dx[k];
                    int y = col + dy[k];
                    if (x < 0 || x >= rows || y < 0 || y >= cols) continue;
                    if (board[x][y] == 1) life++;
                }
                if (life < 2) continue;
                if (life == 2) { // 活细胞存活，死细胞死亡
                    if (board[row][col] == 1) mark[row][col] = true;
                } else if (life == 3) { // 死活都会存活
                    mark[row][col] = true;
                }
            }
        }
        // 根据mark数组重整board数组
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                board[row][col] = mark[row][col] ? 1 : 0;
            }
        }
    }

    /**
     * 【使用位运算】
     * 题目要求原地算法，int类型的数组只存了1、0两种值，故可以考虑采用位运算
     * 将原int数最低位为前一次的状态，倒数第二位存下一次的状态。
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :38.5 MB, 在所有 Java 提交中击败了5.71%的用户
     */
    public void gameOfLife2(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        int[] dx = {-1, 1, 0, 0, -1, 1, -1, 1};
        int[] dy = {0, 0, -1, 1, -1, 1, 1, -1};
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int life = 0;
                // 8个方向
                for (int k = 0; k < 8; k++) {
                    int x = row + dx[k];
                    int y = col + dy[k];
                    if (x < 0 || x >= rows || y < 0 || y >= cols) continue;
                    // 这里要用&1取最低位
                    if ((board[x][y] & 1) == 1) life++;
                }
                // 把下一次的状态存在倒数第二位
                if (life < 2) continue; // 原数据为 0b00或者0b01，倒数第二位本来就是0，不用计算
                if (life == 2) { // 活细胞存活，死细胞死亡
                    if ((board[row][col] & 1) == 1) {
                        board[row][col] = 0b11;
                    } else {
                        board[row][col] = 0b00;
                    }
                } else if (life == 3) { // 死活都会存活
                    board[row][col] |= 0b10;
                }
            }
        }
        // 计算出下一状态真实值
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                board[row][col] >>>= 1; // 右移一位
            }
        }
    }

    @Test
    public void test() {
        int[][] input = {{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
        int[][] output = {{0, 0, 0}, {1, 0, 1}, {0, 1, 1}, {0, 1, 0}};
        gameOfLife(input);
        assert Arrays.deepEquals(input, output);
    }

    @Test
    public void test2() {
        int[][] input = {{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
        int[][] output = {{0, 0, 0}, {1, 0, 1}, {0, 1, 1}, {0, 1, 0}};
        gameOfLife2(input);
        assert Arrays.deepEquals(input, output);
    }
}
