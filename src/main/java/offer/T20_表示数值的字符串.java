package offer;

import org.junit.jupiter.api.Test;

/**
 * TODO
 * https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/
 */
public class T20_表示数值的字符串 {
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
            if (index >= right || chars[index] < '0' || chars[index] > '9') return false; // 开头就是'.'，则后面必须要跟数字
            hasDot = true;
        }

        // 一直到'e'为止
        while (index < right) {
            if (chars[index] >= '0' && chars[index] <= '9') {
                index++;
                hasBase = true;
            } else if (!hasDot && chars[index] == '.') { // 找到中间的小数点了
                hasDot = true;
                index++;
            } else if (!hasE && chars[index] == 'e') { // 找到中间的e了
                hasE = true;
                index++;
                break;
            } else {
                return false; // 不合法
            }
        }

        if (!hasBase) return false;
        if (hasE) {
            // e后面要有数值
            if (index < right) {
                if (chars[index] == '+' || chars[index] == '-') {
                    index++;
                    if (index < right) {
                        while (index < right) {
                            if (chars[index] == '.' || chars[index] < '0' || chars[index] > '9') {
                                return false;
                            }
                            index++;
                        }
                    } else {
                        return false;
                    }
                } else {

                }
            } else {
                return false;
            }
        }

        return true;
    }

    @Test
    public void test() {
        // assert isNumber("+100");
        // assert isNumber("-123");
        // assert isNumber("123");
        // assert isNumber("3.1416");
        // assert isNumber("12.");
        // assert isNumber(".12");
        // assert isNumber("0123");
        // assert isNumber("5e2");
        // assert isNumber(" 1 "); // 首尾空格可以忽略
        //
        // assert !isNumber("4e+");
        assert !isNumber("4e.");
        assert !isNumber(".");
        assert !isNumber(" ");
        assert !isNumber("1 1");
        assert !isNumber("e9");
        assert !isNumber(".e12");
        assert !isNumber("-1E-16");
        assert !isNumber("12e");
        assert !isNumber("1a3.14");
        assert !isNumber("1.2.3");
        assert !isNumber("+-5");
        assert !isNumber("12e+5.4");
    }

}
