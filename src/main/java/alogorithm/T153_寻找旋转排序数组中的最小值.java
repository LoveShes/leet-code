package alogorithm;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/description/
 */
public class T153_寻找旋转排序数组中的最小值 {
    /**
     * 【二分查找】
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :38.1 MB, 在所有 Java 提交中击败了22.77%的用户
     */
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = (start + end) >> 1;
            if (nums[mid] > nums[end]) {
                start = mid + 1;
            } else { // 注意本题不会出现重复元素，所有不用考虑相等的情况
                end = mid;
            }
        }
        return nums[start];
    }

    @Test
    public void test() {
        System.out.println(findMin(new int[]{3, 4, 5, 1, 2})); // 1
        System.out.println(findMin(new int[]{4, 5, 6, 7, 0, 1, 2})); // 1
    }
}
