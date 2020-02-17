package math;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/add-digits/
 */
public class T258_各位相加 {
    public int addDigits(int num) {
        return num == 0 ? 0 : (num-1) % 9 + 1;
    }

    @Test
    public void test() {
        System.out.println(addDigits(0));
        System.out.println(addDigits(1));
        System.out.println(addDigits(9));
        System.out.println(addDigits(128));
        System.out.println(addDigits(129));
        System.out.println(addDigits(999));
    }
}
