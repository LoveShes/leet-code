package array;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/intersection-of-two-arrays/
 */
public class T349_两个数组的交集 {

    /**
     * 【利用集合】
     * 执行用时 :3 ms, 在所有 Java 提交中击败了96.78% 的用户
     * 内存消耗 :40 MB, 在所有 Java 提交中击败了5.15%的用户
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        int[] ans = new int[Math.min(nums1.length, nums2.length)];
        for (int num : nums1) {
            set1.add(num);
        }
        for (int num : nums2) {
            set2.add(num);
        }
        Iterator<Integer> it = set1.iterator();
        int index = 0;
        while (it.hasNext()) {
            Integer next = it.next();
            if (set2.contains(next)) {
                ans[index++] = next;
            }
        }
        return Arrays.copyOf(ans, index);
    }

    /**
     * 【Java自带求交集方法】
     * 对于set，有：
     * - addAll()    求并集
     * - retainAll() 求交集
     * - removeAll() 求差集
     * <p>
     * 执行用时 :4 ms, 在所有 Java 提交中击败了67.85% 的用户
     * 内存消耗 :40.2 MB, 在所有 Java 提交中击败了5.15%的用户
     */
    public int[] intersection2(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        int[] ans = new int[Math.min(nums1.length, nums2.length)];
        for (int num : nums1) {
            set1.add(num);
        }
        for (int num : nums2) {
            set2.add(num);
        }
        set1.retainAll(set2); // 求交集
        int index = 0;
        for (Integer num : set1) {
            ans[index++] = num;
        }
        return Arrays.copyOf(ans, index);
    }

    @Test
    public void test() {
        int[] num1 = new int[]{4, 9, 5};
        int[] num2 = new int[]{9, 4, 9, 8, 4};
        int[] result = intersection(num1, num2);
        System.out.println(Arrays.toString(result)); // 9,4

        int[] num3 = new int[]{1, 2, 2, 3};
        int[] num4 = new int[]{2, 2};
        int[] result2 = intersection2(num3, num4);
        System.out.println(Arrays.toString(result2)); // 2
    }
}
