package cracking_the_coding_interview;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/the-masseuse-lcci/
 */
public class T17_16按摩师 {
    /**
     * 【动态规划】
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :37.2 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int massage(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = nums[i] + (i >= 2 ? dp[i - 2] : 0);
            dp[i] = Math.max(dp[i], i >= 1 ? dp[i - 1] : 0);
        }
        return dp[nums.length - 1];
    }

    /**
     * 【动态规划-优化特殊情况的判断】
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :37.2 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int massage2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length + 1];
        dp[1] = nums[0];
        for (int i = 2; i <= nums.length; i++) {
            dp[i] = Math.max(nums[i - 1] + dp[i - 2], dp[i - 1]);
        }
        return dp[nums.length];
    }

    /**
     * 【动态规划-优化dp数组】
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :37.2 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int massage3(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int dpminus1 = nums[0];
        int dpminus2 = 0;
        for (int i = 2; i <= nums.length; i++) {
            int dp = Math.max(nums[i - 1] + dpminus2, dpminus1);
            dpminus2 = dpminus1;
            dpminus1 = dp;
        }
        return dpminus1;
    }

    @Test
    public void test() {
        System.out.println(massage(new int[]{1, 2, 3, 1})); // 4
        System.out.println(massage(new int[]{2, 7, 9, 3, 1})); // 12
        System.out.println(massage(new int[]{2, 1, 4, 5, 3, 1, 1, 3})); // 12

        System.out.println(massage2(new int[]{1, 2, 3, 1})); // 4
        System.out.println(massage2(new int[]{2, 7, 9, 3, 1})); // 12
        System.out.println(massage2(new int[]{2, 1, 4, 5, 3, 1, 1, 3})); // 12

        System.out.println(massage3(new int[]{1, 2, 3, 1})); // 4
        System.out.println(massage3(new int[]{2, 7, 9, 3, 1})); // 12
        System.out.println(massage3(new int[]{2, 1, 4, 5, 3, 1, 1, 3})); // 12
    }
}
