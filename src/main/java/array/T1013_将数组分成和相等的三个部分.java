package array;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/partition-array-into-three-parts-with-equal-sum/
 */
public class T1013_将数组分成和相等的三个部分 {
    /**
     * 【先算出总和，再左右开弓】
     * 执行用时 :2 ms, 在所有 Java 提交中击败了86.52% 的用户
     * 内存消耗 :44.5 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public boolean canThreePartsEqualSum(int[] A) {
        if (A == null || A.length < 3) return false;
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
        }
        if (sum % 3 != 0) return false;
        int midSum = sum / 3;
        int left = 0, right = A.length - 1;
        int leftSum = 0, rightSum = 0;
        while (left < right && (leftSum += A[left]) != midSum) {
            left++;
        }
        while (left < right && (rightSum += A[right]) != midSum) {
            right--;
        }
        // 这里要保证2边端点中间至少要有1个值
        return right - left >= 2;
    }

    @Test
    public void test() {
        assert canThreePartsEqualSum(new int[]{0, 2, 1, -6, 6, -7, 9, 1, 2, 0, 1});
        assert canThreePartsEqualSum(new int[]{3, 3, 6, 5, -2, 2, 5, 1, -9, 4});
        assert !canThreePartsEqualSum(new int[]{0, 2, 1, -6, 6, 7, 9, -1, 2, 0, 1});
        assert !canThreePartsEqualSum(new int[]{1, -1, 1, -1});
    }
}
