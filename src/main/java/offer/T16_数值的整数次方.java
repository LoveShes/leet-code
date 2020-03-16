package offer;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/
 */
public class T16_数值的整数次方 {
    /**
     * 【二分法】
     * 执行用时 :1 ms, 在所有 Java 提交中击败了90.13% 的用户
     * 内存消耗 :37.1 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public double myPow(double x, int n) {
        if (x == 0) {
            if (n != 0) return 0;
            throw new IllegalArgumentException("x和n不能同时为0");
        } else {
            if (n >= 0) return activePow(x, n);
            return negativePow(x, n);
        }
    }

    /**
     * 【优化代码】
     * 执行用时 :1 ms, 在所有 Java 提交中击败了90.82% 的用户
     * 内存消耗 :37.3 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public double myPow2(double x, int n) {
        if (x == 0) return 0;
        if (x == 1 || n == 0) return 1;
        if (n == 1) return x;

        if (n < 0) return myPow2(1 / x, -(n + 1)) / x; // 这么处理是为了防止计算-Integer.MIN_VALUE

        // n > 0
        if ((n & 1) == 1) return x * myPow2(x * x, (n - 1) >> 1);
        return myPow2(x * x, n >> 1);
    }

    // 要采用二分法计算幂，避免超时
    private double activePow(double x, int n) {
        if (n == 0) return 1;
        if (n == 1) return x;
        double res = 1;
        if ((n & 1) == 1) { // 奇数
            double temp = activePow(x, (n - 1) >> 1);
            res *= (x * temp * temp);
        } else {
            double temp = activePow(x, n >> 1);
            res *= (temp * temp);
        }
        return res;
    }

    private double negativePow(double x, int n) { // n传进来是负数
        double r = 1 / x;
        if (n == -1) return r;
        double res = 1;
        if ((-n) % 2 == 1) {
            double temp = negativePow(x, (n + 1) >> 1);
            res *= (r * temp * temp);
        } else {
            double temp = negativePow(x, n >> 1);
            res *= (temp * temp);
        }
        return res;
    }


    @Test
    public void test() {
        System.out.println(myPow(0.0, 2)); // 0.0
        System.out.println(myPow(2.0, 0)); // 1.0
        System.out.println(myPow(2.0, 1)); // 2.0
        System.out.println(myPow(2.0, 10)); // 1024.0
        System.out.println(myPow(2.0, -1)); // 0.5
        System.out.println(myPow(2.0, -2)); // 0.25
        System.out.println(myPow(1.0, -2147483647)); // 1.0
        System.out.println(myPow(2.0, -2147483648)); // 0.0
    }

    @Test
    public void test2() {
        System.out.println(myPow2(0.0, 2)); // 0.0
        System.out.println(myPow2(2.0, 0)); // 1.0
        System.out.println(myPow2(2.0, 1)); // 2.0
        System.out.println(myPow2(2.0, 10)); // 1024.0
        System.out.println(myPow2(2.0, -1)); // 0.5
        System.out.println(myPow2(2.0, -2)); // 0.25
        System.out.println(myPow2(1.0, -2147483647)); // 1.0
        System.out.println(myPow2(2.0, -2147483648)); // 注意 -Integer.MIN_VALUE还是它本身
    }
}
