package offer;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof/
 */
public class T12_矩阵中的路径 {
    /**
     * 【回溯法】
     * 执行用时 :6 ms, 在所有 Java 提交中击败了92.74% 的用户
     * 内存消耗 :42.6 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public boolean exist(char[][] board, String word) {
        if (board == null || word == null || word.length() == 0 || board.length == 0 || board[0].length == 0) {
            return false;
        }

        char[] chars = word.toCharArray();
        int rows = board.length;
        int cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (search(board, chars, 0, visited, row, col)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean search(char[][] board, char[] chars, int index, boolean[][] visited, int row, int col) {
        if (index == chars.length) return true;
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) return false;
        if (visited[row][col]) return false;
        if (board[row][col] == chars[index]) {
            visited[row][col] = true; // 该点作为路径的一部分才标记，不是遍历过就标记
        } else {
            return false;
        }

        index++;
        boolean searched = search(board, chars, index, visited, row, col - 1)
                || search(board, chars, index, visited, row - 1, col)
                || search(board, chars, index, visited, row, col + 1)
                || search(board, chars, index, visited, row + 1, col);
        if (!searched) {
            visited[row][col] = false; // 没找到说明该点不在路径中，重新标记为false
        }
        return searched;
    }

    /**
     * 【简化代码】
     * 执行用时 :6 ms, 在所有 Java 提交中击败了92.74% 的用户
     * 内存消耗 :42.7 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public boolean exist2(char[][] board, String word) {
        if (board == null || word == null || word.length() == 0 || board.length == 0 || board[0].length == 0) {
            return false;
        }

        char[] chars = word.toCharArray();
        int rows = board.length;
        int cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (search2(board, chars, 0, visited, row, col)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean search2(char[][] board, char[] chars, int index, boolean[][] visited, int row, int col) {
        if (index == chars.length) return true;
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length
                || visited[row][col] || board[row][col] != chars[index]) return false;

        visited[row][col] = true;
        index++;
        boolean searched = search2(board, chars, index, visited, row, col - 1)
                || search2(board, chars, index, visited, row - 1, col)
                || search2(board, chars, index, visited, row, col + 1)
                || search2(board, chars, index, visited, row + 1, col);

        visited[row][col] = searched; // 没找到说明该点不在路径中，重新标记为false
        return searched;
    }

    /**
     * 【DFS + 剪枝】
     * 基本原理一样，与回溯法不同的是把标记数组原地标记变量，事后恢复现场
     * 执行用时 :5 ms, 在所有 Java 提交中击败了98.15% 的用户
     * 内存消耗 :42.8 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public boolean exist3(char[][] board, String word) {
        char[] words = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (DFS(board, words, i, j, 0)) return true;
            }
        }
        return false;
    }

    private boolean DFS(char[][] board, char[] words, int i, int j, int k) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length || words[k] != board[i][j]) return false;
        if (k == words.length - 1) return true;

        char tmp = board[i][j]; // 先保存，处理完了再恢复
        board[i][j] = '/';
        boolean res = DFS(board, words, i + 1, j, k + 1) || DFS(board, words, i - 1, j, k + 1)
                || DFS(board, words, i, j - 1, k + 1) || DFS(board, words, i, j + 1, k + 1);
        board[i][j] = tmp;
        return res;
    }

    @Test
    public void test() {
        char[][] board = {
                {'a', 'b', 'c', 'e'},
                {'s', 'f', 'c', 's'},
                {'a', 'd', 'e', 'e'}
        };

        assert exist3(board, "bfce");
        assert !exist(board, "bfccs");
        assert !exist(board, "abfba");
        assert exist(board, "ba");
        assert !exist(board, "");
    }

    @Test
    public void test2() {
        char[][] board = {
                {'C', 'A', 'A'},
                {'A', 'A', 'A'},
                {'B', 'C', 'D'}
        };

        assert exist2(board, "AAB");
    }

    @Test
    public void test3() {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'E', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        assert exist2(board, "ABCESEEEFS");
    }

    @Test
    public void test4() {
        char[][] board = {
                {'a', 'a'}
        };
        assert !exist2(board, "aaa");
    }



}
