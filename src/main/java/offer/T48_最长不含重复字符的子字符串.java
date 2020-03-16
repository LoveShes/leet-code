package offer;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof/
 */
public class T48_最长不含重复字符的子字符串 {
    /**
     * 【递归+哈希表】
     * 执行用时 :237 ms, 在所有 Java 提交中击败了5.08% 的用户
     * 内存消耗 :299.4 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        Set<Character> set = new HashSet<>();
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            max = set.size();
            if (set.contains(s.charAt(i))) { // 要注意的是，有重复的不是从重复位置开始往后找，而是从当前子串的索引1开始往后找
                max = Math.max(lengthOfLongestSubstring(s.substring(1)), max);
                break;
            }
            set.add(s.charAt(i));
        }
        return Math.max(max, set.size());
    }

    /**
     * 【动态规划】
     * 假设为ASCII码
     * 执行用时 :4 ms, 在所有 Java 提交中击败了93.72% 的用户
     * 内存消耗 :41.4 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) return 0;
        int len = s.length();
        int[] dp = new int[len]; // dp[i]表示以当前字符结尾的子串
        int[] position = new int[256];
        Arrays.fill(position, -1);
        // 初始状态
        dp[0] = 1;
        position[s.charAt(0)] = 0;
        int max = 1;
        // 开始往后找
        for (int i = 1; i < len; i++) {
            int index = position[s.charAt(i)]; // 看当前遍历字符是否出现过
            if (index < 0 || i - index > dp[i - 1]) { // 没出现过，或者在前一个子串之外
                dp[i] = dp[i - 1] + 1; // 可以放心加上当前字符
            } else { // 在前一个子串中
                dp[i] = i - index;
            }
            position[s.charAt(i)] = i; // 更新位置
            if (dp[i] > max) { // 从中找一个最大的
                max = dp[i];
            }
        }
        return max;
    }

    /**
     * 【滑动窗口】
     * [i,j)滑动，这里的处理非常巧妙，如果找到重复的字符串了，i就直接跳到重复字符的下一个位置
     * 执行用时 :3 ms, 在所有 Java 提交中击败了99.48% 的用户
     * 内存消耗 :39.7 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int lengthOfLongestSubstring3(String s) {
        if (s == null || s.length() == 0) return 0;
        int len = s.length();
        int[] position = new int[256];
        int max = 1;
        for (int i = 0, j = 0; j < len; j++) {
            // 例如当前为...zqwabcb...，i在z的位置，j在第2个b的位置，这个时候i就应该更新到第1个b的位置
            // 这里每次计算的都是以当前字符结尾的最长子串
            i = Math.max(position[s.charAt(j)], i);
            max = Math.max(max, j - i + 1); // 这里访问到的i是在c的位置，而j是在第2个b的位置，所以这里要加1
            position[s.charAt(j)] = j + 1; // 下次访问到同样的字符时，i就从该字符的下一位开始
        }
        return max;
    }

    @Test
    public void test() {
        assert lengthOfLongestSubstring(" ") == 1 : lengthOfLongestSubstring(" ");
        assert lengthOfLongestSubstring2("  ") == 1 : lengthOfLongestSubstring2("  ");
        assert lengthOfLongestSubstring("au") == 2 : lengthOfLongestSubstring("au");
        assert lengthOfLongestSubstring("abcabcbb") == 3 : lengthOfLongestSubstring("abcabcbb");
        assert lengthOfLongestSubstring("bbbbb") == 1 : lengthOfLongestSubstring("bbbbb");
        assert lengthOfLongestSubstring("pwwkew") == 3 : lengthOfLongestSubstring("pwwkew");
        assert lengthOfLongestSubstring("abcabsadghgfsahgsjfgafagcbb") == 6 : lengthOfLongestSubstring("abcabsadghgfsahgsjfgafagcbb");
    }

    @Test
    public void test2() {
        assert lengthOfLongestSubstring2(" ") == 1 : lengthOfLongestSubstring2(" ");
        assert lengthOfLongestSubstring2("  ") == 1 : lengthOfLongestSubstring2("  ");
        assert lengthOfLongestSubstring2("au") == 2 : lengthOfLongestSubstring2("au");
        assert lengthOfLongestSubstring2("abcabcbb") == 3 : lengthOfLongestSubstring2("abcabcbb");
        assert lengthOfLongestSubstring2("bbbbb") == 1 : lengthOfLongestSubstring2("bbbbb");
        assert lengthOfLongestSubstring2("pwwkew") == 3 : lengthOfLongestSubstring2("pwwkew");
        assert lengthOfLongestSubstring2("abcabsadghgfsahgsjfgafagcbb") == 6 : lengthOfLongestSubstring2("abcabsadghgfsahgsjfgafagcbb");
    }

    @Test
    public void test3() {
        assert lengthOfLongestSubstring3(" ") == 1 : lengthOfLongestSubstring3(" ");
        assert lengthOfLongestSubstring3("  ") == 1 : lengthOfLongestSubstring3("  ");
        assert lengthOfLongestSubstring3("au") == 2 : lengthOfLongestSubstring3("au");
        assert lengthOfLongestSubstring3("abcabcbb") == 3 : lengthOfLongestSubstring3("abcabcbb");
        assert lengthOfLongestSubstring3("bbbbb") == 1 : lengthOfLongestSubstring3("bbbbb");
        assert lengthOfLongestSubstring3("pwwkew") == 3 : lengthOfLongestSubstring3("pwwkew");
        assert lengthOfLongestSubstring3("abcabsadghgfsahgsjfgafagcbb") == 6 : lengthOfLongestSubstring3("abcabsadghgfsahgsjfgafagcbb");
    }
}
