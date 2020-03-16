package array;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/plus-one/
 */
public class T66_加一 {
    /**
     * 【注意最后进位的处理】
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :35.1 MB, 在所有 Java 提交中击败了49.76%的用户
     */
    public int[] plusOne(int[] digits) {
        int plus = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            if (++digits[i] > 9) {
                digits[i] = 0;
            } else {
                plus = 0;
                break;
            }
        }
        if (plus == 1) { // 有进位
            digits = new int[digits.length + 1];
            digits[0] = 1; // 这里不用复制原来的数组，因为有进位一定是 [1,0,0,0,...,0]
        }
        return digits;
    }

    @Test
    public void test() {
        System.out.println(Arrays.toString(plusOne(new int[]{1, 2, 4})));
        System.out.println(Arrays.toString(plusOne(new int[]{1, 9, 9})));
        System.out.println(Arrays.toString(plusOne(new int[]{9, 9})));
    }
}
