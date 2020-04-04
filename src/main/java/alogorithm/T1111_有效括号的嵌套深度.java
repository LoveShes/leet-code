package alogorithm;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/maximum-nesting-depth-of-two-valid-parentheses-strings/
 */
public class T1111_有效括号的嵌套深度 {
    /**
     * 【贪心】
     * 尽量让A、B的嵌套深度相同。遇到左括号就深度+1，挑深度小的；遇到右括号就深度-1，挑深度大的。
     * 执行用时 :1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :39.8 MB, 在所有 Java 提交中击败了7.14%的用户
     */
    public int[] maxDepthAfterSplit(String seq) {
        int[] ans = new int[seq.length()];
        int depthA = 0, depthB = 0;
        char[] array = seq.toCharArray();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == '(') {
                // 遇到左括号就挑深度小的
                if (depthA < depthB) {
                    depthA++;
                    ans[i] = 0;
                } else {
                    depthB++;
                    ans[i] = 1;
                }
            } else {
                // 遇到右括号挑深度大的
                if (depthA > depthB) {
                    depthA--;
                    ans[i] = 0;
                } else {
                    depthB--;
                    ans[i] = 1;
                }
            }
        }
        return ans;
    }

    /**
     * 【分组】
     * 不是左括号就是右括号，把左右括号分别按奇偶进行分组，目的是尽量减少嵌套，让连续的((或者))分在不同组
     * 执行用时 :1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :39.8 MB, 在所有 Java 提交中击败了7.14%的用户
     */
    public int[] maxDepthAfterSplit2(String seq) {
        int[] ans = new int[seq.length()];
        int idx = 0;
        for (char c : seq.toCharArray()) {
            // 对左括号而言，奇数给A，偶数给B
            // 右括号则反过来，奇数给B，偶数给A
            ans[idx++] = c == '(' ? idx & 1 : ((idx + 1) & 1);
        }
        return ans;
    }

    /**
     * 【进一步优化】
     * 由于(和)的ascii码是挨着的，故可以直接通过^运算来区分
     * 执行用时 :1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :40 MB, 在所有 Java 提交中击败了7.14%的用户
     */
    public int[] maxDepthAfterSplit3(String seq) {
        int[] ans = new int[seq.length()];
        int idx = 0;
        for (char c : seq.toCharArray()) {
            // 这里要用异或，比如都是(时，'('&1=0，相邻的2个左括号的idx&1就是1、0，用异或运算才能分到两个组去。
            ans[idx++] = (c ^ idx) & 1; // (c^idx)&1 == (c&1)^(idx&1)
        }
        return ans;
    }

    @Test
    public void test() {
        System.out.println(Arrays.toString(maxDepthAfterSplit("(()())")));
        System.out.println(Arrays.toString(maxDepthAfterSplit2("(()())")));
        System.out.println(Arrays.toString(maxDepthAfterSplit3("(()())")));

        System.out.println(Arrays.toString(maxDepthAfterSplit("()(())()")));
        System.out.println(Arrays.toString(maxDepthAfterSplit2("()(())()")));
        System.out.println(Arrays.toString(maxDepthAfterSplit3("()(())()")));
    }
}
