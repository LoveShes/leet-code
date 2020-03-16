package alogorithm;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/greatest-common-divisor-of-strings/
 */
public class T1071_字符串的最大公因子 {
    /**
     * 【辗转相除法】
     * 公因子一定是字符串的前缀
     * 执行用时 :1 ms, 在所有 Java 提交中击败了93.98% 的用户
     * 内存消耗 :38.1 MB, 在所有 Java 提交中击败了15.20%的用户
     */
    public String gcdOfStrings(String str1, String str2) {
        if (str1 == null || str2 == null) return "";
        int len = gcd(str1.length(), str2.length());
        // 如果 str1 和 str2 拼接后等于 str2和 str1 拼接起来的字符串（注意拼接顺序不同），那么一定存在符合条件的字符串 X。
        // 这里比较公因子要注意，不能只比较前缀，避免 "ABCDEF" 与 "ABC" 比较出现错误
        if (!(str1 + str2).equals(str2 + str1)) return "";
        return str1.substring(0, len);
    }

    private int gcd(int len1, int len2) {
        if (len2 == 0) return len1;
        return gcd(len2, len1 % len2);
    }

    @Test
    public void testGcd() {
        assert gcd(6, 4) == 2;
        assert gcd(4, 6) == 2;
        assert gcd(9, 3) == 3;
        assert gcd(3, 5) == 1;
        assert gcd(0, 0) == 0;
    }

    @Test
    public void test() {
        assert gcdOfStrings("ABABAB", "ABAB").equals("AB") : gcdOfStrings("ABABAB", "ABAB");
        assert gcdOfStrings("ABCABC", "ABC").equals("ABC") : gcdOfStrings("ABCABC", "ABC");
        assert gcdOfStrings("LEET", "CODE").equals("") : gcdOfStrings("LEET", "CODE");
        assert gcdOfStrings("ABCDEF", "ABC").equals("") : gcdOfStrings("ABCDEF", "ABC");
    }
}
