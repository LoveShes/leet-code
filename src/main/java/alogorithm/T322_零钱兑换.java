package alogorithm;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/coin-change/
 */
public class T322_零钱兑换 {

    // 动态规划
    public int coinChange(int[] coins, int amount) {
        if (amount < 1) return 0;
        if (coins == null || coins.length == 0) return -1;
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i < coin) continue;
                if (dp[i - coin] < 0 || dp[i - coin] >= min) continue;
                min = dp[i - coin];
            }
            if (min == Integer.MAX_VALUE) {
                dp[i] = -1;
            } else {
                dp[i] = min + 1;
            }
        }
        return dp[amount];
    }

    @Test
    public void test() {
        System.out.println(coinChange(new int[]{2}, 3));
    }
}
