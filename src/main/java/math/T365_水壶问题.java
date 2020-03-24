package math;

import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/water-and-jug-problem/
 */
public class T365_水壶问题 {
    /**
     * 【数学方法】
     * 没有第三个瓶子可用，最后的结果一定是x、y或者x+y瓶子中放的水
     * https://leetcode-cn.com/problems/water-and-jug-problem/solution/shui-hu-wen-ti-by-leetcode-solution/
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :36.5 MB, 在所有 Java 提交中击败了13.93%的用户
     */
    public boolean canMeasureWater(int x, int y, int z) {
        // 2个瓶子装不下肯定求不出来
        if (x + y < z) return false;
        // 要单独处理0的情况
        if (x == 0 || y == 0) return z == 0 || x + y == z;
        // 如果z是x、y最大公约数的倍数，就可以装得下
        return z % gcd(x, y) == 0;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    /**
     * 【DFS】
     * 在任意一个时刻，我们可以且仅可以采取以下几种操作：
     * 把 X 壶的水灌进 Y 壶，直至灌满或倒空；
     * 把 Y 壶的水灌进 X 壶，直至灌满或倒空；
     * 把 X 壶灌满；
     * 把 Y 壶灌满；
     * 把 X 壶倒空；
     * 把 Y 壶倒空。
     * <p>
     * 执行用时 :776 ms, 在所有 Java 提交中击败了5.04% 的用户
     * 内存消耗 :253.5 MB, 在所有 Java 提交中击败了5.06%的用户
     */
    public boolean canMeasureWater2(int x, int y, int z) {
        if (x + y < z) return false;
        Stack<Pair<Integer, Integer>> stack = new Stack<>();
        stack.push(new Pair<>(0, 0)); // 初始2个瓶子都是空的
        Set<Pair<Integer, Integer>> visitedState = new HashSet<>();
        while (!stack.empty()) {
            Pair<Integer, Integer> pop = stack.pop();
            int remainX = pop.getKey(); // x壶现在的容量
            int remainY = pop.getValue(); // y壶现在的容量
            // 找到符合要求的了
            if (remainX == z || remainY == z || remainX + remainY == z) return true;
            // 之前访问过了该状态了
            if (visitedState.contains(pop)) continue;
            visitedState.add(pop);
            // 把x壶灌满
            stack.push(new Pair<>(x, remainY));
            // 把y壶灌满
            stack.push(new Pair<>(remainX, y));
            // 把x壶倒空
            stack.push(new Pair<>(0, remainY));
            // 把y壶倒空
            stack.push(new Pair<>(remainX, 0));
            // 把x壶的水灌进y壶，直至灌满或倒空
            // 减去remainX表示x壶倒空，减去(y - remainY)表示y壶灌满
            stack.push(new Pair<>(remainX - Math.min(remainX, y - remainY), remainY + Math.min(remainX, y - remainY)));
            // 把y壶的水灌进x壶，直至灌满或倒空
            stack.push(new Pair<>(remainX + Math.min(remainY, x - remainX), remainY - Math.min(remainY, x - remainX)));
        }
        return false;
    }

    @Test
    public void test() {
        assert canMeasureWater(3, 5, 4);
        assert canMeasureWater(7, 9, 1);
        assert !canMeasureWater(2, 6, 5);

        assert canMeasureWater2(3, 5, 4);
        assert canMeasureWater2(7, 9, 1);
        assert !canMeasureWater2(2, 6, 5);
    }
}
