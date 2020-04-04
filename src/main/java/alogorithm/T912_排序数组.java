package alogorithm;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 复习各种排序算法的好时机
 * https://leetcode-cn.com/problems/sort-an-array/
 */
public class T912_排序数组 {
    /**
     *
     */
    public int[] sortArray(int[] nums) {
        Arrays.sort(nums);
        return nums;
    }

    @Test
    public void test() {
        int[] data1a = {1, 3, 4, 5, 12, 44, 1, 98, 1, 0, 12, 13};
        int[] data1b = Arrays.copyOf(data1a, data1a.length);
        System.out.println(Arrays.toString(sortArray(data1a)));
    }
}
