package stack;

import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * T232_用栈实现队列
 * https://leetcode-cn.com/problems/implement-queue-using-stacks/
 */
public class MyQueue {

    private Stack<Integer> inStack;
    private Stack<Integer> outStack;

    public MyQueue() {
        inStack = new Stack<>();
        outStack = new Stack<>();
    }

    public void push(int x) {
        inStack.push(x);
    }

    public int pop() {
        checkOutStack();
        return outStack.pop();
    }

    public int peek() {
        checkOutStack();
        return outStack.peek();
    }

    private void checkOutStack() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
    }

    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    @Test
    public void test() {
        MyQueue queue = new MyQueue();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        
        System.out.println(12345);

        while (!queue.empty()) {
            System.out.println(queue.pop());
        }
    }
}
