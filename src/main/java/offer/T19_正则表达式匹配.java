package offer;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/zheng-ze-biao-da-shi-pi-pei-lcof/
 */
public class T19_正则表达式匹配 {
    /**
     * 【递归】FIXME
     */
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();
        if (sChars.length == 0 || pChars.length == 0) return false;
        return isMatch(sChars, pChars, 0, 0);
    }

    private boolean isMatch(char[] sChars, char[] pChars, int sIndex, int pIndex) {
        if (sIndex >= sChars.length && pIndex >= pChars.length) return true;
        if (pIndex >= pChars.length) return false;

        // 先看下一位是不是'*'
        if (pIndex < pChars.length - 1 && pChars[pIndex + 1] == '*') {
            if (pChars[pIndex] == '.' || sChars[sIndex] == pChars[pIndex]) {
                return isMatch(sChars, pChars, sIndex, pIndex + 2)
                        || isMatch(sChars, pChars, sIndex + 1, pIndex + 2)
                        || isMatch(sChars, pChars, sIndex + 1, pIndex);
            }
            return isMatch(sChars, pChars, sIndex, pIndex + 2);
        }

        // 下一位不是'*'
        if (pChars[pIndex] == '.' || sChars[sIndex] == pChars[pIndex]) {
            return isMatch(sChars, pChars, sIndex + 1, pIndex + 1);
        }
        return false;
    }

    /**
     * FIXME【动态规划】
     */
    public boolean isMatch2(String s, String p) {
        if (s == null || p == null) return false;
        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();
        if (sChars.length == 0 || pChars.length == 0) return false;
        boolean[][] dp = new boolean[pChars.length][sChars.length];
        dp[0][0] = pChars[0] == '.' || pChars[0] == sChars[0];
        for (int i = 1; i < pChars.length; i++) {
            for (int j = 1; j < sChars.length; j++) {
                if (pChars[i] == '*') {

                }

            }
        }



        return dp[sChars.length - 1][pChars.length - 1];
    }

    @Test
    public void test() {
        assert !isMatch("aa", "a");
        assert isMatch("aa", ".a");
        assert isMatch("aaa", "a*");
        assert isMatch("ab", ".*");
        assert isMatch("aab", "c*a*b");
        assert isMatch("aab", "...");
        assert isMatch("aab", "ab*.a*b");
        assert !isMatch("aab", "*...");
        assert !isMatch("mississippi", "mis*is*p*.");
        assert !isMatch("ab", ".*c");
    }

    @Test
    public void test2() {
        assert !isMatch2("aa", "a");
        assert isMatch2("aa", ".a");
        assert isMatch2("aaa", "a*");
        assert isMatch2("ab", ".*");
        assert isMatch2("aab", "c*a*b");
        assert isMatch2("aab", "...");
        assert isMatch2("aab", "ab*.a*b");
        assert !isMatch2("aab", "*...");
        assert !isMatch2("mississippi", "mis*is*p*.");
        assert !isMatch2("ab", ".*c");
    }
}
