package offer;

import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof/
 */
public class T31_栈的压入弹出序列 {
    /**
     * 【辅助栈】
     * 执行用时 :4 ms, 在所有 Java 提交中击败了41.38% 的用户
     * 内存消耗 :40.7 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed == null || popped == null || pushed.length != popped.length) return false;
        Stack<Integer> stack = new Stack<>();
        int index = 0;
        for (int push : pushed) {
            stack.push(push);
            // peek之前得保证栈非空
            while (index < popped.length && !stack.isEmpty() && stack.peek() == popped[index]) {
                stack.pop();
                index++;
            }
        }
        return index == popped.length;
    }

    /**
     * 【利用数组自身模拟栈】
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :40.8 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public boolean validateStackSequences2(int[] pushed, int[] popped) {
        if (pushed == null || popped == null || pushed.length != popped.length) return false;
        int top = 0; // 栈顶
        int index = 0; // 用于指示popped当前位置
        for (int push : pushed) {
            pushed[top++] = push; // 这里top已经自增了，所以栈顶元素是pushed[top-1]
            while (top > 0 && pushed[top - 1] == popped[index]) {
                top--;
                index++;
            }
        }
        return top == 0;
    }

    @Test
    public void test() {
        assert validateStackSequences(new int[]{1, 0}, new int[]{1, 0});
        assert validateStackSequences(new int[]{}, new int[]{});
        assert !validateStackSequences(null, null);
        assert !validateStackSequences(new int[]{}, new int[]{1});
        assert validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 5, 3, 2, 1});
        assert !validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 3, 5, 1, 2});
        assert !validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{6, 3, 5, 1, 2});
    }

    @Test
    public void test2() {
        assert validateStackSequences2(new int[]{1, 0}, new int[]{1, 0});
        assert validateStackSequences2(new int[]{}, new int[]{});
        assert !validateStackSequences2(null, null);
        assert !validateStackSequences2(new int[]{}, new int[]{1});
        assert validateStackSequences2(new int[]{1, 2, 3, 4, 5}, new int[]{4, 5, 3, 2, 1});
        assert !validateStackSequences2(new int[]{1, 2, 3, 4, 5}, new int[]{4, 3, 5, 1, 2});
        assert !validateStackSequences2(new int[]{1, 2, 3, 4, 5}, new int[]{6, 3, 5, 1, 2});
    }

}
