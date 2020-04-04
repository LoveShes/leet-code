package offer;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/
 */
public class T56II_数组中数字出现的次数II {
    /**
     * 【位运算】
     * 统计每位的1的个数（0的个数对结果没影响）
     * <p>
     * 执行用时 :5 ms, 在所有 Java 提交中击败了87.04% 的用户
     * 内存消耗 :42.5 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int singleNumber(int[] nums) {
        int[] bit = new int[32];
        for (int num : nums) {
            for (int i = 31; i >= 0; i--) {
                bit[i] += num & 1;
                num = num >> 1;
            }
        }
        int ans = 0;
        for (int count : bit) {
            ans = ans << 1;
            ans += count % 3; // 如果有3个一样的数，则该位1的个数一定能被3整除
        }
        return ans;
    }

    /**
     * 【状态机】
     * 太难懂了
     * <p>
     * 执行用时 :3 ms, 在所有 Java 提交中击败了88.89% 的用户
     * 内存消耗 :42.9 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int singleNumber2(int[] nums) {
        int ones = 0, twos = 0;
        for (int num : nums) {
            ones = (ones ^ num) & ~twos;
            twos = (twos ^ num) & ~ones;
        }
        return ones;
    }

    @Test
    public void test() {
        System.out.println(singleNumber(new int[]{3, 4, 3, 3})); // 4
        System.out.println(singleNumber2(new int[]{3, 4, 3, 3})); // 4
        System.out.println(singleNumber(new int[]{9, 1, 7, 9, 7, 9, 7})); // 1
        System.out.println(singleNumber2(new int[]{9, 1, 7, 9, 7, 9, 7})); // 1
    }
}
