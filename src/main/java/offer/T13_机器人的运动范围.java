package offer;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/
 */
public class T13_机器人的运动范围 {

    /**
     * 【回溯法】
     * 执行用时 :1 ms, 在所有 Java 提交中击败了99.14% 的用户
     * 内存消耗 :36.1 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int movingCount(int m, int n, int k) {
        if (m <= 0 || n <= 0 || k < 0) return 0;
        boolean[][] visited = new boolean[m][n];
        return move(m, n, k, 0, 0, visited);
    }

    private int move(int m, int n, int k, int i, int j, boolean[][] visited) {
        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j] || digitAdd(i, j) > k) return 0;
        visited[i][j] = true;

        // 这里的1指的是当前位置
        int count = 1 + move(m, n, k, i, j + 1, visited)
                + move(m, n, k, i, j - 1, visited)
                + move(m, n, k, i + 1, j, visited)
                + move(m, n, k, i - 1, j, visited);

        return count;
    }

    private int digitAdd(int num1, int num2) {
        int res = 0;
        while (num1 > 0) {
            res += num1 % 10;
            num1 /= 10;
        }
        while (num2 > 0) {
            res += num2 % 10;
            num2 /= 10;
        }
        return res;
    }

    // 本题有m、n最多2位数，故可以特殊处理
    private int digitAdd2(int num1, int num2) {
        return num1 / 10 + num1 % 10 + num2 / 10 + num2 % 10;
    }

    @Test
    public void test() {
        System.out.println(movingCount(1, 1, 0)); // 1
        System.out.println(movingCount(2, 3, 1)); // 3
        System.out.println(movingCount(3, 1, 0)); // 1
        System.out.println(movingCount(16, 8, 4)); // 15
    }
}
