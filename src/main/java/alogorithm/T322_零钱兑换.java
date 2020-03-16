package alogorithm;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/coin-change/
 */
public class T322_零钱兑换 {

    /**
     * 【动态规划】
     * 执行用时 :27 ms, 在所有 Java 提交中击败了17.15% 的用户
     * 内存消耗 :40.7 MB, 在所有 Java 提交中击败了5.08%的用户
     */
    public int coinChange(int[] coins, int amount) {
        if (amount < 1) return 0;
        if (coins == null || coins.length == 0) return -1;
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) { // i为钱数
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (i < coin) continue; // 面值太大，放弃该coin
                if (dp[i - coin] < 0 || dp[i - coin] >= min) continue; // 放进去coin后剩下的凑不了，或者放进去该coin导致钱币数过大
                min = dp[i - coin]; // 更新放进去coin后，剩下的最少钱币数
            }
            if (min == Integer.MAX_VALUE) {
                dp[i] = -1;
            } else {
                dp[i] = min + 1;
            }
        }
        return dp[amount];
    }

    /**
     * 【DFS】
     * 能用大硬币找开的，就不用小硬币
     * 执行用时 :4 ms, 在所有 Java 提交中击败了97.33% 的用户
     * 内存消耗 :37.3 MB, 在所有 Java 提交中击败了5.08%的用户
     */
    public int coinChange2(int[] coins, int amount) {
        if (amount < 1) return 0;
        if (coins == null || coins.length == 0) return -1;
        Arrays.sort(coins); // 升序
        int min = Integer.MAX_VALUE;
        min = dfs(coins, coins.length - 1, amount, 0, min);
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    /**
     * @param coins   升序的硬币列表
     * @param index   当前取出的硬币索引
     * @param remains 需要凑的钱数
     * @param count   硬币个数
     * @param min     最少硬币数
     * @return
     */
    private int dfs(int[] coins, int index, int remains, int count, int min) {
        if (index < 0) return min;
        for (int c = remains / coins[index]; c >= 0; c--) {
            int newRemains = remains - c * coins[index];
            int newCount = count + c;
            if (newRemains == 0) { // 刚好能用当前硬币填满
                min = Math.min(min, newCount);
                break; // 剪枝1
            }
            if (newCount + 1 > min) {
                break; // 剪枝2
            }
            min = dfs(coins, index - 1, newRemains, newCount, min);
        }
        return min;
    }

    @Test
    public void test() {
        System.out.println(coinChange(new int[]{2}, 3));
        System.out.println(coinChange(new int[]{1, 2, 5}, 11));
        System.out.println(coinChange(new int[]{1, 2, 5}, 101));
        System.out.println(coinChange(new int[]{1, 2, 5, 17}, 101));

        System.out.println(coinChange2(new int[]{2}, 3));
        System.out.println(coinChange2(new int[]{1, 2, 5}, 11));
        System.out.println(coinChange2(new int[]{1, 2, 5}, 101));
        System.out.println(coinChange2(new int[]{1, 2, 5, 17}, 101));
        System.out.println(coinChange2(new int[]{1, 17, 18}, 34));
    }
}
