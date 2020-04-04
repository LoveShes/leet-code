package bytedance.string;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 无重复字符的最长子串 {
    /**
     * 【暴力】
     * 执行用时：221 ms, 在所有 Java 提交中击败了6.26% 的用户
     * 内存消耗：41.7 MB
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null) return 0;
        char[] array = s.toCharArray();
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = i; j < array.length; j++) {
                if (set.contains(array[j])) break;
                set.add(array[j]);
                max = Math.max(max, set.size());
            }
        }
        return max;
    }

    /**
     * 【动态规划】
     * 执行用时：4 ms, 在所有 Java 提交中击败了90.78% 的用户
     * 内存消耗：40.4 MB
     */
    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] array = s.toCharArray();
        int[] hash = new int[256]; // 记录上次出现字符串的索引
        Arrays.fill(hash, -1);
        int dp = 0; // 以当前字符结尾的最大长度
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            int last = hash[array[i]]; // 上次出现的位置
            if (i - last <= dp) { // 在当前统计范围内出现过
                dp = i - last; // 长度要另算
            } else {  // 前面没出现过或者超出当前统计范围
                dp++;
            }
            hash[array[i]] = i; // 更新该字符的位置
            max = Math.max(max, dp);
        }
        return max;
    }

    @Test
    public void test() {
        System.out.println(lengthOfLongestSubstring("abcabcbb")); // 3
        System.out.println(lengthOfLongestSubstring("bbbbb")); // 1
        System.out.println(lengthOfLongestSubstring("pwwkew")); //3

        System.out.println(lengthOfLongestSubstring2("abcabcbb")); // 3
        System.out.println(lengthOfLongestSubstring2("bbbbb")); // 1
        System.out.println(lengthOfLongestSubstring2("pwwkew")); // 3
        System.out.println(lengthOfLongestSubstring2("tmmzuxt")); // 5
    }
}
