package stack;

import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/score-of-parentheses/
 */
public class T856_括号的分数 {

    // FIXME 个人解法(没想出来，有空再想)
    public int scoreOfParentheses(String S) {
        return 0;
    }

    // 官方解法
    public int scoreOfParentheses2(String S) {
        int score = 0;
        int depth = 0;
        char[] chars = S.toCharArray();
        char before = '?';
        for (char c : chars) {
            if (c == '(') {
                ++depth;
            } else {
                --depth; // 一遇到右括号就减深度
                if (before == '(') { // 只算第1个右括号，避免重复计算
                    score += (1 << depth); // 1 << depth 相当于 2^depth
                }
            }
            before = c;
        }
        return score;
    }

    @Test
    public void test01() {
        System.out.println(new T856_括号的分数().scoreOfParentheses("()()"));            // 2
        System.out.println(new T856_括号的分数().scoreOfParentheses("(()())"));          // 4
        System.out.println(new T856_括号的分数().scoreOfParentheses("((()))()"));        // 6
        System.out.println(new T856_括号的分数().scoreOfParentheses("((())())(())"));    // 8
        System.out.println(new T856_括号的分数().scoreOfParentheses("()()((())(()))"));  // 10
    }

    @Test
    public void test02() {
        String s = "()()((())(()))"; // 10
        System.out.println(new T856_括号的分数().scoreOfParentheses2(s));
    }
}
