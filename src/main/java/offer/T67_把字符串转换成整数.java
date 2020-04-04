package offer;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/ba-zi-fu-chuan-zhuan-huan-cheng-zheng-shu-lcof/
 */
public class T67_把字符串转换成整数 {
    /**
     * 执行用时 :3 ms, 在所有 Java 提交中击败了49.13% 的用户
     * 内存消耗 :38.6 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int strToInt(String str) {
        if (str == null || str.length() == 0) return 0;
        char[] array = str.toCharArray();
        int index = 0;
        int sign = 1;
        long ans = 0;
        // 去除前面的空格
        while (index < array.length && array[index] == ' ') {
            index++;
        }
        if (index >= array.length) return (int) ans;
        // 到这里array[index]不为空
        if (array[index] == '-') {
            sign = -1;
            index++;
        } else if (array[index] == '+') {
            index++;
        }
        // 到这里应该为纯数字
        for (int i = index; i < array.length; i++) {
            // 一旦探测到非数字就退出
            if (array[i] < '0' || array[i] > '9') return sign * (int) ans;
            long now = 10 * ans + (array[i] - '0'); // 应该是越来越大的
            // 判断是否溢出
            if (sign == 1 && now >= Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if (sign == -1 && now > Integer.MAX_VALUE) return Integer.MIN_VALUE;
            ans = now;
        }
        return sign * (int) ans;
    }

    /**
     * 【判断是否溢出的另一种写法】
     * 因为 MAX_VALUE=0x7fffffff，所以当上一次计算的ans大于MAX_VALUE/10，
     * 或者等于MAX_VALUE/10且当前数字大于7，说明超范围了
     * <p>
     * 执行用时 :2 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :38.7 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int strToInt2(String str) {
        if (str == null || str.length() == 0) return 0;
        char[] array = str.toCharArray();
        int index = 0;
        int sign = 1;
        int ans = 0;
        // 去除前面的空格
        while (index < array.length && array[index] == ' ') {
            index++;
        }
        if (index >= array.length) return ans;
        
        // 到这里array[index]不为空
        if (array[index] == '-') {
            sign = -1;
            index++;
        } else if (array[index] == '+') {
            index++;
        }
        // 到这里应该为纯数字
        for (int i = index; i < array.length; i++) {
            // 一旦探测到非数字就退出
            if (array[i] < '0' || array[i] > '9') return sign * ans;
            int num = array[i] - '0';
            // 判断是否溢出
            if (ans > Integer.MAX_VALUE / 10 || ans == Integer.MAX_VALUE / 10 && num > 7) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            ans = 10 * ans + num; // 应该是越来越大的
        }
        return sign * ans;
    }

    @Test
    public void test() {
        System.out.println(strToInt("-")); // 0
        System.out.println(strToInt("42")); // 42
        System.out.println(strToInt("  -42")); // -42
        System.out.println(strToInt("4193 with words")); // 4193
        System.out.println(strToInt("words and 987")); // 0
        System.out.println(strToInt("-91283472332")); // -2147483648
        // 之前没注意到的情况
        System.out.println(strToInt(" ")); // 0
        System.out.println(strToInt("-6147483648")); // -2147483648
        System.out.println(strToInt("2147483647")); // 2147483647
        System.out.println(strToInt("2147483648")); // 2147483647
        System.out.println(strToInt("-2147483647")); // -2147483647
        System.out.println(strToInt("-2147483648")); // -2147483648
    }

    @Test
    public void test2() {
        System.out.println(strToInt2("42")); // 42
        System.out.println(strToInt2("  -42")); // -42
        System.out.println(strToInt2("4193 with words")); // 4193
        System.out.println(strToInt2("words and 987")); // 0
        System.out.println(strToInt2("-91283472332")); // -2147483648

        System.out.println(strToInt2(" ")); // 0
        System.out.println(strToInt2("-6147483648")); // -2147483648
        System.out.println(strToInt2("2147483647")); // 2147483647
        System.out.println(strToInt2("2147483648")); // 2147483647（被当做超范围处理了，取最大值）
        System.out.println(strToInt2("-2147483647")); // -2147483647
        System.out.println(strToInt2("-2147483648")); // -2147483648（被当做超范围处理了，取最小值）（实际上并没有超范围）
    }
}
