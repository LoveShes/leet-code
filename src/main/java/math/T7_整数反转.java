package math;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/reverse-integer/
 */
public class T7_整数反转 {

    // 注意，需要判断溢出
    public int reverse(int x) {
        int result = 0;
        int temp;
        while (x != 0) {
            temp = x % 10;
            x = x / 10;
            if (result > Integer.MAX_VALUE / 10 || result < Integer.MIN_VALUE / 10) {
                return 0;
            }
            result = 10 * result + temp;
        }
        return result;
    }

    // 通过比较result的个位是否等于temp来判断有无溢出
    public int reverse2(int x) {
        int result = 0;
        int temp;
        while (x != 0) {
            temp = x % 10;
            x = x / 10;
            result = 10 * result + temp;
            if (result % 10 != temp) {
                return 0;
            }
        }
        return result;
    }

    @Test
    public void test() {
        int s1 = 123;
        int s2 = -123;
        int s3 = 214748364;
        int s4 = 2147483646;

        System.out.println(new T7_整数反转().reverse(s1));
        System.out.println(new T7_整数反转().reverse2(s1));
        System.out.println(new T7_整数反转().reverse(s2));
        System.out.println(new T7_整数反转().reverse2(s2));
        System.out.println(new T7_整数反转().reverse(s3));
        System.out.println(new T7_整数反转().reverse2(s3));
        System.out.println(new T7_整数反转().reverse(s4));
        System.out.println(new T7_整数反转().reverse2(s4));
    }

}
