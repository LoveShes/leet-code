package array;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/available-captures-for-rook/
 */
public class T999_车的可用捕获量 {
    /**
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :36.9 MB, 在所有 Java 提交中击败了5.40%的用户
     */
    public int numRookCaptures(char[][] board) {
        final char rook = 'R';
        final char bishop = 'B';
        final char pawn = 'p';
        final char blank = '.';
        int ans = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // 先找到白车
                if (board[i][j] == rook) {
                    // 开始上下左右试探
                    // 上
                    int top = i - 1;
                    while (top >= 0 && board[top][j] == blank) {
                        top--;
                    }
                    if (top >= 0 && board[top][j] == pawn) ans++;
                    // 下
                    int bottom = i + 1;
                    while (bottom < 8 && board[bottom][j] == blank) {
                        bottom++;
                    }
                    if (bottom < 8 && board[bottom][j] == pawn) ans++;
                    // 左
                    int left = j - 1;
                    while (left >= 0 && board[i][left] == blank) {
                        left--;
                    }
                    if (left >= 0 && board[i][left] == pawn) ans++;
                    // 右
                    int right = j + 1;
                    while (right < 8 && board[i][right] == blank) {
                        right++;
                    }
                    if (right < 8 && board[i][right] == pawn) ans++;
                    return ans;
                }

            }
        }
        return ans;
    }

    /**
     * 【简化代码——方向数组】
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :36.9 MB, 在所有 Java 提交中击败了5.40%的用户
     */
    public int numRookCaptures2(char[][] board) {
        final char rook = 'R';
        final char bishop = 'B';
        final char pawn = 'p';
        final char blank = '.';
        int[] axisX = {1, -1, 0, 0};
        int[] axisY = {0, 0, 1, -1};
        int ans = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // 先找到白车
                if (board[i][j] == rook) {
                    // 开始上下左右试探
                    for (int k = 0; k < 4; k++) {
                        int x = i + axisX[k];
                        int y = j + axisY[k];
                        while (x >= 0 && x < 8 && y >= 0 && y < 8
                                && board[x][y] == blank) {
                            x += axisX[k];
                            y += axisY[k];
                        }
                        if (x < 0 || x >= 8 || y < 0 || y >= 8) continue;
                        if (board[x][y] == pawn) ans++;
                    }
                    return ans;
                }
            }
        }
        return ans;
    }

    @Test
    public void test() {
        int ans;

        char[][] data1 = {{'.', '.', '.', '.', '.', '.', '.', '.'}, {'.', '.', '.', 'p', '.', '.', '.', '.'}, {'.', '.', '.', 'R', '.', '.', '.', 'p'}, {'.', '.', '.', '.', '.', '.', '.', '.'}, {'.', '.', '.', '.', '.', '.', '.', '.'}, {'.', '.', '.', 'p', '.', '.', '.', '.'}, {'.', '.', '.', '.', '.', '.', '.', '.'}, {'.', '.', '.', '.', '.', '.', '.', '.'}};
        ans = numRookCaptures(data1);
        assert ans == 3 : ans;
        ans = numRookCaptures2(data1);
        assert ans == 3 : ans;

        char[][] data2 = {{'.', '.', '.', '.', '.', '.', '.', '.'}, {'.', 'p', 'p', 'p', 'p', 'p', '.', '.'}, {'.', 'p', 'p', 'B', 'p', 'p', '.', '.'}, {'.', 'p', 'B', 'R', 'B', 'p', '.', '.'}, {'.', 'p', 'p', 'B', 'p', 'p', '.', '.'}, {'.', 'p', 'p', 'p', 'p', 'p', '.', '.'}, {'.', '.', '.', '.', '.', '.', '.', '.'}, {'.', '.', '.', '.', '.', '.', '.', '.'}};
        ans = numRookCaptures(data2);
        assert ans == 0 : ans;
        ans = numRookCaptures2(data2);
        assert ans == 0 : ans;

        char[][] data3 = {{'.', '.', '.', '.', '.', '.', '.', '.'}, {'.', '.', '.', 'p', '.', '.', '.', '.'}, {'.', '.', '.', 'p', '.', '.', '.', '.'}, {'p', 'p', '.', 'R', '.', 'p', 'B', '.'}, {'.', '.', '.', '.', '.', '.', '.', '.'}, {'.', '.', '.', 'B', '.', '.', '.', '.'}, {'.', '.', '.', 'p', '.', '.', '.', '.'}, {'.', '.', '.', '.', '.', '.', '.', '.'}};
        ans = numRookCaptures(data3);
        assert ans == 3 : ans;
        ans = numRookCaptures2(data3);
        assert ans == 3 : ans;
    }
}

