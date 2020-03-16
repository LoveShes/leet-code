package offer;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof/
 */
public class T43_1到n整数中1出现的次数 {
    /**
     * 【暴力】
     * 超出时间限制
     */
    public int countDigitOne(int n) {
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans += countNum(i);
        }
        return ans;
    }

    private int countNum(int n) {
        int result = 0;
        while (n > 0) {
            if (n % 10 == 1) result++;
            n = n / 10;
        }
        return result;
    }

    /**
     * 【找数学规律&递归】
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :36.6 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int countDigitOne2(int n) {
        return dfs(n);
    }

    private int dfs(int n) {
        if (n <= 0) return 0;
        // 不用字符串很难确定最高位
        String numStr = String.valueOf(n);
        int high = numStr.charAt(0) - '0';
        int pow = (int) Math.pow(10, numStr.length() - 1); // 最高位的单位
        int last = n - high * pow; // 去除最高位后剩下的结果

        if (high == 1) {
            /**
             * 例如1234，最高位high=1，pow=1000，last=234，可以将数字范围分成两部分1~999和1000~1234
             * 【1~999】 dfs(pow - 1)代表[1,999]中1的个数
             * 【1000~1234】 由2部分组成
             * 1) last+1 代表最高位为1有多少种情况，即[1000,1234]（只看最高位）
             * 2) dfs(last)代表[1000,1234]中除去最高位（234）中1的个数
             */
            return dfs(pow - 1) + (last + 1) + dfs(last);
        } else {
            /**
             * 例如2234，最高位high=2，pow=1000，last=234，可以将数字范围分成两部分1~999, 1000~1999, 2000~2234
             * 【1~999】 dfs(pow - 1)代表[1,999]中1的个数
             * 【1000~1999】 由2部分组成
             * 1) pow 代表最高位为1有多少种情况，即[1000,1999]（只看最高位）
             * 2) dfs(pow - 1) 代表[1000,1999]中除去最高位（999）中1的个数
             * 【2000~2234】 dfs(last)代表[2000,2234]中1的个数
             */
            return high * dfs(pow - 1) + pow + dfs(last);
        }
    }

    @Test
    public void test() {
        assert countDigitOne(12) == 5 : countDigitOne(12);
        assert countDigitOne2(12) == 5 : countDigitOne2(12);
        assert countDigitOne2(1234) == 689 : countDigitOne2(1234);
    }
}
