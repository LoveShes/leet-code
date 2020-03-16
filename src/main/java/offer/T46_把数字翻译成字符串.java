package offer;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/
 */
public class T46_把数字翻译成字符串 {
    /**
     * 【递归】
     * f(i) = f(i+1) + g(i,i+1)*f(i+2)，g(i,i+1)表示子串[i,i+1]为有效的（注意是在[10,25]之间）
     * 执行用时 :1 ms, 在所有 Java 提交中击败了30.32% 的用户
     * 内存消耗 :36.7 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int translateNum(int num) {
        if (num < 0) return 0;
        String s = String.valueOf(num);
        return count(s);
    }

    private int count(String s) {
        if (s.length() <= 1) return 1;
        return count(s.substring(1)) + isValid(s.substring(0, 2)) * count(s.substring(2));
    }

    private int isValid(String s) {
        int num = (s.charAt(0) - '0') * 10 + (s.charAt(1) - '0');
        if (num >= 10 && num <= 25) return 1; // 注意，如果传进来的是以0开头的（例如"06"）是无效的
        return 0;
    }

    /**
     * 【动态规划】
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :36.3 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int translateNum2(int num) {
        if (num < 0) return 0;
        String s = String.valueOf(num);
        int len = s.length();
        int[] dp = new int[len + 1];
        dp[len] = 1;
        for (int i = len - 1; i >= 0; i--) {
            if (i == len - 1) {
                dp[i] = 1;
            } else {
                // 每一步都可以拆分成2种译法，例如2512112可以拆分成(2|512112)和(25|12112)
                dp[i] = dp[i + 1]; // 这里即对应(2|512112)译法
                // 下面计算(25|12112)的译法，如果前2位不能翻译，则直接抛弃
                int temp = 10 * (s.charAt(i) - '0') + (s.charAt(i + 1) - '0');
                if (temp <= 25 && temp >= 10) { // 这个数要在范围内（注意以0开头的数不算）
                    if (i < len - 2) { // 防止dp[i+2]超出范围
                        // 已经取了2位，则结果取决于把这2位抛开的结果，即2512112，前两位为25，结果取决于12112的种类数
                        dp[i] += dp[i + 2];
                    } else {
                        // 这里相当于251取前2位，但是只剩下一位了，这一位肯定能翻译
                        dp[i]++;
                    }
                }
            }
        }
        return dp[0];
    }

    @Test
    public void test() {
        assert translateNum(0) == 1 : translateNum(0);
        assert translateNum(25) == 2 : translateNum(25);
        assert translateNum(506) == 1 : translateNum(506);
        assert translateNum(12258) == 5 : translateNum(12258);
    }

    @Test
    public void test2() {
        assert translateNum2(0) == 1 : translateNum2(0);
        assert translateNum2(25) == 2 : translateNum2(25);
        assert translateNum2(506) == 1 : translateNum2(506);
        assert translateNum2(12258) == 5 : translateNum2(12258);
        assert translateNum2(2512112) == 16 : translateNum2(2512112);
    }
}
