package stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * T225_用队列实现栈
 * https://leetcode-cn.com/problems/implement-stack-using-queues/
 * 【方法1】使用2个队列，每次从队列中取出最后面的元素，另一个队列临时存放其余元素
 */
public class MyStack {

    Queue<Integer> q1;
    Queue<Integer> q2;

    /** Initialize your data structure here. */
    public MyStack() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        q1.offer(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        int element = q1.poll();;
        while (!q1.isEmpty()) {
            q2.offer(element);
            element = q1.poll();
        }
        Queue<Integer> temp = new LinkedList<>();
        temp = q1;
        q1 = q2;
        q2 = temp;
        return element;
    }

    /** Get the top element. */
    public int top() {
        int element = q1.poll();;
        while (!q1.isEmpty()) {
            q2.offer(element);
            element = q1.poll();
        }
        q2.offer(element);
        Queue<Integer> temp = new LinkedList<>();
        temp = q1;
        q1 = q2;
        q2 = temp;
        return element;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q1.isEmpty();
    }

    public static void main(String[] args) {
        MyStack stack = new MyStack();
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
