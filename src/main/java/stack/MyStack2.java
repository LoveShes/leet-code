package stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * T225_用队列实现栈
 * https://leetcode-cn.com/problems/implement-stack-using-queues/description/
 * 【方法2】使用一个队列，存的时候就逆序存
 */
public class MyStack2 {

    Queue<Integer> queue;

    /** Initialize your data structure here. */
    public MyStack2() {
        queue = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue.offer(x);
        int size = queue.size();
        for (int i = 0; i < size - 1; i++) {
            queue.offer(queue.poll());
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue.poll();
    }

    /** Get the top element. */
    public int top() {
        return queue.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }

    public static void main(String[] args) {
        MyStack2 stack = new MyStack2();
        stack.push(1);
        stack.push(2);
        System.out.println(stack.top());
        stack.push(3);
        stack.push(4);

        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
    }
}
