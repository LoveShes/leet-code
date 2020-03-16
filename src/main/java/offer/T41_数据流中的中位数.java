package offer;

import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof/
 */
public class T41_数据流中的中位数 {
    @Test
    public void test() {
        MedianFinder mf = new MedianFinder();
        for (int i = 0; i < 10; i++) {
            mf.addNum(i);
        }
        System.out.println(mf.findMedian()); // 4.5
        mf.addNum(10);
        System.out.println(mf.findMedian()); // 5

    }
}

/**
 * 【双堆法】
 * 执行用时 :100 ms, 在所有 Java 提交中击败了48.56% 的用户
 * 内存消耗 :55 MB, 在所有 Java 提交中击败了100.00%的用户
 */
class MedianFinder {
    private Queue<Integer> leftQueue = new PriorityQueue<>((i1, i2) -> i2 - i1); // 最大堆
    private Queue<Integer> rightQueue = new PriorityQueue<>(); // 最小堆
    private int size = 0;

    public void addNum(int num) {
        if ((size & 1) == 0) { // 偶数插左边（先插左边）
            if (!rightQueue.isEmpty() && num > rightQueue.peek()) {
                int rightMin = rightQueue.poll();
                rightQueue.offer(num);
                num = rightMin;
            }
            leftQueue.offer(num);
        } else { // 奇数插右边
            if (!leftQueue.isEmpty() && num < leftQueue.peek()) {
                int leftMax = leftQueue.poll();
                leftQueue.offer(num);
                num = leftMax;
            }
            rightQueue.offer(num);
        }
        size++;
    }

    public double findMedian() {
        if ((size & 1) == 0) { // 偶数
            return (leftQueue.peek() + rightQueue.peek()) / 2.0;
        } else {
            return leftQueue.peek();
        }
    }
}
