package offer;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/
 */
public class T17_打印从1到最大的n位数 {
    /**
     * 【普通解法】
     * 执行用时 :1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :48 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int[] printNumbers(int n) {
        if (n <= 0) return new int[]{};
        int len = (int) Math.pow(10, n) - 1;
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            res[i] = i + 1;
        }
        return res;
    }

    /**
     * TODO【大数问题】
     */
    public int[] printNumbers2(int n) {
        if (n <= 0) return new int[]{};
        int len = (int) Math.pow(10, n) - 1;
        int[] res = new int[len];

        return res;
    }

    @Test
    public void test() {
        System.out.println(Arrays.toString(printNumbers2(3)));
    }
}
