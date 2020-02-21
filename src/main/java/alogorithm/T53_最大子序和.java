package array;

import org.junit.jupiter.api.Test;

/**
 * 动态规划
 * https://leetcode-cn.com/problems/maximum-subarray/
 */
public class T53_最大子序和 {

    // 【运行超时】O(n^3)
    public int maxSubArray2(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int begin = 0; begin < nums.length; begin++) {
            for (int end = begin; end < nums.length; end++) {
                int sum = 0;
                for (int i = begin; i <= end; i++) {
                    sum += nums[i];
                }
                if (sum > max) {
                    max = sum;
                }
            }
        }
        return max;
    }

    // 【时间复杂度】O(n^2)
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int begin = 0; begin < nums.length; begin++) {
            int sum = 0;
            for (int end = begin; end < nums.length; end++) {
                sum += nums[end];
                if (sum > max) {
                    max = sum;
                }
            }
        }
        return max;
    }

    // 采用分治算法 T(n) = 2T(n/2) + O(n)
    // 【时间复杂度】O(nlogn)
    public int maxSubArray3(int[] nums) {
        return maxSubArray(nums, 0, nums.length);
    }

    private int maxSubArray(int[] nums, int begin, int end) {
        if (end - begin < 2) return nums[begin];
        int mid = (begin + end) >> 1;

        int leftMax = nums[mid - 1];
        int leftSum = 0;
        for (int i = mid - 1; i >= begin; i--) {
            leftSum += nums[i];
            leftMax = Math.max(leftMax, leftSum);
        }

        int rightMax = nums[mid];
        int rightSum = 0;
        for (int i = mid; i < end; i++) {
            rightSum += nums[i];
            rightMax = Math.max(rightMax, rightSum);
        }

        int max = leftMax + rightMax;
        return Math.max(max, Math.max(maxSubArray(nums, begin, mid), maxSubArray(nums, mid, end)));
    }

    // 动态规划（优化dp数组）
    //【时间复杂度】O(n)     【空间复杂度】O(1)
    public int maxSubArray4(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int dp = nums[0];
        int max = dp;
        for (int i = 1; i < nums.length; i++) {
            if (dp <= 0) {
                dp = nums[i];
            } else {
                dp = dp + nums[i];
            }
            max = Math.max(dp, max);
        }
        return max;
    }

    // 参考解法
    public int maxSubArray5(int[] nums) {
        int Maxsum = nums[0], sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum > Maxsum) Maxsum = sum;
            // 前面计算的结果如果小于0，后面无论再加一个怎样的数字，最后的和都会变的更小。所以就是sum<0就无增益效果，所以需要把前面这一段舍弃掉。从下一个数字重新开始。
            if (sum < 0) sum = 0;
        }
        return Maxsum;
    }

    @Test
    public void test() {
        System.out.println(maxSubArray4(new int[]{-1, 2, -7}));
        System.out.println(maxSubArray4(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }
}
