package stack;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/
 */
public class T150_逆波兰表达式求值 {

    /**
     * 使用栈，遇到数字就入栈，遇到操作符就取栈顶2个元素进行操作
     * 【耗时】 9ms（击败69.28%）
     * 【内存】 36.2MB（击败91.57%）
     */

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        Integer temp;
        int result = 0;
        for (String token : tokens) {
            // - 和 / 要注意顺序，栈顶是符号右边的值
            if ("+".equals(token)) {
                result = stack.pop() + stack.pop();
                stack.push(result);
            } else if ("-".equals(token)) {
                int right = stack.pop();
                int left = stack.pop();
                result = left - right;
                stack.push(result);
            } else if ("*".equals(token)) {
                result = stack.pop() * stack.pop();
                stack.push(result);
            } else if ("/".equals(token)) {
                int right = stack.pop();
                int left = stack.pop();
                result = left / right;
                stack.push(result);
            } else {
                temp = Integer.parseInt(token);
                stack.push(temp);
            }
        }
        return stack.pop();
    }

    /**
     * 【优化思路】
     *  1. 不使用官方的Stack，自己用数组模拟stack；
     *  2. 字符串不能直接使用==进行比较，调用equals方法可能太慢，使用switch...case；
     *  3. Integer.parseInt太慢，自己实现数值型字符串转数字的方法
     *  【耗时】 2ms（击败100.00%）
     *  【内存】 36.8MB（90.06%）
     */
public int evalRPN2(String[] tokens) {
    int length = tokens.length;
    int[] stack = new int[length];
    int top = -1;
    for (String token : tokens) {
        switch (token) {
            case "+": stack[top-1] += stack[top]; --top; break;
            case "-": stack[top-1] -= stack[top]; --top; break;
            case "*": stack[top-1] *= stack[top]; --top; break;
            case "/": stack[top-1] /= stack[top]; --top; break;
            default:  stack[++top] = calc(token);
        }
    }
    return stack[top];
}
private int calc(String s) {
    char[] chars = s.toCharArray();
    int result = 0;
    int pos = 1;
    for (char c : chars) {
        if (c == '-') {
            pos = -1;
        } else {
            result = 10 * result + (c - '0');
        }
    }
    return pos * result;
}

    @Test
    public void test01() {
        String[] s1 = {"2", "1", "+", "3", "*"}; // 9
        System.out.println(new T150_逆波兰表达式求值().evalRPN(s1));

        String[] s2 = {"4", "13", "5", "/", "+"}; // 6
        System.out.println(new T150_逆波兰表达式求值().evalRPN(s2));

        String[] s3 = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}; // 22
        System.out.println(new T150_逆波兰表达式求值().evalRPN(s3));
    }

    @Test
    public void test02() {
        String[] s1 = {"2", "1", "+", "3", "*"}; // 9
        System.out.println(new T150_逆波兰表达式求值().evalRPN2(s1));

        String[] s2 = {"4", "13", "5", "/", "+"}; // 6
        System.out.println(new T150_逆波兰表达式求值().evalRPN2(s2));

        String[] s3 = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}; // 22
        System.out.println(new T150_逆波兰表达式求值().evalRPN2(s3));

        System.out.println(calc("-123"));
    }

    // 测试 switch...case 匹配
    @Test
    public void test03() {
        String x = new String("+");
        switch (x) {
            case "+":
                System.out.println(x);break;
            default:
                System.out.println("未匹配");
        }

        char c = '+';
        switch (c) {
            case '+':
                System.out.println(x);break;
            default:
                System.out.println("未匹配");
        }

        if (x == "+") {
            System.out.println(x);
        } else {
            System.out.println("未匹配");
        }

        if (c == '+') {
            System.out.println(c);
        } else {
            System.out.println("未匹配");
        }
    }

    // 测试数组
    @Test
    public void test04() {
        int[] nums = {1,2,5,0,0,0,0,0,0,0};
        int pos = 2;
        // nums[--pos] = nums[pos-1] + nums[pos];
        // 上句要先计算 pos = pos - 1，然后计算 nums[pos] = nums[pos-1] + nums[pos];
        pos = pos - 1;
        nums[pos] = nums[pos-1] + nums[pos];

        System.out.println(Arrays.toString(nums));
    }
}
