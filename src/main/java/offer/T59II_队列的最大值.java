package offer;

import org.junit.jupiter.api.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof/
 */
public class T59II_队列的最大值 {
    @Test
    public void test() {
        MaxQueue queue = new MaxQueue();
        for (int i = 10; i > 0; i--) {
            queue.push_back(i);
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(queue.max_value());
            System.out.println(queue.pop_front());
        }
    }
}

/**
 * 【双端队列】
 * 当一个元素进入队列的时候，它前面所有比它小的元素就不会再对答案产生影响。
 * 执行用时 :37 ms, 在所有 Java 提交中击败了81.88% 的用户
 * 内存消耗 :45.7 MB, 在所有 Java 提交中击败了100.00%的用户
 */
class MaxQueue {
    Queue<Integer> valueQueue = new LinkedList();
    Deque<Integer> maxQueue = new LinkedList(); // 递减

    public int max_value() {
        if (maxQueue.isEmpty()) {
            return -1;
        }
        return maxQueue.peek();
    }

    public void push_back(int value) {
        valueQueue.offer(value);
        // 之前小的，出队对求最大值没影响
        while (!maxQueue.isEmpty() && value > maxQueue.peekLast()) {
            maxQueue.pollLast(); // 取出对结果无影响的元素
        }
        maxQueue.offer(value); // 保留比队列小的元素（因为）
    }

    public int pop_front() {
        if (valueQueue.isEmpty()) {
            return -1;
        }
        int front = valueQueue.poll();
        if (front == maxQueue.peek()) { // 意味着队列头就是最大值，出队之后要把对应最大值删除
            maxQueue.poll();
        }
        return front;
    }
}