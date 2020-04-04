package offer;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/
 */
public class T20_表示数值的字符串 {
    /**
     * 【面向测试用例编程】
     * 反复试了好多遍才找出之前没处理的情况
     * <p>
     * 执行用时 :2 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :38.7 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public boolean isNumber(String s) {
        if (s == null) return false;
        char[] chars = s.toCharArray();
        if (chars.length == 0) return false;

        int index = 0;
        boolean hasDot = false;
        boolean hasE = false;
        boolean hasBase = false;

        // 去除首空格
        while (chars[index] == ' ') {
            index++;
            if (index >= chars.length) return false;
        }
        // 去除尾空格
        int right = chars.length;
        while (chars[right - 1] == ' ') {
            right--;
            if (right < 0) return false;
        }

        // 先看有无正负号
        if (chars[index] == '+' || chars[index] == '-') index++;
        // 再看有无小数点
        if (chars[index] == '.') {
            index++;
            hasDot = true;
            // 开头就是'.'，则后面必须要跟数字
            if (index >= right || chars[index] < '0' || chars[index] > '9') return false;
        }

        // 一直到'e'为止
        while (index < right) {
            if (chars[index] >= '0' && chars[index] <= '9') {
                index++;
                hasBase = true; // e前面有数
            } else if (!hasDot && chars[index] == '.') { // 找到中间的小数点了
                hasDot = true;
                index++;
            } else if (chars[index] == 'e') { // 找到中间的e了
                hasE = true;
                index++;
                break;
            } else {
                return false; // 不合法
            }
        }

        // 有e但是到头了
        if (hasE && index >= right) return false;
        // e的后面必须要接数字
        if (hasE) {
            // e前面要有数值
            if (!hasBase) return false;
            // e后面必须为整数
            if (chars[index] == '+' || chars[index] == '-') {
                index++;
                // '.'后面必须要跟数字
                if (index >= right || chars[index] < '0' || chars[index] > '9') return false;
            }
            // 后面必须为整数
            while (index < right) {
                if (chars[index] < '0' || chars[index] > '9') return false;
                index++;
            }
        }
        return true;
    }

    /**
     * 【简洁代码】
     * 执行用时 :2 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :38.5 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public boolean isNumber2(String s) {
        s = s.trim();
        char[] array = s.toCharArray();

        boolean numberSeen = false;
        boolean numberAfterE = true;
        boolean pointSeen = false;
        boolean eSeen = false;

        for (int i = 0; i < array.length; i++) {
            if (array[i] >= '0' && array[i] <= '9') {
                numberSeen = true;
                numberAfterE = true;
            } else if (array[i] == '.') {
                // 在小数点之前已经有小数点或者e了，无效
                if (pointSeen || eSeen) return false;
                pointSeen = true;
            } else if (array[i] == 'e') {
                // 在e之前发现了e，或者在e之前没发现数字，无效
                if (eSeen || !numberSeen) return false;
                numberAfterE = false; // 因为已经有e了，所以在e之后一定还要有数字才行
                eSeen = true;
            } else if (array[i] == '+' || array[i] == '-') {
                // 在非开头或者e后的符号都是无效的
                if (i != 0 && array[i - 1] != 'e') return false;
            } else {
                return false;
            }
        }
        // 这里numberAfterE很巧妙，没有e的话，一旦有数字numberAfterE就会被设置为true
        // 一旦有了e，numberAfterE就会被设置为false，等到后面再读出数字才会变成true
        return numberSeen && numberAfterE;
    }

    @Test // 纯整数
    public void test1() {
        assert isNumber("123");
        assert isNumber("0123");
        assert isNumber(" 1 "); // 首尾空格可以忽略
        assert !isNumber("1 1"); // 中间空格不可忽略
        assert !isNumber(" ");
    }

    @Test // 带符号整数
    public void test2() {
        assert isNumber("+100");
        assert isNumber("-123");
        assert !isNumber("+-5");
    }

    @Test // 带小数点
    public void test3() {
        assert isNumber("3.1416");
        assert !isNumber("1.2.3");
        assert isNumber("12.");
        assert isNumber(".12");
        assert !isNumber(".");
        assert !isNumber("..");
        assert !isNumber(".1.");
    }

    @Test // 带e，e后面一定且只能为整数
    public void test4() {
        assert !isNumber("4e+");
        assert !isNumber("4e.");
        assert isNumber("1.e1");
        assert isNumber("5e2");
        assert !isNumber("e9");
        assert !isNumber(".e12");
        assert !isNumber("-1E-16");
        assert !isNumber("1a3.14");
        assert !isNumber("12e+5.4");
        assert !isNumber("12e");
    }

    @Test
    public void test5() {
        assert !isNumber2("4e+");
        assert !isNumber2("4e.");
        assert isNumber("1.e1");
        assert isNumber2("5e2");
        assert !isNumber2("e9");
        assert !isNumber2(".e12");
        assert !isNumber2("-1E-16");
        assert !isNumber2("1a3.14");
        assert !isNumber2("12e+5.4");
        assert !isNumber2("12e");
    }
}
