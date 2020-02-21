package alogorithm;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/longest-common-subsequence/
 */
public class T1143_最长公共子序列 {
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null) return 0;

        char[] nums1 = text1.toCharArray();
        if (nums1.length == 0) return 0;
        char[] nums2 = text2.toCharArray();
        if (nums2.length == 0) return 0;

        char[] rowNums = nums1;
        char[] colsNums = nums2;
        if (nums1.length < nums2.length) {
            rowNums = nums2;
            colsNums = nums1;
        }

        int[] dp = new int[colsNums.length + 1];
        for (int i = 1; i <= rowNums.length; i++) {
            int cur = 0;
            for (int j = 1; j <= colsNums.length; j++) {
                int leftTop = cur;
                cur = dp[j];
                if (rowNums[i - 1] == colsNums[j - 1]) {
                    dp[j] = leftTop + 1;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
            }
        }
        return dp[colsNums.length];
    }

    // 动态规划
    public int lcs(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) return 0;
        if (nums2 == null || nums2.length == 0) return 0;
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[nums1.length][nums2.length];
    }

    /*
     * 动态规划
     * 【优化】滚动数组
     */
    public int lcs2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) return 0;
        if (nums2 == null || nums2.length == 0) return 0;
        int[][] dp = new int[2][nums2.length + 1];
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i & 1][j] = dp[(i - 1) & 1][j - 1] + 1;
                } else {
                    dp[i & 1][j] = Math.max(dp[(i - 1) & 1][j], dp[i & 1][j - 1]);
                }
            }
        }
        return dp[nums1.length & 1][nums2.length];
    }

    /*
     * 动态规划
     * 【优化】一维数组
     */
    public int lcs3(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) return 0;
        if (nums2 == null || nums2.length == 0) return 0;
        // 小的当行，大的当列
        // 默认nums2更短
        int[] rowNums = nums1;
        int[] colsNums = nums2;
        // 如果nums1更短
        if (nums1.length < nums2.length) {
            colsNums = nums2;
            rowNums = nums1;
        }
        int[] dp = new int[colsNums.length + 1];  // 这样可以节省空间
        for (int i = 1; i <= rowNums.length; i++) {
            int cur = 0;
            for (int j = 1; j <= colsNums.length; j++) {
                int leftTop = cur;
                cur = dp[j];
                if (rowNums[i - 1] == colsNums[j - 1]) {
                    dp[j] = leftTop + 1;
                } else {
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                }
            }
        }
        return dp[colsNums.length];
    }

    // 递归写法
    public int lcs4(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) return 0;
        if (nums2 == null || nums2.length == 0) return 0;
        return lcs4(nums1, nums1.length, nums2, nums2.length);
    }

    private int lcs4(int[] nums1, int i, int[] nums2, int j) {
        if (i == 0 || j == 0) return 0;
        if (nums1[i - 1] == nums2[j - 1]) {
            return lcs4(nums1, i - 1, nums2, j - 1) + 1;
        }
        return Math.max(lcs4(nums1, i - 1, nums2, j), lcs4(nums1, i, nums2, j - 1));
    }

    @Test
    public void test() {
        String text1 = "abcc";
        String text2 = "bca";
        System.out.println(longestCommonSubsequence(text1, text2));
    }

    @Test
    public void test2() {
        int[] nums1 = {1, 3, 5, 9, 10};
        int[] nums2 = {1, 4, 9, 10};
        System.out.println(lcs3(nums1, nums2));
    }
}
