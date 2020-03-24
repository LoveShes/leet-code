package offer;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/bu-ke-pai-zhong-de-shun-zi-lcof/
 */
public class T61_扑克牌中的顺子 {
    /**
     * 【排序】
     * 执行用时 :1 ms, 在所有 Java 提交中击败了87.13% 的用户
     * 内存消耗 :37.4 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public boolean isStraight(int[] nums) {
        if (nums == null || nums.length < 5) return false;
        Arrays.sort(nums);
        int count0 = 0;
        int index = 0;
        // 先统计0的数量
        while (index < nums.length) {
            if (nums[index] == 0) {
                count0++;
            } else {
                break;
            }
            index++;
        }
        // last为第1个非0元素
        int last = nums[index++];
        while (index < nums.length) {
            if (nums[index] - last >= 2) {
                count0 -= (nums[index] - last - 1);
            } else if (nums[index] == last) {
                return false;
            }
            last = nums[index++];
        }
        return count0 >= 0;
    }

    /**
     * 【计数排序】
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :37.1 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public boolean isStraight2(int[] nums) {
        if (nums == null || nums.length < 5) return false;
        int[] count = new int[14]; // 放置0~13
        int min = 14;
        int max = 0;
        // 如果没有重复元素的话，顺子的非0最小值和最大值之间小于等于4
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (count[nums[i]] == 1) return false; // 说明在遍历之前就已经有1个该元素了，杜绝重复元素
                max = Math.max(max, nums[i]);
                min = Math.min(min, nums[i]);
            }
            count[nums[i]]++;
        }
        return (max - min) < 5;
    }

    @Test
    public void test() {
        assert isStraight(new int[]{1, 2, 3, 4, 5});
        assert isStraight(new int[]{0, 0, 1, 2, 5});
        assert isStraight(new int[]{0, 0, 4, 5, 8});
        assert !isStraight(new int[]{0, 0, 1, 2, 6});
        assert !isStraight(new int[]{0, 1, 1, 2, 3});

        assert isStraight2(new int[]{1, 2, 3, 4, 5});
        assert isStraight2(new int[]{0, 0, 0, 0, 0});
        assert isStraight2(new int[]{0, 0, 1, 2, 5});
        assert isStraight2(new int[]{0, 0, 4, 5, 8});
        assert !isStraight2(new int[]{0, 0, 1, 2, 6});
        assert !isStraight2(new int[]{0, 1, 1, 2, 3});
    }
}
