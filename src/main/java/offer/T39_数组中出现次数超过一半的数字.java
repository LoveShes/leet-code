package offer;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof/
 */
public class T39_数组中出现次数超过一半的数字 {
    /**
     * 【排序法】
     * 排完序之后，出现超过一半的数字一定在数组中间
     * 执行用时 :2 ms, 在所有 Java 提交中击败了85.47% 的用户
     * 内存消耗 :41.9 MB, 在所有 Java 提交中击败了38.31%的用户
     */
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length >> 1];
    }

    /**
     * 【哈希表】
     * 执行用时 :18 ms, 在所有 Java 提交中击败了18.53% 的用户
     * 内存消耗 :46.1 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int majorityElement2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxCount = 0;
        int ans = nums[0];
        for (int num : nums) {
            Integer count = map.get(num);
            count = count == null ? 0 : count;
            map.put(num, count + 1);
            if (count > maxCount) {
                maxCount = count;
                ans = num;
            }
        }
        return ans;
    }

    /**
     * 【投票法】
     * 执行用时 :1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :42.2 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int majorityElement3(int[] nums) {
        int ans = nums[0];
        int count = 0;
        for (int num : nums) {
            if (count == 0) { // 前面的票数抵消了，则重新假设众数
                ans = num;
            }
            if (num == ans) {
                count++;
            } else {
                count--;
            }
        }
        // System.out.println("次数：" + count);
        return ans;
    }


    @Test
    public void test() {
        System.out.println(majorityElement(new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2})); // 2
        System.out.println(majorityElement(new int[]{1, 2, 3, 3, 2, 2, 1, 2, 2})); // 2
        System.out.println(majorityElement(new int[]{2, 1, 2, 3, 2, 4, 2, 5, 2})); // 2
    }
}
