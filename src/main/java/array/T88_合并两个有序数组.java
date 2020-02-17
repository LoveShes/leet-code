package array;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/merge-sorted-array/
 */
public class T88_合并两个有序数组 {
    // 【更优】从后往前，先找大的放在后面
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) {
            return;
        }
        int ai = m + n - 1;
        int le = m - 1;
        int re = n - 1;
        while (le >= 0 && re >= 0) {
            if (nums1[le] > nums2[re]) {
                nums1[ai--] = nums1[le--];
            } else {
                nums1[ai--] = nums2[re--];
            }
        }
        // 只用考虑nums2还有剩余的情况，因为nums1有剩余意味着nums2已经排完，则此时nums1剩下未排的自然有序
        // re >= 0时会执行。又因为 re < 0 时，复制的长度小于1，故不会执行复制操作
        System.arraycopy(nums2, 0, nums1, 0, re + 1);
    }

    // 借用临时数组
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) {
            return;
        }
        int[] temp = new int[m];
        int li = 0;
        int ri = 0;
        int ai = 0;

        for (int i = 0; i < m; i++) {
            temp[i] = nums1[i];
        }
        while (li < m && ri < n) {
            if (temp[li] < nums2[ri]) {
                nums1[ai++] = temp[li++];
            } else {
                nums1[ai++] = nums2[ri++];
            }
        }
        while (li < m) {
            nums1[ai++] = temp[li++];
        }
        while (ri < n) {
            nums1[ai++] = nums2[ri++];
        }
    }

    @Test
    public void test() {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = {2, 5, 6};
        int n = 3;

        nums1 = new int[]{2, 0};
        m = 1;
        nums2 = new int[]{1};
        n = 1;

        nums1 = new int[]{0};
        m = 0;
        nums2 = new int[]{1};
        n = 1;

        merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));
    }
}
