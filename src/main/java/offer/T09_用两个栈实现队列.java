package offer;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/
 */
public class T09_用两个栈实现队列 {
    @Test
    public void test() {
        CQueue queue = new CQueue();
        for (int i = 0; i < 5; i++) {
            queue.appendTail(i);
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(queue.deleteHead());
        }
        System.out.println(queue.deleteHead());
    }

    @Test
    public void test2() {
        CStack stack = new CStack();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(stack.pop());
        }
        System.out.println(stack.pop());
    }
}

/**
 * 用两个栈实现队列
 * 执行用时 :54 ms, 在所有 Java 提交中击败了97.41% 的用户
 * 内存消耗 :45.9 MB, 在所有 Java 提交中击败了100.00%的用户
 */
class CQueue {

    private Stack<Integer> inStack;
    private Stack<Integer> outStack;

    public CQueue() {
        inStack = new Stack<>();
        outStack = new Stack<>();
    }

    public void appendTail(int value) {
        inStack.push(value);
    }

    public int deleteHead() {
        if (!outStack.isEmpty()) return outStack.pop();
        if (inStack.isEmpty()) return -1;
        while (!inStack.isEmpty()) {
            outStack.push(inStack.pop());
        }
        return outStack.pop();
    }
}

/**
 * 用一个队列实现栈
 */
class CStack {
    private Queue<Integer> q1;

    public CStack() {
        q1 = new LinkedList<>();
    }

    // 入栈
    public void push(int value) {
        q1.offer(value);
    }

    // 出栈
    public int pop() {
        for (int i = 1; i < q1.size(); i++) {
            q1.offer(q1.poll());
        }
        return q1.isEmpty() ? Integer.MIN_VALUE : q1.poll();
    }
}