package offer;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof/
 */
public class T53II_0到n减1中缺失的数字 {
    /**
     * 【线性查找】
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :43.1 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int missingNumber(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] != i) return i;
            i++;
        }
        return i;
    }

    /**
     * 【二分查找】
     * 找到第一个捣蛋鬼（下标和值不等的元素），这个元素之前的全是下标与值相等的元素，这个元素及之后都是下标与值不相等的元素
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :43.1 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int missingNumber2(int[] nums) {
        int begin = 0;
        int end = nums.length;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (nums[mid] != mid) {
                if (mid == 0 || nums[mid - 1] == mid - 1) { // mid处不相等，mid-1处相等，说明mid处就是捣蛋鬼
                    return mid;
                } else { // mid处不相等，mid-1处也不相等，说明捣蛋鬼还在前面，需要往左找
                    end = mid;
                }
            } else { // 当前位置相等，说明前面肯定相等，往右边找
                begin = mid + 1;
            }
        }
        return nums.length; // 前面都没找到
    }

    /**
     * 【二分查找——简化】
     * 数组元素只可能≥下标，第一个≥下标的元素的下标即为所求
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :42.7 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int missingNumber3(int[] nums) {
        int begin = 0;
        int end = nums.length;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (nums[mid] != mid) { // 往左边找（这里也只可能是大于）
                end = mid;
            } else { // 往右边找
                begin = mid + 1;
            }
        }
        return begin;
    }

    @Test
    public void test() {
        assert missingNumber(new int[]{1}) == 0 : missingNumber(new int[]{1});
        assert missingNumber(new int[]{0, 2}) == 1 : missingNumber(new int[]{0, 2});
        assert missingNumber(new int[]{0, 1}) == 2 : missingNumber(new int[]{0, 1});
        assert missingNumber(new int[]{0, 1, 3}) == 2 : missingNumber(new int[]{0, 1, 3});
        assert missingNumber(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 9}) == 8 : missingNumber(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 9});
    }

    @Test
    public void test2() {
        assert missingNumber2(new int[]{1}) == 0 : missingNumber2(new int[]{1});
        assert missingNumber2(new int[]{0, 2}) == 1 : missingNumber2(new int[]{0, 2});
        assert missingNumber2(new int[]{0, 1}) == 2 : missingNumber2(new int[]{0, 1});
        assert missingNumber2(new int[]{0, 1, 3}) == 2 : missingNumber2(new int[]{0, 1, 3});
        assert missingNumber2(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 9}) == 8 : missingNumber2(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 9});
    }

    @Test
    public void test3() {
        assert missingNumber3(new int[]{1}) == 0 : missingNumber3(new int[]{1});
        assert missingNumber3(new int[]{0, 2}) == 1 : missingNumber3(new int[]{0, 2});
        assert missingNumber3(new int[]{0, 1}) == 2 : missingNumber3(new int[]{0, 1});
        assert missingNumber3(new int[]{0, 1, 3}) == 2 : missingNumber3(new int[]{0, 1, 3});
        assert missingNumber3(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 9}) == 8 : missingNumber3(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 9});
        assert missingNumber3(new int[]{0, 2, 3, 4, 5, 6, 7, 8}) == 1 : missingNumber3(new int[]{0, 2, 3, 4, 5, 6, 7, 8});
    }
}
