package offer;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
 */
public class T51_数组中的逆序对 {
    /**
     * 【暴力】
     * 超出时间限制
     */
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (nums[i] > nums[j]) count++;
            }
        }
        return count;
    }

    /**
     * TODO【归并排序】
     */
    public int reversePairs2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int count = 0;
        return count;
    }


    @Test
    public void test() {
        System.out.println(reversePairs(new int[]{7, 5, 6, 4})); // 5
        System.out.println(reversePairs2(new int[]{7, 5, 6, 4})); // 5
    }
}
