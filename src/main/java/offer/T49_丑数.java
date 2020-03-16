package offer;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/chou-shu-lcof/
 */
public class T49_丑数 {
    /**
     * 【暴力】
     * 超出时间限制
     */
    public int nthUglyNumber(int n) {
        int count = 0;
        int num = 1;
        while (true) {
            if (isUgly(num)) count++;
            if (count == n) {
                return num;
            }
            num++;
        }
    }

    private boolean isUgly(int num) {
        while (num % 5 == 0) {
            num = num / 5;
        }
        while (num % 3 == 0) {
            num = num / 3;
        }
        while (num % 2 == 0) {
            num = num / 2;
        }
        return num == 1;
    }

    /**
     * 【动态规划】
     * 一个丑数一定是由比它小的丑数×2，×3，×5得到的，相当于下面三个有序数组合并
     * A：{1*2，2*2，3*2，4*2，5*2，6*2，8*2，10*2......}
     * B：{1*3，2*3，3*3，4*3，5*3，6*3，8*3，10*3......}
     * C：{1*5，2*5，3*5，4*5，5*5，6*5，8*5，10*5......}
     * 每次一定是从中挑最小的，挑完了之后，下次就从后面开始挑（指针++），如果有多个相同的，要都往后挪一位
     * <p>
     * 执行用时 :3 ms, 在所有 Java 提交中击败了89.78% 的用户
     * 内存消耗 :37.4 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int nthUglyNumber2(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int p2 = 0, p3 = 0, p5 = 0;
        for (int i = 1; i < n; i++) {
            dp[i] = Math.min(dp[p2] * 2, Math.min(dp[p3] * 3, dp[p5] * 5));
            // 可能会存在相同的元素，所以都要往后挪一位，这里不能写成if...else
            if (dp[i] == dp[p2] * 2) p2++;
            if (dp[i] == dp[p3] * 3) p3++;
            if (dp[i] == dp[p5] * 5) p5++;
        }
        return dp[n - 1];
    }

    @Test
    public void test() {
        assert nthUglyNumber(1) == 1 : nthUglyNumber(1);
        assert nthUglyNumber(10) == 12 : nthUglyNumber(10);

        assert nthUglyNumber2(1) == 1 : nthUglyNumber2(1);
        assert nthUglyNumber2(10) == 12 : nthUglyNumber2(10);
    }
}
