package math;

import org.junit.jupiter.api.Test;
import tools.Times;

/**
 * https://leetcode-cn.com/problems/palindrome-number/
 */
public class T9_回文数 {

    // 简单做法，变成字符串
    public boolean isPalindrome(int x) {
        String s = "" + x;
        char[] chars = s.toCharArray();
        int len = chars.length;
        for (int i = 0; i < len; i++) {
            if (chars[i] != chars[len - i - 1]) {
                return false;
            }
        }
        return true;
    }

    // 数学方法，先整体反转再比较
    public boolean isPalindrome2(int x) {
        // 如果为负数或者以0结尾，肯定不是回文数
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int copy = x;
        int result = 0;
        while (copy != 0) {
            result = 10 * result + copy % 10;
            copy = copy / 10; // 在leetcode中测试， /= 操作更为费时
        }
        return result == x;
    }

    // 数学方法，反转一半就比较
    public boolean isPalindrome3(int x) {
        // 如果为负数或者以0结尾，肯定不是回文数
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int result = 0;
        while (result < x) {
            result = 10 * result + x % 10;
            x = x /10;
        }
        return result == x || (result / 10 == x);
    }

    @Test
    public void test() {
        int x1 = 12321;
        int x2 = 12345;
        int x3 = 10;
        int x4 = -121;

        // System.out.println(isPalindrome(x1)); // true
        // System.out.println(isPalindrome(x2)); // false
        // System.out.println(isPalindrome(x3)); // false
        // System.out.println(isPalindrome(x4)); // false
        //
        // System.out.println(isPalindrome2(x1)); // true
        // System.out.println(isPalindrome2(x2)); // false
        // System.out.println(isPalindrome2(x3)); // false
        // System.out.println(isPalindrome2(x4)); // false

        System.out.println(isPalindrome3(x1)); // true
        System.out.println(isPalindrome3(x2)); // false
        System.out.println(isPalindrome3(x3)); // false
        System.out.println(isPalindrome3(x4)); // false
    }

    @Test
    public void testTime() {
        int x = 123454321;
        int n = 100000000;
        Times.test("反转整体", ()->{
            for (int i = 0; i < n; i++) {
                isPalindrome2(x);
            }});
        Times.test("反转一半", ()->{
            for (int i = 0; i < n; i++) {
                isPalindrome3(x);
            }});
    }


}
