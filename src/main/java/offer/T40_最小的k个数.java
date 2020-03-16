package offer;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/
 */
public class T40_最小的k个数 {
    /**
     * 【排序】
     * 执行用时 :7 ms, 在所有 Java 提交中击败了76.06% 的用户
     * 内存消耗 :43.4 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        if (arr == null || k <= 0) return new int[0];
        Arrays.sort(arr);
        int[] ans = new int[k];
        System.arraycopy(arr, 0, ans, 0, k);
        return ans;
    }

    /**
     * 【最大堆（使用优先队列实现）】
     * 执行用时 :13 ms, 在所有 Java 提交中击败了45.01% 的用户
     * 内存消耗 :43.9 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int[] getLeastNumbers2(int[] arr, int k) {
        if (arr == null || k <= 0) return new int[0];
        int[] ans = new int[k];
        Queue<Integer> queue = new PriorityQueue<>((x1, x2) -> x2 - x1);
        for (int i = 0; i < k; i++) {
            queue.offer(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (arr[i] < queue.peek()) {
                queue.poll();
                queue.offer(arr[i]);
            }
        }
        int index = 0;
        for (Integer integer : queue) {
            ans[index++] = integer;
        }
        return ans;
    }

    private void siftDown(int index, int heapSize, int[] array) {
        int element = array[index];
        int half = heapSize >> 1;
        while (index < half) { // index小于第1个叶子节点的索引（非叶子节点的数量）
            // 有2种情况
            // 1. 只有左子节点
            // 2. 同时有左右子节点

            // 默认采用左子节点
            int childIndex = (index << 1) + 1;
            int child = array[childIndex];

            // 右子节点
            int rightIndex = childIndex + 1;
            // 选出左右子节点最大的
            if (rightIndex < heapSize && array[rightIndex] > child) {
                child = array[childIndex = rightIndex];
            }

            if (element >= child) {
                break;
            }
            array[index] = child;
            index = childIndex;
        }
        array[index] = element;
    }

    private void swap(int i1, int i2, int[] array) {
        int temp = array[i1];
        array[i1] = array[i2];
        array[i2] = temp;
    }

    @Test
    public void test() {
        System.out.println(Arrays.toString(getLeastNumbers(new int[]{3, 2, 1}, 2)));
        System.out.println(Arrays.toString(getLeastNumbers2(new int[]{3, 2, 1}, 2)));
    }
}
