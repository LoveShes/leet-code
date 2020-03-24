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

    /**
     * 【快排】
     * 执行用时 :1 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :42.9 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int[] getLeastNumbers3(int[] arr, int k) {
        if (arr == null || k <= 0) return new int[0];
        quickSelect(arr, 0, arr.length - 1, k);
        return Arrays.copyOf(arr, k);
    }

    // 对[start,end]范围内的元素进行快排
    private void quickSelect(int[] nums, int start, int end, int k) {
        // 只剩下一个元素就退出（等于说明只剩下1个元素）
        if (start >= end) return;
        int left = start;
        int right = end;
        int pivot = nums[(left + right) / 2]; // 每次选择中间的作为轴点元素
        while (left <= right) {
            // 找到左边第1个大于等于轴点元素的位置
            while (left <= right && nums[left] < pivot) {
                left++;
            }
            // 找到右边第1个小于等于轴点元素的位置
            while (left <= right && nums[right] > pivot) {
                right--;
            }
            if (left <= right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
        }
        // 到这里，左边[0,left)都是小于pivot，右边(right,len-1]都是大于pivot
        if (right >= k) { // right==k时，左边+中间只有[0,right-1]，right还没有包含进去
            quickSelect(nums, start, right, k);
        } else { // 左边排序个数少于k个，还需要继续排右边未排序的
            quickSelect(nums, left, end, k);
        }
    }

    @Test
    public void test() {
        System.out.println(Arrays.toString(getLeastNumbers(new int[]{3, 2, 1}, 2)));
        System.out.println(Arrays.toString(getLeastNumbers2(new int[]{3, 2, 1}, 2)));
        System.out.println(Arrays.toString(getLeastNumbers3(new int[]{3, 2, 1}, 2)));
        System.out.println(Arrays.toString(getLeastNumbers(new int[]{0, 0, 1, 2, 4, 2, 2, 3, 1, 4}, 8)));
        System.out.println(Arrays.toString(getLeastNumbers3(new int[]{0, 0, 1, 2, 4, 2, 2, 3, 1, 4}, 8)));
        System.out.println(Arrays.toString(getLeastNumbers(new int[]{0, 1, 1, 2, 4, 4, 1, 3, 3, 2}, 6)));
        System.out.println(Arrays.toString(getLeastNumbers3(new int[]{0, 1, 1, 2, 4, 4, 1, 3, 3, 2}, 6)));

    }
}
