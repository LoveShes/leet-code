package cracking_the_coding_interview;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/check-permutation-lcci/
 */
public class T01_02判定是否互为字符重排 {
    /**
     * 【数组】
     * 假设为扩展ASCII字符集（256个）
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :37.1 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public boolean CheckPermutation(String s1, String s2) {
        if (s1 == null && s2 == null) return true;
        if (s1 == null || s2 == null || s1.length() != s2.length()) return false;

        final int SIZE = 256;
        int[] table1 = new int[SIZE];
        int[] table2 = new int[SIZE];

        for (int i = 0; i < s1.length(); i++) {
            table1[s1.charAt(i)]++;
            table2[s2.charAt(i)]++;
        }

        for (int i = 0; i < SIZE; i++) {
            if (table1[i] != table2[i]) return false;
        }
        return true;
    }

    /**
     * 【数组——优化】
     * 只统计s1的字符数量，然后遍历s2中的字符，看是否在s1中，有一个就减一个，如果某个字符减到负数就提前返回false，直到结束
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :37.3 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public boolean CheckPermutation2(String s1, String s2) {
        if (s1 == null && s2 == null) return true;
        if (s1 == null || s2 == null || s1.length() != s2.length()) return false;

        final int SIZE = 256;
        int[] table = new int[SIZE];

        for (int i = 0; i < s1.length(); i++) {
            table[s1.charAt(i)]++;
        }

        for (int i = 0; i < s2.length(); i++) {
            table[s2.charAt(i)]--;
            if (table[s2.charAt(i)] < 0) return false;
        }
        return true;
    }

    /**
     * 【排序法】
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :36.9 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public boolean CheckPermutation3(String s1, String s2) {
        if (s1 == null && s2 == null) return true;
        if (s1 == null || s2 == null || s1.length() != s2.length()) return false;

        char[] a = s1.toCharArray();
        Arrays.sort(a);
        char[] b = s2.toCharArray();
        Arrays.sort(b);
        return Arrays.equals(a, b);
    }

    @Test
    public void test() {
        assert CheckPermutation("abcd", "dcba");
        assert !CheckPermutation(null, "dcba");
        assert CheckPermutation(null, null);
        assert !CheckPermutation("abc", "dca");
        assert !CheckPermutation("abc", "abca");
    }

    @Test
    public void test2() {
        assert CheckPermutation2("abcd", "dcba");
        assert !CheckPermutation2(null, "dcba");
        assert CheckPermutation2(null, null);
        assert !CheckPermutation2("abc", "dca");
        assert !CheckPermutation2("abc", "abca");
    }

    @Test
    public void test3() {
        assert CheckPermutation3("abcd", "dcba");
        assert !CheckPermutation3(null, "dcba");
        assert CheckPermutation3(null, null);
        assert !CheckPermutation3("abc", "dca");
        assert !CheckPermutation3("abc", "abca");
    }
}
