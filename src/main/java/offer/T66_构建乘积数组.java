package offer;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/gou-jian-cheng-ji-shu-zu-lcof/
 */
public class T66_构建乘积数组 {
    /**
     * 【分成2部分】
     * 执行用时 :2 ms, 在所有 Java 提交中击败了83.15% 的用户
     * 内存消耗 :55.4 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int[] constructArr(int[] a) {
        if (a == null || a.length == 0) return new int[0];
        int len = a.length;
        int[] left = new int[len]; // 从左边开始乘到当前位置之前
        int[] right = new int[len];// 从右边开始乘到当前位置之后
        int[] ans = new int[len];

        left[0] = 1;
        for (int i = 1; i < len; i++) {
            left[i] = left[i - 1] * a[i - 1];
        }
        right[len - 1] = 1;
        for (int i = len - 2; i >= 0; i--) {
            right[i] = right[i + 1] * a[i + 1];
        }

        for (int i = 0; i < len; i++) {
            ans[i] = left[i] * right[i];
        }
        return ans;
    }

    /**
     * 【优化——2遍循环】
     * 执行用时 :1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :54.5 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int[] constructArr2(int[] a) {
        if (a == null || a.length == 0) return new int[0];
        int len = a.length;
        int[] ans = new int[len];
        int pre = 1; // 暂存上一步乘积结果
        for (int i = 0; i < len; i++) {
            ans[i] = pre;
            pre *= a[i];
        }
        // 到这里ans内已经为乘了左半部分的结果
        // 下面开始乘右边
        pre = 1;
        for (int i = len - 1; i >= 0; i--) {
            ans[i] *= pre;
            pre *= a[i];
        }
        return ans;
    }

    @Test
    public void test() {
        System.out.println(Arrays.toString(constructArr(new int[]{1, 2, 3, 4, 5}))); // [120,60,40,30,24]
        System.out.println(Arrays.toString(constructArr2(new int[]{1, 2, 3, 4, 5}))); // [120,60,40,30,24]
    }
}
