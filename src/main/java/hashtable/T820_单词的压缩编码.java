package hashtable;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/short-encoding-of-words/
 */
public class T820_单词的压缩编码 {
    /**
     * 【后缀法】
     * me是time的后缀，则可以直接不考虑me
     * 目标就是保留所有不是其他单词后缀的单词，最后的结果就是这些单词长度加一的总和
     * <p>
     * 执行用时 :23 ms, 在所有 Java 提交中击败了66.92% 的用户
     * 内存消耗 :42 MB, 在所有 Java 提交中击败了16.66%的用户
     */
    public int minimumLengthEncoding(String[] words) {
        int min = 0;
        if (words == null || words.length == 0) return min;
        // set天然去重了
        Set<String> set = new HashSet<>(Arrays.asList(words));
        for (String word : words) {
            // 看看每一个单词的后缀在不在set中
            for (int i = 1; i < word.length(); i++) {
                set.remove(word.substring(i)); // 有的话就删除
            }
        }
        for (String word : set) {
            min += word.length() + 1;
        }
        return min;
    }

    /**
     * 【逆序反转，比较相邻】
     * 每个单词都逆序反转，me和time逆序之后变成em和emit，数组排序时一定在相邻位置
     * <p>
     * 执行用时 :44 ms, 在所有 Java 提交中击败了44.74% 的用户
     * 内存消耗 :41.9 MB, 在所有 Java 提交中击败了16.66%的用户
     */
    public int minimumLengthEncoding2(String[] words) {
        int min = 0;
        if (words == null || words.length == 0) return min;
        StringBuilder[] sb = new StringBuilder[words.length];
        // 反转单词
        for (int i = 0; i < words.length; i++) {
            sb[i] = new StringBuilder(words[i]).reverse();
        }
        Arrays.sort(sb, Comparator.comparing(StringBuilder::toString));
        // 比较相邻的
        for (int i = 1; i < sb.length; i++) {
            if (sb[i].toString().startsWith(sb[i - 1].toString())) continue;
            min += sb[i - 1].length() + 1; // 前面的不是后面的开头，就加上前面的长度
        }
        min += sb[sb.length - 1].length() + 1; // 加上最后一个的长度
        return min;
    }

    @Test
    public void test() {
        int n1 = minimumLengthEncoding(new String[]{"time", "me", "bell"});
        assert n1 == 10 : n1;
        int n2 = minimumLengthEncoding(new String[]{"me", "time", "bell"});
        assert n2 == 10 : n2;
        int n3 = minimumLengthEncoding(new String[]{"bell", "me", "time"});
        assert n3 == 10 : n3;
        int n4 = minimumLengthEncoding(new String[]{"time", "atime", "btime"});
        assert n4 == 12 : n4;
        int n5 = minimumLengthEncoding(new String[]{"time", "me"});
        assert n5 == 5 : n5;
    }

    @Test
    public void test2() {
        int n1 = minimumLengthEncoding2(new String[]{"time", "me", "bell"});
        assert n1 == 10 : n1;
        int n2 = minimumLengthEncoding2(new String[]{"me", "time", "bell"});
        assert n2 == 10 : n2;
        int n3 = minimumLengthEncoding2(new String[]{"bell", "me", "time"});
        assert n3 == 10 : n3;
        int n4 = minimumLengthEncoding2(new String[]{"time", "atime", "btime"});
        assert n4 == 12 : n4;
        int n5 = minimumLengthEncoding2(new String[]{"time", "me"});
        assert n5 == 5 : n5;
    }
}
