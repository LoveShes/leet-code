package math;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/rectangle-overlap/
 */
public class T836_矩形重叠 {
    /**
     * 【交叉情况太多，可以先找出不交叉的情况】
     * 2个矩形如下，通过比较线段来判断不相交的情况：
     * <pre>
     *        y2                y4
     *    ┌───────┐         ┌───────┐
     * x1 |       | x2   x3 |       | x4
     *    └───────┘         └───────┘
     *        y1                y3
     * </pre>
     * 不相交的情况有4种：①x3>=x2；②x4<=x1；③y3>=y2；④y4<=y1
     * <p>
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :36.9 MB, 在所有 Java 提交中击败了5.49%的用户
     */
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        int x1 = rec1[0], y1 = rec1[1];
        int x2 = rec1[2], y2 = rec1[3];
        int x3 = rec2[0], y3 = rec2[1];
        int x4 = rec2[2], y4 = rec2[3];
        // 找出不交叉的情况
        if (x3 >= x2 || x4 <= x1 || y3 >= y2 || y4 <= y1) return false;
        return true;
    }

    @Test
    public void test() {
        assert isRectangleOverlap(new int[]{0, 0, 2, 2}, new int[]{1, 1, 3, 3});
        assert !isRectangleOverlap(new int[]{0, 0, 1, 1}, new int[]{1, 0, 2, 1});
    }
}
