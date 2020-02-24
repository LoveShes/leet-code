package stack;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/implement-stack-using-queues/
 */
public class T225_用队列实现栈 {
    @Test
    public void test() {
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

    @Test
    public void test2() {
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

/**
 * 【方法1】使用2个队列，每次从队列中取出最后面的元素，另一个队列临时存放其余元素
 */
class MyStack {

    Queue<Integer> q1;
    Queue<Integer> q2;

    public MyStack() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    public void push(int x) {
        q1.offer(x);
    }

    public int pop() {
        int element = q1.poll();
        ;
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

    public int top() {
        int element = q1.poll();
        ;
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

    public boolean empty() {
        return q1.isEmpty();
    }
}


/**
 * 【方法2】使用一个队列，存的时候就逆序存
 */
class MyStack2 {

    Queue<Integer> queue;

    public MyStack2() {
        queue = new LinkedList<>();
    }

    public void push(int x) {
        queue.offer(x);
        int size = queue.size();
        for (int i = 0; i < size - 1; i++) {
            queue.offer(queue.poll());
        }
    }

    public int pop() {
        return queue.poll();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}