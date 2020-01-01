package stack;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/valid-parentheses/
 */
public class T20_有效的括号 {

    // 左括号入栈，右边匹配
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        char c;
        char left;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else { // 右括号
                if (stack.isEmpty()) { // 注意，如果stack为空的话，直接pop会报错
                    return false;
                }
                left = stack.pop();
                if (left=='(' && c!=')' || left=='[' && c!=']' || left=='{' && c!='}' ) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    // 使用map进行配对
    public boolean isValid2(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');

        int len = s.length();
        Stack<Character> stack = new Stack<>();
        char c;
        for (int i = 0; i < len; i++) {
            c = s.charAt(i);
            if (map.containsKey(c)) {
                stack.push(c);
            } else { // 右括号
                if (stack.isEmpty()) { // 注意，如果stack为空的话，直接pop会报错
                    return false;
                }
                if (c != map.get(stack.pop())) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }


    // 方法2改进，使用静态成员变量提升效率
    private static Map<Character, Character> map = new HashMap<>();
    static {
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
    }
    public boolean isValid2_2(String s) {
        int len = s.length();
        Stack<Character> stack = new Stack<>();
        char c;
        for (int i = 0; i < len; i++) {
            c = s.charAt(i);
            if (map.containsKey(c)) {
                stack.push(c);
            } else { // 右括号
                if (stack.isEmpty()) { // 注意，如果stack为空的话，直接pop会报错
                    return false;
                }
                if (c != map.get(stack.pop())) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }


    // 【推荐】遇到左括号，提前将对应右括号入栈，看下一个遍历的字符是否对应
    public boolean isValid3(String s) {
        Stack<Character> stack = new Stack<>();
        stack.push('?'); // 先加入一个哑元素，避免s以右括号开头导致空栈pop报错
        char[] chars = s.toCharArray(); // 使用 chars.length 比 s.length()效率高
        for (char c : chars) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else if (c != stack.pop()){
                return false;
            }
        }
        return stack.pop() == '?';
    }

    // 简单，但是效率较低
    public boolean isValid4(String s) {
        while (s.contains("()") || s.contains("[]") || s.contains("{}")) {
            s = s.replace("()", "");
            s = s.replace("[]", "");
            s = s.replace("{}", "");
        }
        return s.isEmpty();
    }

    @Test
    public void test01() {
        String s = "{()[]}[]{[]}";
        System.out.println(isValid(s));
    }

    @Test
    public void test02() {
        String s = "{()[{]}[]{[]}[";
        System.out.println(isValid2(s));
        System.out.println(isValid2_2(s));
    }

    @Test
    public void test03() {
        String s = "])";
        System.out.println(isValid3(s));
    }

    @Test
    public void test04() {
        String s = "{()[]}[";
        System.out.println(isValid4(s));
    }
}
