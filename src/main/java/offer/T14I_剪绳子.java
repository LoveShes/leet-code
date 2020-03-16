package offer;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/jian-sheng-zi-lcof/
 */
public class T14I_剪绳子 {

    /**
     * 【动态规划】
     * 执行用时 :1 ms, 在所有 Java 提交中击败了54.49% 的用户
     * 内存消耗 :36.1 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int cuttingRope(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;

        int[] dp = new int[n + 1];
        dp[2] = 2; // 注意这里特殊值的处理，否则后面算不出来
        dp[3] = 3;
        for (int i = 4; i <= n; i++) {
            int max = 0;
            for (int j = i / 2; j > 1; j--) {
                max = Math.max(max, dp[j] * dp[i - j]);
                dp[i] = max;
            }
        }
        return dp[n];
    }

    /**
     * 【找数学规律】
     * 求导可知，当分成 n/e 段时，乘积最大，每一段长度为 e，可得每一段长度为3或者2时最后的乘积最大
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :36.1 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int cuttingRope2(int n) {
        if (n <= 3) return n - 1;

        int r = n % 3;
        int a = n / 3;
        if (r == 0) return (int) Math.pow(3, a);
        if (r == 1) return (int) Math.pow(3, a - 1) * 4;
        return (int) Math.pow(3, a) * 2;
    }

    /**
     * 【查表法】
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :36.2 MB, 在所有 Java 提交中击败了5.18%的用户
     */
    public int cuttingRope3(int n) {
        int[] res = {0, 0, 1, 2, 4, 6, 9, 12, 18, 27, 36, 54, 81, 108, 162, 243, 324, 486, 729, 972, 1458, 2187, 2916, 4374, 6561, 8748, 13122, 19683, 26244, 39366, 59049, 78732, 118098, 177147, 236196, 354294, 531441, 708588, 1062882, 1594323, 2125764, 3188646, 4782969, 6377292, 9565938, 14348907, 19131876, 28697814, 43046721, 57395628, 86093442, 129140163, 172186884, 258280326, 387420489, 516560652, 774840978, 1162261467, 1549681956};
        return res[n];
    }

    @Test
    public void test() {
        System.out.println(cuttingRope(2)); // 1
        System.out.println(cuttingRope(3)); // 2
        System.out.println(cuttingRope(10)); // 36
        System.out.println(cuttingRope(12)); // 81
        System.out.println(cuttingRope(40)); // 2125764

        System.out.println();

        System.out.println(cuttingRope2(2)); // 1
        System.out.println(cuttingRope2(3)); // 2
        System.out.println(cuttingRope2(10)); // 36
        System.out.println(cuttingRope2(12)); // 81
        System.out.println(cuttingRope2(40)); // 2125764

        System.out.println();

        System.out.println(cuttingRope3(2)); // 1
        System.out.println(cuttingRope3(3)); // 2
        System.out.println(cuttingRope3(10)); // 36
        System.out.println(cuttingRope3(12)); // 81
        System.out.println(cuttingRope3(40)); // 2125764
    }

    @Test
    public void test2() {
        StringBuilder sb = new StringBuilder("{0, 0, ");
        int i = 2;
        for (; i < 58; i++) {
            sb.append(cuttingRope(i)).append(", ");
        }
        sb.append(cuttingRope(i)).append("}");
        System.out.println(sb.toString());
    }

}
