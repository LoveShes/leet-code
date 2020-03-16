package offer;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/gu-piao-de-zui-da-li-run-lcof/
 */
public class T63_股票的最大利润 {
    /**
     * 【暴力】
     * 执行用时 :483 ms, 在所有 Java 提交中击败了5.02% 的用户
     * 内存消耗 :43 MB, 在所有 Java 提交中击败了5.02%的用户
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;
        int price = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] - prices[i] > price) price = prices[j] - prices[i];
            }
        }
        return price;
    }

    /**
     * 【动态规划】
     * 动态规划前i天的最大收益 = max{前i-1天的最大收益，第i天的价格-前i-1天中的最小价格}
     * 执行用时 :1 ms, 在所有 Java 提交中击败了99.57% 的用户
     * 内存消耗 :42.8 MB, 在所有 Java 提交中击败了5.02%的用户
     */
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length < 2) return 0;
        int[] dp = new int[prices.length];
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > min) {
                dp[i] = Math.max(dp[i - 1], prices[i] - min);
            } else {
                dp[i] = dp[i - 1];
                min = prices[i];
            }
        }
        return dp[prices.length - 1];
    }

    /**
     * 【动态规划——优化dp数组】
     * 由于只需要dp数组的上一个元素，所以dp数组可以优化为一个变量
     * 执行用时 :1 ms, 在所有 Java 提交中击败了99.57% 的用户
     * 内存消耗 :43.1 MB, 在所有 Java 提交中击败了5.02%的用户
     */
    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length < 2) return 0;
        int min = prices[0];
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            } else if (prices[i] - min > maxprofit) {
                maxprofit = prices[i] - min;
            }
        }
        return maxprofit;
    }


    @Test
    public void test() {
        System.out.println(maxProfit2(new int[]{7, 1, 5, 3, 6, 4})); // 5
        System.out.println(maxProfit2(new int[]{7, 6, 4, 3, 1})); // 0
        System.out.println(maxProfit2(new int[]{2, 4, 1})); // 2

        System.out.println(maxProfit3(new int[]{7, 1, 5, 3, 6, 4})); // 5
        System.out.println(maxProfit3(new int[]{7, 6, 4, 3, 1})); // 0
        System.out.println(maxProfit3(new int[]{2, 4, 1})); // 2
    }
}
