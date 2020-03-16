package cracking_the_coding_interview;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/is-unique-lcci/
 * 【要点】需要问清楚字符集，这对于解题很有帮助
 */
public class T01_01判定字符是否唯一 {
    /**
     * 【哈希表】
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :37.6 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public boolean isUnique(String astr) {
        if (astr == null || astr.length() == 0) return true;
        Set<Character> set = new HashSet<>();
        char[] chars = astr.toCharArray();
        for (char c : chars) {
            if (set.contains(c)) return false;
            set.add(c);
        }
        return true;
    }

    /**
     * 【使用数组】
     * 执行用时 :1 ms, 在所有 Java 提交中击败了7.73% 的用户
     * 内存消耗 :41.4 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public boolean isUnique2(String astr) {
        if (astr == null || astr.length() == 0) return true;
        boolean[] table = new boolean[65536];
        char[] chars = astr.toCharArray();
        for (char c : chars) {
            if (table[(int) c]) return false;
            table[(int) c] = true;
        }
        return true;
    }

    /**
     * 【位运算】
     * 假设只含有a-z
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :37.1 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public boolean isUnique3(String astr) {
        if (astr == null || astr.length() == 0) return true;
        int mark = 0;  // int类型有32位
        char[] chars = astr.toCharArray();
        for (char c : chars) {
            int flag = 1 << (c - 'a');
            if ((mark & flag) > 0) return false;
            mark |= flag;
        }
        return true;
    }

    @Test
    public void test() {
        assert isUnique("asdfghjkl");
        assert !isUnique("adasfasf");

        assert isUnique2("asdfghjkl");
        assert !isUnique2("adasfasf");

        assert isUnique3("asdfghjkl");
        assert !isUnique3("adasfasf");
        assert !isUnique3("wafcews");
    }
}
