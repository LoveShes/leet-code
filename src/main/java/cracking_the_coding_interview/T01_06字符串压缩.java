package cracking_the_coding_interview;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/compress-string-lcci/
 */
public class T01_06字符串压缩 {
    /**
     * 执行用时 :4 ms, 在所有 Java 提交中击败了94.41% 的用户
     * 内存消耗 :39 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public String compressString(String S) {
        if (S == null || S.length() == 0) return "";
        char[] chars = S.toCharArray();
        char lastChar = chars[0];
        int currCount = 1;
        StringBuilder sb = new StringBuilder();
        sb.append(lastChar); // 先添加第一个字符
        for (int i = 1, len = chars.length; i < len; i++) {
            char currChar = chars[i];
            if (currChar == lastChar) {
                currCount++;
            } else {
                sb.append(currCount).append(currChar);
                lastChar = currChar;
                currCount = 1;
            }
        }
        sb.append(currCount);
        if (chars.length <= sb.length()) return S;
        return sb.toString();
    }

    @Test
    public void test() {
        System.out.println(compressString("a")); // "a"
        System.out.println(compressString("aabcccccaaa")); // "a2b1c5a3"
        System.out.println(compressString("abbccd")); // "abbccd"
    }
}
