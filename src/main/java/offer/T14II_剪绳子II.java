package offer;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/jian-sheng-zi-ii-lcof/
 */
public class T14II_剪绳子II {
    /**
     * TODO【贪婪算法】
     * 由于此题要对结果取余，所以不能使用动态规划，并且提前取余会影响结果
     */
    public int cuttingRope(int n) {
        if (n <= 3) return n - 1;

        int res = 1;
        return res;
    }

    @Test
    public void test() {
        System.out.println(cuttingRope(2)); // 1
        System.out.println(cuttingRope(3)); // 2
        System.out.println(cuttingRope(10)); // 36
        System.out.println(cuttingRope(11)); // 54
        System.out.println(cuttingRope(12)); // 81
        System.out.println(cuttingRope(40)); // 2125764
        System.out.println(cuttingRope(1000)); // 772799356
    }
}
