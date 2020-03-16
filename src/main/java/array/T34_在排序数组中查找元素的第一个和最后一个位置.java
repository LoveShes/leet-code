package array;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 */
public class T34_在排序数组中查找元素的第一个和最后一个位置 {
    /**
     * 要注意处理找不到的情况
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :42.9 MB, 在所有 Java 提交中击败了45.43%的用户
     */
    public int[] searchRange(int[] nums, int target) {
        int[] ans = {-1, -1};
        if (nums == null || nums.length == 0) return ans;
        int firstEqualsIndex = search(nums, target, false);
        // 要注意找不到的情况
        if (firstEqualsIndex >= nums.length || nums[firstEqualsIndex] != target) return ans;
        ans[0] = firstEqualsIndex;
        // 前面已经找到了，则这里一定的大于该元素的索引（如果没有则为数组的溢出边界）
        ans[1] = search(nums, target, true) - 1;
        return ans;
    }

    private int search(int[] nums, int target, boolean greated) {
        int begin = 0;
        int end = nums.length;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (target > nums[mid] || greated && target == nums[mid]) {
                begin = mid + 1;
            } else {
                end = mid;
            }
        }
        return begin;
    }

    @Test
    public void test() {
        int[] data = {5, 7, 7, 8, 8, 10};
        assert Arrays.equals(searchRange(data, 8), new int[]{3, 4});
        assert Arrays.equals(searchRange(data, 6), new int[]{-1, -1}) : Arrays.toString(searchRange(data, 6));
    }
}
