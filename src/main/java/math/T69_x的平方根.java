package math;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/sqrtx/
 */
public class T69_x的平方根 {
    /**
     * 【暴力循环】
     * 这里只用返回整数部分
     * 执行用时 :86 ms, 在所有 Java 提交中击败了5.13% 的用户
     * 内存消耗 :33.6 MB, 在所有 Java 提交中击败了62.86%的用户
     */
    public int mySqrt(int x) {
        int i = 1;
        for (; i <= x; i++) {
            if (i > x / i) break;
        }
        return i - 1;
    }

    /**
     * 【二分法】
     * 执行用时 :2 ms, 在所有 Java 提交中击败了80.08% 的用户
     * 内存消耗 :33.7 MB, 在所有 Java 提交中击败了40.70%的用户
     */
    public int mySqrt2(int x) {
        if (x <= 1) return x;
        int left = 2;
        int right = x >> 1; // 对于>=2的数，平方根一定小于x/2，所以整数部分小于等于 x>>1
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (mid > x / mid) {
                right = mid - 1;
            } else if (mid < x / mid) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return right;
    }

    /**
     * 【递归法】
     * 通过递归将x缩小，直至能够直接写成平方根
     * 执行用时 :1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :33.3 MB, 在所有 Java 提交中击败了95.96%的用户
     */
    public int mySqrt3(int x) {
        if (x <= 1) return x;

        int left = mySqrt3(x >> 2) << 1; // 即 sqrt(x/4)*2
        int right = left + 1;
        return right > x / right ? left : right;
    }

    /**
     * 【牛顿法】
     * 迭代公式为 ans=(ans+x/ans)/2
     * 执行用时 :1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :36.7 MB, 在所有 Java 提交中击败了5.09%的用户
     */
    public int mySqrt4(int x) {
        long ans = x; // 由于是从大往小走，用int类型可能会超范围
        while (ans * ans > x) {
            ans = (ans + x / ans) >> 1;
        }
        return (int) ans;
    }

    @Test
    public void test1() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i + ":" + mySqrt(i));
        }
        System.out.println(mySqrt(2147395600)); // 46340
    }

    @Test
    public void test2() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i + ":" + mySqrt2(i));
        }
        System.out.println(mySqrt2(2147395600)); // 46340
    }

    @Test
    public void test3() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i + ":" + mySqrt3(i));
        }
        System.out.println(mySqrt3(2147395600)); // 46340
    }

    @Test
    public void test4() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i + ":" + mySqrt4(i));
        }
        System.out.println(mySqrt4(2147395600)); // 46340
    }
}
