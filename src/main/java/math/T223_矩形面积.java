package math;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/rectangle-area/
 */
public class T223_矩形面积 {

    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int totalArea = (C-A)*(D-B) + (G-E)*(H-F);
        // 不相交
        if (E>=C || A >= G || B >= H || F>=D) {
            return totalArea;
        }
        // 注意这里对坐标的处理，直接计算长度会导致一个包含另一个矩形的计算出错
        int left = Math.max(A, E);
        int right = Math.min(C, G);
        int bottom = Math.max(B, F);
        int top = Math.min(D, H);
        return totalArea-(top-bottom)*(right-left);
    }

    @Test
    public void test() {
        System.out.println(computeArea( -3, 0, 3, 4, 0, -1, 9, 2)); // 45
        System.out.println(computeArea( 0, 0, 0, 0, -1, -1, 1, 1)); // 4
    }

}
