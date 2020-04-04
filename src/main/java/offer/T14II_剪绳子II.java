package offer;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/jian-sheng-zi-ii-lcof/
 */
public class T14II_剪绳子II {
    /**
     * 【贪婪算法】
     * 由于此题要对结果取余，所以不能使用动态规划，并且提前取余会影响结果
     * 尽可能多的分成长度为3的段，如果剩下一段长度为1，则把最后2段由(3,1)变成(2,2)
     * <p>
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :36.6 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int cuttingRope(int n) {
        if (n <= 3) return n - 1;
        int mod = 1000000007;
        long res = 1;
        while (n > 4) { // 等于4的时候要拆成 2 2
            res *= 3; // 先找出尽可能多的3
            res %= mod;
            n -= 3;
        }
        return (int) (res * n % mod); // 最后仍然有可能是大于mod的
    }

    /**
     * 【快速幂算法求余】
     * 求余公式：(xy)⊙p=[(x⊙p)(y⊙p)]⊙p，其中⊙表示求余
     * 快速幂求余公式：
     * a 为偶数： (x^a)⊙p=(x^2⊙p)^(a//2)⊙p
     * a 为奇数： (x^a)⊙p=[x(x^2⊙p)^(a//2)]⊙p
     * <p>
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :36.4 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int cuttingRope2(int n) {
        if (n <= 3) return n - 1;
        int r = n % 3;
        int mod = 1000000007;
        long rem = 1;
        long x = 3;
        // 从 n/3-1 开始算，可能会漏下1~2段（整除时漏下1段）
        for (int a = n / 3 - 1; a > 0; a /= 2) {
            if (a % 2 == 1) rem = (rem * x) % mod;
            x = (x * x) % mod;
        }
        // 漏下的在这里算上去
        if (r == 0) return (int) (rem * 3 % mod);
        if (r == 1) return (int) (rem * 4 % mod);
        return (int) (rem * 6 % mod);
    }

    @Test
    public void test() {
        System.out.println(cuttingRope(2)); // 1
        System.out.println(cuttingRope(3)); // 2
        System.out.println(cuttingRope(10)); // 36
        System.out.println(cuttingRope(11)); // 54
        System.out.println(cuttingRope(12)); // 81
        System.out.println(cuttingRope(40)); // 2125764
        System.out.println(cuttingRope(1000)); // 620946522
    }

    @Test
    public void test2() {
        System.out.println(cuttingRope2(2)); // 1
        System.out.println(cuttingRope2(3)); // 2
        System.out.println(cuttingRope2(10)); // 36
        System.out.println(cuttingRope2(11)); // 54
        System.out.println(cuttingRope2(12)); // 81
        System.out.println(cuttingRope2(40)); // 2125764
        System.out.println(cuttingRope2(1000)); // 620946522
    }
}
