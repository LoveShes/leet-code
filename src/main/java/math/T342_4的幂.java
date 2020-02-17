package math;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/power-of-four/
 */
public class T342_4的幂 {

    public boolean isPowerOfFour(int num) {
        if (num <= 0) return false;
        while (num > 1) {
            if (num % 4 != 0) return false;
            num /= 4;
        }
        return true;
    }

    // 利用位运算
    public boolean isPowerOfFour2(int num) {
        return num > 0 && (num & (num - 1)) == 0 && (num & 0xaaaaaaaa) == 0;
    }

    @Test
    public void test() {
        int[] data = {0, 1, 2, 3, 4, 5, 8, 15, 16, 17, 32, 11, -1, 64, 40};
        for (int i = 1; i < 100; i++) {
            System.out.println(i + " : " + isPowerOfFour2(i));
        }
    }
}
