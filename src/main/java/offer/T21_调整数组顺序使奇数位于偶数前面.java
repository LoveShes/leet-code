package offer;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/
 */
public class T21_调整数组顺序使奇数位于偶数前面 {
    /**
     * 【双指针】快排找元素的思路
     * 将判断奇偶性的条件改成函数可使代码具有通用性（如将所有负数放到正数前面）
     * 执行用时 :2 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :49 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int[] exchange(int[] nums) {
        if (nums == null) return nums;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            while (left < right && (nums[left] & 1) == 1) { // 找到偶数为止
                left++;
            }
            while (left < right && (nums[right] & 1) == 0) { // 找到奇数为止
                right--;
            }
            nums[left] = nums[right] + 0 * (nums[right] = nums[left]); // 交换
            left++;
            right--;
        }
        return nums;
    }

    @Test
    public void test() {
        System.out.println(Arrays.toString(exchange(null))); // null
        System.out.println(Arrays.toString(exchange(new int[]{}))); // []
        System.out.println(Arrays.toString(exchange(new int[]{1, 2, 3, 4}))); // 1 3 2 4
        System.out.println(Arrays.toString(exchange(new int[]{1, 3, 5}))); // 1 3 5
        System.out.println(Arrays.toString(exchange(new int[]{2, 4, 6}))); // 2 4 6
        System.out.println(Arrays.toString(exchange(new int[]{2, 4, 6, 1}))); // 1 2 4 6
        System.out.println(Arrays.toString(exchange(new int[]{1, 2, 3, 3, 4}))); // 1 3 3 2 4
    }
}
