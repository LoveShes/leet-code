package offer;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/
 */
public class T10II_青蛙跳台阶问题 {
    /**
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :36.1 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int numWays(int n) {
        if (n <= 1) return 1;
        int first = 1;
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
        System.out.println(numWays(0));
        System.out.println(numWays(4));
        System.out.println(numWays(5));
    }
}
