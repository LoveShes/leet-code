package alogorithm;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 动态规划
 * https://leetcode-cn.com/problems/longest-increasing-subsequence/
 */
public class T300_最长上升子序列 {
    // 动态规划
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length]; // dp[i]表示以nums[i]结尾的最长上升子序列长度
        int max = 1;
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1; // 只包含自己
            for (int j = 0; j < i; j++) { // 遍历i前面的子序列
                if (nums[i] <= nums[j]) continue; // 不能拼接
                dp[i] = Math.max(dp[i], dp[j] + 1); // 可以拼接
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }

    /**
     * 【牌堆法】
     * 把每个数字看作是一张扑克牌，从左到右按顺序处理每一个扑克牌
     * 将它压在（从左往右）第一个(牌顶>=它)的牌堆上面
     * 如果找不到(牌顶>=它)的牌堆，就在最右边新建一个牌堆，将它放入新牌堆中
     * 处理完所有牌后，最终牌堆的数量就是最长上升子序列的长度
     */
    public int lengthOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        // 牌堆数量
        int len = 0;
        // 存放每个牌堆的牌顶元素
        int[] top = new int[nums.length];
        // 遍历所有牌
        for (int num : nums) {
            int j = 0;
            while (j < len) { // 遍历所有牌堆
                if (top[j] >= num) {
                    top[j] = num; // 更新牌顶
                    break;
                }
                j++;
            }
            // 新建牌堆
            if (j == len) {
                len++;
                top[j] = num;
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
            // 二分搜索
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
            // 覆盖牌顶
            top[begin] = num;
            // 到头了就新建牌堆
            if (begin == len) len++;
        }
        return len;
    }

    // 二分搜索——自定义search（自己的还快一点）
    public int lengthOfLIS4(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int len = 0;
        int[] top = new int[nums.length];
        for (int num : nums) {
            // 二分搜索，找到num元素的待插入位置
            int search = search(num, top, len);
            // int search = Arrays.binarySearch(top, 0, len, num);
            // 覆盖牌顶（反正都是要插入一个地方）
            top[search] = num;
            // 到头了就新建牌堆
            if (search == len) len++;
        }
        return len;
    }

    // 二分搜索——官方binarySearch
    public int lengthOfLIS5(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int len = 0;
        int[] top = new int[nums.length];
        for (int num : nums) {
            // 二分搜索，找到num元素的待插入位置
            int search = Arrays.binarySearch(top, 0, len, num);
            // 注意找不到的情况
            if (search < 0) search = -(search + 1);
            // 覆盖牌顶（反正都是要插入一个地方）
            top[search] = num;
            // 到头了就新建牌堆
            if (search == len) len++;
        }
        return len;
    }

    /**
     * 利用二分搜索找到num元素的待插入位置（第一个大于等于它的位置）
     * 已经排好序数组的区间范围是 [0,len)
     *
     * @param num   待插入的元素
     * @param array 数组
     * @param len   牌堆数量（Java数组没有切片，需要传入有效长度）
     * @return 要插入的位置
     */
    private int search(int num, int[] array, int len) {
        int begin = 0;
        int end = len;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (num <= array[mid]) {
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
        System.out.println(lengthOfLIS2(nums));
        System.out.println(lengthOfLIS3(nums));
        System.out.println(lengthOfLIS4(nums));
        System.out.println(lengthOfLIS5(nums));
    }

    @Test
    public void testBinarySearch() {
        int[] nums = {1, 2, 2, 2, 4, 5, 6, 7, 8, 9};
        System.out.println(Arrays.binarySearch(nums, 2)); // 找到第一个大于等于key的索引
        System.out.println(Arrays.binarySearch(nums, -1)); // 在边界之外
        System.out.println(Arrays.binarySearch(nums, 3)); // 找不到且范围内
        System.out.println(Arrays.binarySearch(nums, 10)); // 在边界之外

        // 如果index<0，则-(index+1)可以得到插入位置（第一个大于它的位置），要注意边界

    }
}
