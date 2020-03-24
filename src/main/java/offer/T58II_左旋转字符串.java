package offer;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/
 */
public class T58II_左旋转字符串 {
    /**
     * 【字符串切片拼接】
     * 执行用时 :1 ms, 在所有 Java 提交中击败了46.80% 的用户
     * 内存消耗 :39.1 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public String reverseLeftWords(String s, int n) {
        return s.substring(n) + s.substring(0, n);
    }

    /**
     * 【三次翻转】
     * 假如 s="abcdefg", n=2, 可分为"ab|cdefg"
     * 第1次翻转(左边)：ba|cdefg
     * 第2次翻转(右边)：ba|gfedc
     * 第3次翻转(整个)：cdefgab
     * <p>
     * 执行用时 :2 ms, 在所有 Java 提交中击败了28.99% 的用户
     * 内存消耗 :39.4 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public String reverseLeftWords2(String s, int n) {
        char[] array = s.toCharArray();
        reverse(array, 0, n - 1);
        reverse(array, n, array.length - 1);
        reverse(array, 0, array.length - 1);
        return String.valueOf(array);
    }

    private void reverse(char[] array, int start, int end) {
        while (start < end) {
            char temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }


    @Test
    public void test() {
        System.out.println(reverseLeftWords("abcdefg", 2)); // "cdefgab"
        System.out.println(reverseLeftWords("lrloseumgh", 6)); // "umghlrlose"

        System.out.println(reverseLeftWords2("abcdefg", 2)); // "cdefgab"
        System.out.println(reverseLeftWords2("lrloseumgh", 6)); // "umghlrlose"
    }
}
