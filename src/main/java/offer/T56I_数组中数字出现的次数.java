package offer;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/
 */
public class T56I_数组中数字出现的次数 {
    /**
     * 【异或运算,再分组】
     * 一个数异或它本身等于0，可以分成2组，每组分别求值
     * 执行用时 :2 ms, 在所有 Java 提交中击败了96.51% 的用户
     * 内存消耗 :42.3 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int[] singleNumbers(int[] nums) {
        int[] ans = new int[2];
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        // int low = xor ^ (xor - 1) & xor; // 算出最右边的1
        int low = (-xor) & xor; // 或者这么算（二进制求相反数等于各位取反再加1）
        // 按最低位是否为0分成2组
        for (int num : nums) {
            if ((num & low) == 0) {
                ans[0] ^= num;
            } else {
                ans[1] ^= num;
            }
        }
        return ans;
    }

    /**
     * 【也可以只算一个，另一个由xor^x1得出】
     * 异或满足交换律，结合律。 x1^x2==xor => xor^x1==x2
     * 执行用时 :1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :42.3 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int[] singleNumbers2(int[] nums) {
        int xor = 0;
        int x1 = 0;
        for (int num : nums) {
            xor ^= num;
        }
        // int low = xor ^ (xor - 1) & xor; // 算出最右边的1
        int low = (-xor) & xor; // 或者这么算（二进制求相反数等于各位取反再加1）
        // 按最低位是否为0分成2组，只计算一组
        for (int num : nums) {
            if ((num & low) == 0) x1 ^= num;
        }
        return new int[]{x1, x1 ^ xor};
    }

    @Test
    public void test() {
        System.out.println(Arrays.toString(singleNumbers(new int[]{4, 1, 4, 6})));
        System.out.println(Arrays.toString(singleNumbers(new int[]{4, 1, 0, 4})));
        System.out.println(Arrays.toString(singleNumbers(new int[]{1, 2, 10, 4, 1, 4, 3, 3})));

        System.out.println(Arrays.toString(singleNumbers2(new int[]{4, 1, 4, 6})));
        System.out.println(Arrays.toString(singleNumbers2(new int[]{4, 1, 0, 4})));
        System.out.println(Arrays.toString(singleNumbers2(new int[]{1, 2, 10, 4, 1, 4, 3, 3})));
    }
}
