package offer;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/
 */
public class T59I_滑动窗口的最大值 {
    /**
     * 【暴力法】
     * 执行用时 :45 ms, 在所有 Java 提交中击败了24.22% 的用户
     * 内存消耗 :49.4 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[]{};
        int len = nums.length - k + 1;
        int[] max = new int[len];
        for (int i = 0; i < len; i++) {
            max[i] = findMax(nums, i, i + k);
        }
        return max;
    }

    /**
     * 【暴力法优化】
     * 执行用时 :2 ms, 在所有 Java 提交中击败了96.89% 的用户
     * 内存消耗 :49.3 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[]{};
        int len = nums.length - k + 1;
        int[] max = new int[len];

        // 前k的最大值
        int firstMax = nums[0];
        for (int i = 0; i < k; i++) {
            if (nums[i] > firstMax) {
                firstMax = nums[i];
            }
        }
        max[0] = firstMax;

        // 开始滑动窗口
        for (int i = k; i < nums.length; i++) {
            if (nums[i] >= firstMax) {
                firstMax = nums[i];
            } else if (nums[i - k] == firstMax) { // 最大值滑出窗口
                firstMax = findMax(nums, i - k + 1, i + 1);
            }
            max[i - k + 1] = firstMax;
        }
        return max;
    }

    private int findMax(int[] nums, int start, int end) {
        int max = nums[start];
        for (int i = start + 1; i < end; i++) {
            if (nums[i] > max) max = nums[i];
        }
        return max;
    }

    /**
     * 【双端队列】
     * 执行用时 :15 ms, 在所有 Java 提交中击败了66.35% 的用户
     * 内存消耗 :48.9 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int[] maxSlidingWindow3(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[]{};
        int[] max = new int[nums.length - k + 1];

        Deque<Integer> queue = new ArrayDeque<>(); // queue是降序排列，队头放着最大值
        for (int i = 0, j = 0; i < nums.length; i++) {
            if (!queue.isEmpty() && i - queue.peek() >= k) { // 最大值已经滑出去了
                queue.poll(); // 删除最大值
            }
            while (!queue.isEmpty() && nums[i] > nums[queue.peekLast()]) {
                queue.pollLast(); // 前面小的已经不需要了
            }
            queue.offer(i);
            if (i >= k - 1) {
                max[j++] = nums[queue.peek()];
            }
        }
        return max;
    }

    /**
     * 【动态规划】
     * 执行用时 :5 ms, 在所有 Java 提交中击败了84.46% 的用户
     * 内存消耗 :49.1 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int[] maxSlidingWindow4(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[]{};
        int len = nums.length;

        /**
         * 分块，每块k个元素
         * 从左到右遍历数组，建立数组 left，其中 left[j] 是从块的开始到下标 j 最大的元素，方向 左->右
         * 从右到左遍历数组，建立数组 right，其中 right[j] 是从块的结尾到下标 j 最大的元素，方向 右->左
         * 建立输出数组 max(right[i], left[i + k - 1])，其中 i 取值范围为 (0, n - k + 1)
         */
        int[] left = new int[len];
        left[0] = nums[0];
        int[] right = new int[len];
        right[len - 1] = nums[len - 1];

        for (int i = 1; i < nums.length; i++) {
            // 生成left数组
            if (i % k == 0) {
                left[i] = nums[i];
            } else {
                left[i] = Math.max(left[i - 1], nums[i]); // 拿nums[i]和前面的最大值进行比较
            }
            // 生成right数组
            int j = len - i - 1;
            if ((j + 1) % k == 0) {
                right[j] = nums[j];
            } else {
                right[j] = Math.max(right[j + 1], nums[j]);
            }
        }

        // 得到结果
        int[] max = new int[nums.length - k + 1];
        for (int i = 0; i < len - k + 1; i++) {
            max[i] = Math.max(left[i + k - 1], right[i]);
        }
        return max;
    }
    
    @Test
    public void test() {
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{}, 1))); // []
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1}, 1))); // [1]
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3))); // [3,3,5,5,6,7]
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{2, 3, 4, 2, 6, 2, 5, 1}, 3))); // [4,4,6,6,6,5]
    }

    @Test
    public void test2() {
        System.out.println(Arrays.toString(maxSlidingWindow2(new int[]{}, 1))); // []
        System.out.println(Arrays.toString(maxSlidingWindow2(new int[]{1}, 1))); // [1]
        System.out.println(Arrays.toString(maxSlidingWindow2(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3))); // [3,3,5,5,6,7]
        System.out.println(Arrays.toString(maxSlidingWindow2(new int[]{2, 3, 4, 2, 6, 2, 5, 1}, 3))); // [4,4,6,6,6,5]
    }

    @Test
    public void test3() {
        System.out.println(Arrays.toString(maxSlidingWindow3(new int[]{}, 1))); // []
        System.out.println(Arrays.toString(maxSlidingWindow3(new int[]{1}, 1))); // [1]
        System.out.println(Arrays.toString(maxSlidingWindow3(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3))); // [3,3,5,5,6,7]
        System.out.println(Arrays.toString(maxSlidingWindow3(new int[]{2, 3, 4, 2, 6, 2, 5, 1}, 3))); // [4,4,6,6,6,5]
    }

    @Test
    public void test4() {
        System.out.println(Arrays.toString(maxSlidingWindow4(new int[]{}, 1))); // []
        System.out.println(Arrays.toString(maxSlidingWindow4(new int[]{1}, 1))); // [1]
        System.out.println(Arrays.toString(maxSlidingWindow4(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3))); // [3,3,5,5,6,7]
        System.out.println(Arrays.toString(maxSlidingWindow4(new int[]{2, 3, 4, 2, 6, 2, 5, 1}, 3))); // [4,4,6,6,6,5]
    }
}
