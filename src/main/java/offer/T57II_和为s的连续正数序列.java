package offer;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof/
 */
public class T57II_和为s的连续正数序列 {
    /**
     * 【双指针】
     * 执行用时 :4 ms, 在所有 Java 提交中击败了75.52% 的用户
     * 内存消耗 :36.9 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int[][] findContinuousSequence(int target) {
        if (target < 3) return new int[][]{};

        int small = 1;
        int big = 2;
        int mid = (target + 1) >> 1;
        int sum = small + big;
        List<int[]> lists = new ArrayList<>();

        while (small < mid) {
            if (sum == target) {
                lists.add(getList(small, big));
                big++;
                sum += big;
            } else if (sum < target) {
                big++; // 包含更多数字
                sum += big;
            } else {
                sum -= small;
                small++; // 减少最左边的数字
            }
        }
        // toArray(T[] t)方法返回一个类T的数组，这个数组包含了类T中的所有元素。
        return lists.toArray(new int[lists.size()][]);
    }

    /**
     * 【双指针-while优化】
     * 执行用时 :2 ms, 在所有 Java 提交中击败了94.66% 的用户
     * 内存消耗 :37.2 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int[][] findContinuousSequence2(int target) {
        if (target < 3) return new int[][]{};

        int small = 1;
        int big = 2;
        int mid = (target + 1) >> 1;
        int sum = small + big;
        List<int[]> lists = new ArrayList<>();

        while (small < mid) {
            while (sum < target) {
                big++;
                sum += big;
            }
            while (sum > target) {
                sum -= small;
                small++;
            }
            if (sum == target) {
                lists.add(getList(small, big));
                big++;
                sum += big;
            }
        }
        // toArray(T[] t)方法返回一个类T的数组，这个数组包含了类T中的所有元素。
        return lists.toArray(new int[0][]);
    }

    /**
     * 【优化重复代码】
     * 执行用时 :2 ms, 在所有 Java 提交中击败了94.66% 的用户
     * 内存消耗 :37.3 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int[][] findContinuousSequence3(int target) {
        if (target < 3) return new int[][]{};

        int small = 1;
        int big = 2;
        int mid = (target + 1) >> 1;
        int sum = small + big;
        List<int[]> lists = new ArrayList<>();

        while (small < mid) {
            while (sum > target) {
                sum -= small;
                small++;
            }
            if (sum == target) {
                lists.add(getList(small, big));
            }
            big++;
            sum += big;
        }
        // toArray(T[] t)方法返回一个类T的数组，这个数组包含了类T中的所有元素。
        return lists.toArray(new int[0][]);
    }

    /**
     * 【数学方法-利用求和公式】
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :37.4 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int[][] findContinuousSequence4(int target) {
        /**
         * 连续数字为等差数列，设首项为x，末项为x+i，则和为s=(x+x+i)*(i+1)/2=t
         * 即 2(i+1)x+(i+1)i-2t=0，求得 x=(t-(i+1)i/2)/(i+1) 为正整数，则有
         * ① (i+1)i/2 < t
         * ② (t-(i+1)i/2) % (i+1) == 0
         * i从1开始递增，求出x后，有y=x+i
         */
        if (target < 3) return new int[][]{};

        int i = 1, temp = 0;
        List<int[]> lists = new LinkedList<>();

        while ((temp = (i + 1) * i >> 1) < target) {
            if ((target - temp) % (i + 1) == 0) {
                int start = (target - temp) / (i + 1);
                lists.add(0, getList(start, start + i));
            }
            i++;
        }
        // toArray(T[] t)方法返回一个类T的数组，这个数组包含了类T中的所有元素。
        return lists.toArray(new int[0][]);
    }

    private int[] getList(int start, int end) {
        int[] res = new int[end - start + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = i + start;
        }
        return res;
    }

    @Test
    public void test() {
        System.out.println(Arrays.deepToString(findContinuousSequence(9))); // [[2,3,4],[4,5]]
        System.out.println(Arrays.deepToString(findContinuousSequence(15))); // [[1,2,3,4,5],[4,5,6],[7,8]]
    }

    @Test
    public void test2() {
        System.out.println(Arrays.deepToString(findContinuousSequence2(9))); // [[2,3,4],[4,5]]
        System.out.println(Arrays.deepToString(findContinuousSequence2(15))); // [[1,2,3,4,5],[4,5,6],[7,8]]
    }

    @Test
    public void test3() {
        System.out.println(Arrays.deepToString(findContinuousSequence3(9))); // [[2,3,4],[4,5]]
        System.out.println(Arrays.deepToString(findContinuousSequence3(15))); // [[1,2,3,4,5],[4,5,6],[7,8]]
    }

    @Test
    public void test4() {
        System.out.println(Arrays.deepToString(findContinuousSequence4(9))); // [[2,3,4],[4,5]]
        System.out.println(Arrays.deepToString(findContinuousSequence4(15))); // [[1,2,3,4,5],[4,5,6],[7,8]]
    }

    @Test
    public void testGetList() {
        System.out.println(Arrays.toString(getList(1, 2)));
        System.out.println(Arrays.toString(getList(1, 1)));
    }
}
