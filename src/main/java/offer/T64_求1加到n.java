package offer;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/qiu-12n-lcof/
 */
public class T64_求1加到n {
    /**
     * 【短路求和】
     * 执行用时 :1 ms, 在所有 Java 提交中击败了58.88% 的用户
     * 内存消耗 :36.6 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int sumNums(int n) {
        // 一旦n==0就停止递归
        boolean flag = (n == 0 || (n += sumNums(n - 1)) > 0);
        return n;
    }

    @Test
    public void test() {
        System.out.println(sumNums(100)); // 5050
    }
}
