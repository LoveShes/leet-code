package offer;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/comments/
 */
public class T03_数组中重复的数字 {

    /**
     * 【暴力解法】
     * 执行用时 :2755 ms, 在所有 Java 提交中击败了5.07% 的用户
     * 内存消耗 :50.9 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int findRepeatNumber(int[] nums) {
        if (nums == null) return -1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == nums[i]) {
                    return nums[i];
                }
            }
        }
        return -1;
    }

    /**
     * 【使用哈希表】
     * 执行用时 :11 ms, 在所有 Java 提交中击败了31.69% 的用户
     * 内存消耗 :53.1 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int findRepeatNumber2(int[] nums) {
        if (nums == null) return -1;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) return nums[i];
            set.add(nums[i]);
        }
        return -1;
    }

    /**
     * 【使用计数排序】
     * 要重复利用题目中的条件：在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
     * 刚好符合计数排序的使用条件
     * 时间复杂度 O(n)，空间复杂度 O(n)。无副作用
     * 执行用时 :2 ms, 在所有 Java 提交中击败了71.12% 的用户
     * 内存消耗 :50.6 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int findRepeatNumber3(int[] nums) {
        if (nums == null) return -1;
        int len = nums.length;
        int[] times = new int[len];
        for (int i = 0; i < len; i++) {
            times[nums[i]]++;
            if (times[nums[i]] > 1) return nums[i];
        }
        return -1;
    }

    /**
     * 【原地计数排序】
     * 再进一步，看能不能原地排序
     * 时间复杂度 O(n)，空间复杂度 O(1)。缺点，有副作用
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :50.4 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int findRepeatNumber4(int[] nums) {
        if (nums == null) return -1;
        int len = nums.length;
        int i = 0;
        while (i < len) {
            if (nums[i] == i) {
                i++;
            } else {
                if (nums[i] == nums[nums[i]]) return nums[i];
                int temp = nums[i];
                nums[i] = nums[temp];
                nums[temp] = temp;
                // 交换nums[i]与nums[nums[i]]也可以使用
                // nums[i] = nums[nums[i]] + (nums[nums[i]] = nums[i]) * 0;
            }
        }
        return -1;
    }

    @Test
    public void test() {
        int[] nums = {2, 3, 1, 0, 2, 5, 3};
        int result = findRepeatNumber4(nums);
        assert result == 2 || result == 3;
    }

    @Test
    public void test2() {
        int[] nums = {0, 1, 2, 3, 4, 11, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}; // 16
        int result = findRepeatNumber4(nums);
        assert result == 11;
        System.out.println(result);
    }

    @Test
    public void test3() {
        int[] nums = {2, 3, 1, 0, 2, 5, 3}; // 7
        int result = findRepeatNumber4(nums);
        // assert result == 2;
        System.out.println(result);
    }

    @Test
    public void test4() {
        int[] nums = {0, 1, 2, 0, 4, 5, 6, 7, 8, 9};
        int result = findRepeatNumber4(nums);
        // assert result == 0;
        System.out.println(result);
    }

    // 此外，交换a和b变量的值如果要求不允许借助其它变量可以使用下面的方法
    @Test
    public void swap1() {
        double a = 10.1, b = 11;
        // System.out.println("a: " + a + ", b: " + b);
        b = a + (a = b) * 0; // 注意，这里一定要写成 b = a + xx 的形式，不能写成 b = xx + a；
        System.out.println("a: " + a + ", b: " + b);
    }

    @Test
    public void swap2() {
        int a = 10, b = 11;
        // System.out.println("a: " + a + ", b: " + b);
        a = a + b; // 注意，Java正数溢出会变成负数，负数溢出会变成正数，不影响该结果
        b = a - b;
        a = a - b;
        System.out.println("a: " + a + ", b: " + b);
    }

    @Test
    public void swap3() {
        int a = 10, b = 11;
        // System.out.println("a: " + a + ", b: " + b);
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        // System.out.println("a: " + a + ", b: " + b);
    }
}
