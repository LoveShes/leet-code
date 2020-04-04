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
     * 【大数问题】
     * n过大时数字可能会超范围，用字符串模拟数字
     */
    public String[] printNumbers2(int n) {
        if (n <= 0) return new String[]{};
        int len = (int) Math.pow(10, n) - 1;
        String[] res = new String[len];
        res[0] = "1";
        for (int i = 1; i < len; i++) {
            res[i] = incr(res[i - 1]);
        }
        return res;
    }

    // 加1
    private String incr(String s) {
        char[] array = s.toCharArray();
        int carry = 1;
        for (int i = array.length - 1; i >= 0; i--) {
            int num = array[i] - '0' + carry;
            if (num >= 10) {
                array[i] = (char) (num % 10 + '0');
                carry = 1;
            } else {
                array[i] = (char) (num + '0');
                carry = 0;
                break;
            }
        }
        String ans = new String(array);
        if (carry == 0) return ans;
        return "1" + ans;
    }

    @Test
    public void test() {
        // System.out.println(Arrays.toString(printNumbers(3)));
        // System.out.println(Arrays.toString(printNumbers2(3)));
        int len = 7;
        printNumbers2(8);
        // assert Arrays.toString(printNumbers(len)).equals(Arrays.toString(printNumbers2(len)));

        // System.out.println(incr("0"));
        // System.out.println(incr("1"));
        // System.out.println(incr("11"));
        // System.out.println(incr("19"));
        // System.out.println(incr("99"));
        // System.out.println(incr("199"));
        // System.out.println(incr("99999999999999999999999"));
    }
}
