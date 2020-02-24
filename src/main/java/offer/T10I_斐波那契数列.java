package offer;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/
 */
public class T10I_斐波那契数列 {
    /**
     * 【求余的转化】
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :36 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int fib(int n) {
        if (n <= 1) return n;
        int first = 0;
        int second = 1;
        int result = 0;
        while (--n > 0) {
            result = first + second;
            if (result >= 1000000007) {
                result -= 1000000007;
            }
            first = second;
            second = result;
        }
        return result;
    }

    @Test
    public void test() {
        System.out.println(fib(1000000000));
    }
}
