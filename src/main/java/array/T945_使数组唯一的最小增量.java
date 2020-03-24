package array;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/minimum-increment-to-make-array-unique/
 */
public class T945_使数组唯一的最小增量 {
    /**
     * 【排序后递增1】
     * 执行用时 :16 ms, 在所有 Java 提交中击败了77.81% 的用户
     * 内存消耗 :45.7 MB, 在所有 Java 提交中击败了92.16%的用户
     */
    public int minIncrementForUnique(int[] A) {
        Arrays.sort(A);
        int count = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i - 1] >= A[i]) { // 前面的递增后可能会大于等于后面的
                count += (A[i - 1] - A[i] + 1);
                A[i] = A[i - 1] + 1; // 不要忘记递增了
            }
        }
        return count;
    }

    /**
     * 【计数排序】
     * 执行用时 :5 ms, 在所有 Java 提交中击败了97.81% 的用户
     * 内存消耗 :47.2 MB, 在所有 Java 提交中击败了76.47%的用户
     */
    public int minIncrementForUnique2(int[] A) {
        if (A == null || A.length == 0) return 0;

        int count = 0;
        int[] status = new int[50000];

        for (int num : A) {
            status[num]++;
        }

        for (int i = 0; i < 50000; i++) {
            if (status[i] > 1) { // 当前位置不止一个
                int expect = status[i] - 1; // 需要move的个数
                count += expect; // 移动1位到下一个数去
                status[i + 1] += expect;
            }
        }
        return count;
    }

    @Test
    public void test() {
        System.out.println(minIncrementForUnique(new int[]{1, 2, 2})); // 1
        System.out.println(minIncrementForUnique(new int[]{3, 2, 1, 2, 1, 7})); // 6

        System.out.println(minIncrementForUnique2(new int[]{1, 2, 2})); // 1
        System.out.println(minIncrementForUnique2(new int[]{40000, 40000, 40000})); // 3
        System.out.println(minIncrementForUnique2(new int[]{3, 2, 1, 2, 1, 7})); // 6
    }
}
