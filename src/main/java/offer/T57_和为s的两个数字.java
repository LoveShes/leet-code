package offer;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

/**
 * https://leetcode-cn.com/problems/he-wei-sde-liang-ge-shu-zi-lcof/
 */
public class T57_和为s的两个数字 {
    /**
     * 【普通做法】
     * 会超时
     */
    public int[] twoSum(int[] nums, int target) {
        if (nums == null) return new int[]{};
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) return new int[]{nums[i], nums[j]};
            }
        }
        return new int[]{};
    }

    /**
     * 【使用HashSet】
     * 执行用时 :63 ms, 在所有 Java 提交中击败了10.96% 的用户
     * 内存消耗 :57.8 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int[] twoSum2(int[] nums, int target) {
        if (nums == null) return new int[]{};
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(target - nums[i])) return new int[]{nums[i], target - nums[i]};
            set.add(nums[i]);
        }
        return new int[]{};
    }

    /**
     * 【双指针】
     * 执行用时 :2 ms, 在所有 Java 提交中击败了99.48% 的用户
     * 内存消耗 :57.1 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int[] twoSum3(int[] nums, int target) {
        if (nums == null) return new int[]{};

        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                return new int[]{nums[left], nums[right]};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{};
    }

    /**
     * 【双指针-优化】
     * 执行用时 :1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :57.4 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int[] twoSum4(int[] nums, int target) {
        if (nums == null) return new int[]{};

        int left = 0;
        int right = nums.length - 1;

        while (nums[left] + nums[right] != target && left < right) {
            // 这样找的快一点，找到等于的就退出外层循环
            while (nums[left] + nums[right] < target) {
                left++;
            }
            while (nums[left] + nums[right] > target) {
                right--;
            }
        }
        if (left < right) return new int[]{nums[left], nums[right]};
        return new int[]{};
    }

    @Test
    public void test() {
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9))); // [2,7] 或者 [7,2]
        System.out.println(Arrays.toString(twoSum(new int[]{10, 26, 30, 31, 47, 60}, 40))); // [10,30] 或者 [30,10]
        System.out.println(Arrays.toString(twoSum(new int[]{16, 16, 18, 24, 30, 32}, 48))); // [16,32]

        System.out.println(Arrays.toString(twoSum2(new int[]{2, 7, 11, 15}, 9))); // [2,7] 或者 [7,2]
        System.out.println(Arrays.toString(twoSum2(new int[]{10, 26, 30, 31, 47, 60}, 40))); // [10,30] 或者 [30,10]
        System.out.println(Arrays.toString(twoSum2(new int[]{16, 16, 18, 24, 30, 32}, 48))); // [16,32]

        System.out.println(Arrays.toString(twoSum3(new int[]{2, 7, 11, 15}, 9))); // [2,7] 或者 [7,2]
        System.out.println(Arrays.toString(twoSum3(new int[]{10, 26, 30, 31, 47, 60}, 40))); // [10,30] 或者 [30,10]
        System.out.println(Arrays.toString(twoSum3(new int[]{16, 16, 18, 24, 30, 32}, 48))); // [16,32]

        System.out.println(Arrays.toString(twoSum4(new int[]{2, 7, 11, 15}, 9))); // [2,7] 或者 [7,2]
        System.out.println(Arrays.toString(twoSum4(new int[]{10, 26, 30, 31, 47, 60}, 40))); // [10,30] 或者 [30,10]
        System.out.println(Arrays.toString(twoSum4(new int[]{16, 16, 18, 24, 30, 32}, 48))); // [16,32]
    }
}
