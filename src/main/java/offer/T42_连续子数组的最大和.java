package offer;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/
 */
public class T42_连续子数组的最大和 {
    /**
     * 【动态规划】
     * 执行用时 :2 ms, 在所有 Java 提交中击败了27.77% 的用户
     * 内存消耗 :46.4 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len + 1];
        int max = nums[0];
        for (int i = 1; i <= len; i++) {
            if (dp[i - 1] <= 0) { // 负数只会越加越小，所以果断抛弃
                dp[i] = nums[i - 1];
            } else {
                dp[i] = dp[i - 1] + nums[i - 1];
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * 【动态规划——优化dp数组】
     * 执行用时 :1 ms, 在所有 Java 提交中击败了98.07% 的用户
     * 内存消耗 :46 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int maxSubArray2(int[] nums) {
        int len = nums.length;
        int dp = nums[0];
        int max = nums[0];
        for (int i = 1; i < len; i++) {
            if (dp <= 0) { // 负数只会越加越小，所以果断抛弃
                dp = nums[i];
            } else {
                dp += nums[i];
            }
            max = Math.max(max, dp);
        }
        return max;
    }

    /**
     * 【贪心法】
     * 舍弃前面的负数和（因为负数加上任何数都只会更小）
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :46.2 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int maxSubArray3(int[] nums) {
        int len = nums.length;
        int max = nums[0];
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            if (sum > max) { // 要先比较，避免直接舍弃了负数
                max = sum;
            }
            if (sum < 0) { // 直接舍弃
                sum = 0;
            }
        }
        return max;
    }

    @Test
    public void test() {
        assert maxSubArray(new int[]{-1}) == -1;
        assert maxSubArray2(new int[]{-1}) == -1;
        assert maxSubArray3(new int[]{-1}) == -1;

        assert maxSubArray(new int[]{1}) == 1;
        assert maxSubArray2(new int[]{1}) == 1;
        assert maxSubArray3(new int[]{1}) == 1;

        assert maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}) == 6;
        assert maxSubArray2(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}) == 6;
        assert maxSubArray3(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}) == 6;
    }
}
