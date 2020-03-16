package offer;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/
 */
public class T53I_在排序数组中查找数字I {
    /**
     * 【简单二分】
     * 二分查找找到后，往左往右扫
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :42.4 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        int begin = 0;
        int end = nums.length;
        int mid = 0;
        while (begin < end) {
            mid = (begin + end) >> 1;
            if (target > nums[mid]) {
                begin = mid + 1;
            } else if (target < nums[mid]) {
                end = mid;
            } else {
                break;
            }
        }

        if (begin == end) return 0;
        int count = 1;
        // 往左
        int index = mid - 1;
        while (index >= 0) {
            if (nums[index] != target) break;
            count++;
            index--;
        }
        index = mid + 1;
        while (index < nums.length) {
            if (nums[index] != target) break;
            count++;
            index++;
        }
        return count;
    }

    /**
     * 【第一个大于target的位置 - 第一个等于target的位置】
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :42.6 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int search2(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        return findFirstOverTarget(nums, target) - findFirstEqualTarget(nums, target);
    }

    private int findFirstOverTarget(int[] nums, int target) {
        int begin = 0;
        int end = nums.length;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (target >= nums[mid]) {
                begin = mid + 1;
            } else {
                end = mid;
            }
        }
        return begin;
    }

    private int findFirstEqualTarget(int[] nums, int target) {
        int begin = 0;
        int end = nums.length;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (target > nums[mid]) {
                begin = mid + 1;
            } else {
                end = mid;
            }
        }
        return begin;
    }

    /**
     * 【上面的代码可以合并成一个】
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :42.4 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int search3(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        return binarySearch(nums, target, true) - binarySearch(nums, target, false);
    }

    private int binarySearch(int[] nums, int target, boolean greater) {
        int begin = 0;
        int end = nums.length;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (target > nums[mid] || greater && target == nums[mid]) {
                begin = mid + 1;
            } else {
                end = mid;
            }
        }
        return begin;
    }

    @Test
    public void test() {
        int[] data = {1, 2, 3, 3, 3, 3, 4, 5, 6};
        assert search(data, 3) == 4 : search(data, 3);
        assert search2(data, 3) == 4 : search2(data, 3);
        assert search3(data, 3) == 4 : search3(data, 3);

        System.out.println(findFirstOverTarget(data, 3));
        System.out.println(findFirstEqualTarget(data, 3));
        // 找不到的话，两者的返回值是一样的
        System.out.println(findFirstOverTarget(data, 0));
        System.out.println(findFirstEqualTarget(data, 0));
        System.out.println(findFirstOverTarget(data, 7));
        System.out.println(findFirstEqualTarget(data, 7));
    }
}
