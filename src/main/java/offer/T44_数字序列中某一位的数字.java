package offer;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof/
 */
public class T44_数字序列中某一位的数字 {
    /**
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :36.5 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int findNthDigit(int n) {
        // s = "012345678910111213141516..."
        if (n < 0) return -1;
        int digits = 1; // 位数
        while (true) {
            int numbers = countOfIntegers(digits);
            // 乘法运算要小心溢出
            if (n < (long) numbers * digits) return findNthDigit(n, digits);
            n -= digits * numbers;
            digits++;
        }
    }

    /**
     * 【简化代码】
     * 思路：1*10+2*90+3*900+4*9000
     * 1、先判断它位于几位数，
     * 2、然后判断它位于n位数中的第几个
     * 3、最后找它是该数的第几位
     * <p>
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :36.4 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int findNthDigit2(int n) {
        if (n < 0) return -1;
        if (n < 10) return n;
        int i = 2;
        long bitNum = 90; // 从10开始，每i位数的数字共有90个；
        long sum = 10 + i * bitNum; // 总数字位数
        long lastSum = 10;
        long lastNum = 10; // i位数最开头的数字
        while (sum < n) {
            i++; // 寻找下一个位数
            bitNum *= 10;
            lastSum = sum;
            sum += i * bitNum;
            lastNum *= 10;
        }
        // 到这里就已经找到了n所在的是几位数
        long count = (n - lastSum) / i; // 经过了多少个数字
        long curNum = count + lastNum; // 当前所在的那个数字
        int rest = (int) (n - lastSum) % i; // 在当前数字中的位置
        return String.valueOf(curNum).charAt(rest) - '0';
    }

    /**
     * 在一个数字中找出对应位置的数字
     *
     * @param index  在该组数字中的位数
     * @param digits 该组数字是几位数
     * @return 数字
     */
    private int findNthDigit(int index, int digits) {
        int number = beginNumber(digits) + index / digits; // 对应的那个数的完整数字
        int indexFormRight = digits - index % digits; // 所求数所在位置的下一位（当为个位数时会重合）
        for (int i = 1; i < indexFormRight; i++) { // 注意是从1开始的
            number /= 10;
        }
        return number % 10;
    }

    /**
     * 统计该位数的数字个数
     *
     * @param digits 位数
     * @return 数字个数
     */
    private int countOfIntegers(int digits) {
        if (digits == 1) return 10;
        return 9 * (int) Math.pow(10, digits - 1);
    }

    /**
     * 获取位数的第一个数字
     *
     * @param digits 位数
     * @return 第一个该位数的数字
     */
    private int beginNumber(int digits) {
        if (digits == 1) return 0;
        return (int) Math.pow(10, digits - 1);
    }

    @Test
    public void test() {
        assert findNthDigit(9) == 9 : findNthDigit(9);
        assert findNthDigit(10) == 1 : findNthDigit(10);
        assert findNthDigit(3) == 3 : findNthDigit(3);
        assert findNthDigit(11) == 0 : findNthDigit(11);
        assert findNthDigit(12) == 1 : findNthDigit(12);
        assert findNthDigit(1000000000) == 1 : findNthDigit(1000000000);
    }

    @Test
    public void test2() {
        assert findNthDigit2(9) == 9 : findNthDigit2(9);
        assert findNthDigit2(10) == 1 : findNthDigit2(10);
        assert findNthDigit2(3) == 3 : findNthDigit2(3);
        assert findNthDigit2(11) == 0 : findNthDigit2(11);
        assert findNthDigit2(12) == 1 : findNthDigit2(12);
        assert findNthDigit2(1000000000) == 1 : findNthDigit2(1000000000);
    }
}
