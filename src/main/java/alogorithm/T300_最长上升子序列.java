package alogorithm;

import org.junit.jupiter.api.Test;

/**
 * 动态规划
 * https://leetcode-cn.com/problems/longest-increasing-subsequence/
 */
public class T300_最长上升子序列 {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        int max = 1;
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                // 不能拼接
                if (nums[i] <= nums[j]) continue;
                dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }

    // 牌堆
    public int lengthOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int len = 0;
        int[] top = new int[nums.length];
        for (int num : nums) {
            int j = 0;
            while (j < len) {
                if (top[j] >= num) {
                    top[j] = num;
                    break;
                }
                j++;
            }
            if (j == len) {
                len++;
                top[j] = num; // 新建牌堆
            }
        }
        return len;
    }

    // 二分搜索
    public int lengthOfLIS3(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int len = 0;
        int[] top = new int[nums.length];
        for (int num : nums) {
            int begin = 0;
            int end = len;
            while (begin < end) {
                int mid = (begin + end) >> 1;
                if (num <= top[mid]) {
                    end = mid;
                } else {
                    begin = mid + 1;
                }
            }
            // 覆盖牌堆
            top[begin] = num;
            if (begin == len) len++;
        }
        return len;
    }

    private int search(int index, int[] array) {
        int begin = 0;
        int end = index;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (array[index] <= array[mid]) {
                end = mid;
            } else {
                begin = mid + 1;
            }
        }
        return begin;
    }

    @Test
    public void test() {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lengthOfLIS(nums));
        System.out.println(lengthOfLIS3(nums));

    }
}
