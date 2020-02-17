package array;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/remove-element/
 */
public class T27_移除元素 {
    public int removeElement(int[] nums, int val) {
        int len = nums.length;
        int i = 0;
        while (i < len) {
            if (nums[i] == val) {
                len--;
                nums[i] = nums[len];
            } else {
                i++;
            }
        }
        return len;
    }

    @Test
    public void test() {
        int[] nums = new int[]{0, 1, 2, 2, 3, 0, 4, 2};
        System.out.println(removeElement(nums, 2));
        System.out.println(Arrays.toString(nums));
    }
}
