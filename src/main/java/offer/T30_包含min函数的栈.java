package offer;

import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/bao-han-minhan-shu-de-zhan-lcof/
 */
public class T30_包含min函数的栈 {
    @Test
    public void test() {
        // MinStack minStack = new MinStack();
        // MinStack2 minStack = new MinStack2();
        // MinStack3 minStack = new MinStack3();
        MinStack4 minStack = new MinStack4();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        assert minStack.min() == -3;
        assert minStack.top() == -3;
        minStack.pop();
        assert minStack.top() == 0;
        assert minStack.min() == -2;
        minStack.pop();
        minStack.pop();
    }

    @Test
    public void test2() {
        // ["top","min","push","top","min","pop","min"]
        // [[],[],[-2147483648],[],[],[],[]]
        // MinStack3 minStack = new MinStack3();
        MinStack4 minStack = new MinStack4();
        minStack.push(2147483646);
        minStack.push(2147483646);
        minStack.push(2147483647);
        assert minStack.top() == 2147483647;
        minStack.pop();
        assert minStack.min() == 2147483646;
        minStack.pop();
        assert minStack.min() == 2147483646;
        minStack.pop(); // 空

        minStack.push(2147483647);
        assert minStack.top() == 2147483647;
        minStack.push(-2147483648);
        assert minStack.top() == -2147483648;
        assert minStack.min() == -2147483648;
        minStack.pop();
        assert minStack.min() == 2147483647;
    }
}

/**
 * 【双栈】一个普通栈，另一个放到目前为止的min
 * 执行用时 :19 ms, 在所有 Java 提交中击败了94.97% 的用户
 * 内存消耗 :41.4 MB, 在所有 Java 提交中击败了100.00%的用户
 */
class MinStack {
    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> min = new Stack<>();

    public void push(int x) {
        stack.push(x);
        if (min.isEmpty()) {
            min.push(x);
        } else {
            min.push(x < min.peek() ? x : min.peek());
        }
    }

    public void pop() {
        stack.pop();
        min.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return min.peek();
    }
}

/**
 * 【双栈-优化】使用数组来模拟栈
 * 执行用时 :19 ms, 在所有 Java 提交中击败了94.97% 的用户
 * 内存消耗 :43.7 MB, 在所有 Java 提交中击败了100.00%的用户
 */
class MinStack2 {
    int[] stack = new int[20001];
    int[] min = new int[20001];
    int top = -1; // 当前栈顶

    public void push(int x) {
        stack[top + 1] = x;
        if (top == -1) {
            min[top + 1] = x;
        } else {
            min[top + 1] = x < min[top] ? x : min[top];
        }
        top++;
    }

    public void pop() {
        top--;
    }

    public int top() {
        return stack[top];
    }

    public int min() {
        return min[top];
    }
}

/**
 * 【单栈】先压入当前值，再压入当前最小值
 * 执行用时 :20 ms, 在所有 Java 提交中击败了74.75% 的用户
 * 内存消耗 :41.1 MB, 在所有 Java 提交中击败了100.00%的用户
 */
class MinStack3 {
    private Stack<Integer> stack = new Stack<>();

    public void push(int x) {
        int min;
        if (stack.isEmpty()) {
            min = x;
        } else {
            min = Math.min(x, stack.peek()); // 这里上次的min一定要现场取，而不能直接存起来，否则stack空了，min还是上次的值
        }
        stack.push(x);
        stack.push(min);
    }

    public void pop() {
        stack.pop(); // 弹出最小值
        stack.pop(); // 弹出当前值
    }

    public int top() {
        int minValue = stack.pop(); // 弹出最小值
        int top = stack.peek();
        stack.push(minValue); // 再把最小值放回去
        return top;
    }

    public int min() {
        return stack.peek();
    }
}

/**
 * 【单栈】用变量保存最小值，更新最小值之前先把次小值压入栈（避免pop之后找不到次小值）
 * 执行用时 :19 ms, 在所有 Java 提交中击败了94.97% 的用户
 * 内存消耗 :40.9 MB, 在所有 Java 提交中击败了100.00%的用户
 */
class MinStack4 {
    private Stack<Integer> stack = new Stack<>();
    private int min = Integer.MAX_VALUE;

    public void push(int x) {
        if (x <= min) {
            stack.push(min); // 保证最小值的下面就是次小值（且不为本值）
            min = x;
        }
        stack.push(x); // 这里才是本值
    }

    public void pop() {
        if (stack.pop() == min) { // 当前元素就是最小值，移除之后，栈顶绝对是最小值
            min = stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return min;
    }
}