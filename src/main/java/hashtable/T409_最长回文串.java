package hashtable;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/longest-palindrome/
 */
public class T409_最长回文串 {
    /**
     * 【哈希表】
     * 回文串：n对字符+最多一个单字符
     * 执行用时 :10 ms, 在所有 Java 提交中击败了24.81% 的用户
     * 内存消耗 :38.2 MB, 在所有 Java 提交中击败了5.25%的用户
     */
    public int longestPalindrome(String s) {
        if (s == null || s.length() == 0) return 0;
        int len = 0;
        boolean single = true;
        char[] array = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        // 统计数目
        for (char c : array) {
            map.merge(c, 1, Integer::sum);
        }
        for (Integer value : map.values()) {
            if ((value & 1) == 0) {
                len += value;
            } else {
                if (single) {
                    len += value;
                    single = false;
                } else {
                    len += value - 1;
                }
            }
        }
        return len;
    }

    /**
     * 【使用数组模拟哈希表】
     * 执行用时 :2 ms, 在所有 Java 提交中击败了76.48% 的用户
     * 内存消耗 :37.8 MB, 在所有 Java 提交中击败了5.25%的用户
     */
    public int longestPalindrome2(String s) {
        if (s == null || s.length() == 0) return 0;
        int len = 0;
        boolean single = true;
        char[] array = s.toCharArray();
        int[] hash = new int[256];
        for (char c : array) {
            hash[c]++;
        }
        for (int value : hash) {
            len += value;
            if ((value & 1) == 1) {
                if (single) {
                    single = false;
                } else {
                    len--;
                }
            }
        }
        return len;
    }

    /**
     * 【一次遍历】
     * 遍历偶数字符才加长度
     * 执行用时 :2 ms, 在所有 Java 提交中击败了76.48% 的用户
     * 内存消耗 :37.3 MB, 在所有 Java 提交中击败了5.25%的用户
     */
    public int longestPalindrome3(String s) {
        if (s == null || s.length() == 0) return 0;
        int len = 0;
        char[] array = s.toCharArray();
        int[] hash = new int[256];
        for (char c : array) {
            if ((hash[c] & 1) == 1) { // 奇数
                len += 2; // 总是在遍历第偶数个字符时才加长度
            }
            hash[c]++;
        }
        if (len < s.length()) len++; // 如果小于，肯定漏掉了奇数字符，只用加一个就好
        return len;
    }

    @Test
    public void test() {
        System.out.println(longestPalindrome("abccccdd")); // 7
        System.out.println(longestPalindrome("abbbaa")); // 5

        System.out.println(longestPalindrome2("abccccdd")); // 7
        System.out.println(longestPalindrome2("abbbaa")); // 5

        System.out.println(longestPalindrome3("abccccdd")); // 7
        System.out.println(longestPalindrome3("abbbaa")); // 5
    }
}
