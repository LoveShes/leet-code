package offer;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/fan-zhuan-dan-ci-shun-xu-lcof/
 */
public class T58I_翻转单词顺序 {
    /**
     * 执行用时 :3 ms, 在所有 Java 提交中击败了66.50% 的用户
     * 内存消耗 :41 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public String reverseWords(String s) {
        if (s == null) return "";
        String[] split = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = split.length - 1; i >= 0; i--) {
            if (!split[i].equals("")) {
                sb.append(split[i]).append(" ");
            }
        }
        if (sb.length() >= 1) sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /**
     * TODO【先整体反序，再单词反序】
     */
    public String reverseWords2(String s) {
        if (s == null) return "";

        return null;
    }

    /**
     * TODO【从后往前遍历】
     */
    public String reverseWords3(String s) {
        if (s == null) return "";

        return null;
    }

    @Test
    public void test() {
        System.out.println("[" + reverseWords("the  sky is blue! ") + "]");
        System.out.println("[" + reverseWords("  hello world!     ") + "]");
        System.out.println("[" + reverseWords("") + "]");
        System.out.println("[" + reverseWords(" ") + "]");
        System.out.println("[" + reverseWords("   ") + "]");
    }

    @Test
    public void test2() {
        System.out.println("[" + reverseWords2("the  sky is blue! ") + "]");
        System.out.println("[" + reverseWords2("  hello world!     ") + "]");
        System.out.println("[" + reverseWords2("") + "]");
        System.out.println("[" + reverseWords2(" ") + "]");
        System.out.println("[" + reverseWords2("   ") + "]");
    }

    @Test
    public void test3() {
        System.out.println("[" + reverseWords3("the  sky is blue! ") + "]");
        System.out.println("[" + reverseWords3("  hello world!     ") + "]");
        System.out.println("[" + reverseWords3("") + "]");
        System.out.println("[" + reverseWords3(" ") + "]");
        System.out.println("[" + reverseWords3("   ") + "]");
    }
}
