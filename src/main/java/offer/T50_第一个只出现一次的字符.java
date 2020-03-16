package offer;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/
 */
public class T50_第一个只出现一次的字符 {
    /**
     * 【HashMap】
     * Set还不行，得用Map统计次数，另外需要用LinkedHashMap保证插入顺序
     * 执行用时 :29 ms, 在所有 Java 提交中击败了48.91% 的用户
     * 内存消耗 :42.6 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public char firstUniqChar(String s) {
        if (s == null || s.length() == 0) return ' ';
        // 需要保证添加顺序
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0, len = s.length(); i < len; i++) {
            char c = s.charAt(i);
            map.merge(c, 1, Integer::sum);
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) return entry.getKey();
        }
        return ' ';
    }

    /**
     * 【数组模拟HashMap】
     * 执行用时 :4 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :41.8 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public char firstUniqChar2(String s) {
        if (s == null || s.length() == 0) return ' ';
        int[] hashTable = new int[256];
        // 字符串转成数组比后续用charAt()要快一点
        char[] array = s.toCharArray();
        for (char c : array) {
            hashTable[c]++;
        }
        for (char c : array) {
            if (hashTable[c] == 1) return c;
        }
        return ' ';
    }

    @Test
    public void test() {
        assert firstUniqChar("abaccdeff") == 'b' : firstUniqChar("abaccdeff");
        assert firstUniqChar("zabbbac") == 'z' : firstUniqChar("zabbbac");
        assert firstUniqChar("aadadaad") == ' ' : firstUniqChar("aadadaad");
        assert firstUniqChar("") == ' ';
        assert firstUniqChar("cc") == ' ';
    }

    @Test
    public void test2() {
        assert firstUniqChar2("abaccdeff") == 'b' : firstUniqChar2("abaccdeff");
        assert firstUniqChar2("zabbbac") == 'z' : firstUniqChar2("zabbbac");
        assert firstUniqChar2("aadadaad") == ' ' : firstUniqChar2("aadadaad");
        assert firstUniqChar2("") == ' ';
        assert firstUniqChar2("cc") == ' ';
    }
}
