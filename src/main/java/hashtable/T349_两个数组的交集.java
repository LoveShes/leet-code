package hashtable;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * https://leetcode-cn.com/problems/intersection-of-two-arrays/
 */
// TODO
public class T349_两个数组的交集 {

    public int[] intersection(int[] nums1, int[] nums2) {
        // Set<Integer> set1 = new TreeSet<>();
        // Set<Integer> set2 = new TreeSet<>();
        // for (int i = 0; i < nums1.length; i++) {
        //     set1.add(nums1[i]);
        // }
        // for (int i = 0; i < nums2.length; i++) {
        //     set2.add(nums2[i]);
        // }
        // int len = set.size();
        // int[] result = new int[len];
        // Iterator<Integer> it = set.iterator();
        // for (int i = 0; i < len; i++) {
        //     result[i] = it.next();
        // }
        // return result;
        return new int[]{};
    }

    @Test
    public void test() {
        int[] num1 = new int[]{4, 9, 5};
        int[] num2 = new int[]{9, 4, 9, 8, 4};
        int[] result = intersection(num1, num2);
        System.out.println(Arrays.toString(result)); // 9,4
    }
}
