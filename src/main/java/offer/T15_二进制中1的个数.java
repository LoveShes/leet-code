package offer;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/
 */
public class T15_二进制中1的个数 {
    /**
     * 【无符号右移】
     * 执行用时 :1 ms, 在所有 Java 提交中击败了98.91% 的用户
     * 内存消耗 :36.4 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            count += n & 1;
            n >>>= 1;  // 无符号右移的目的是清零，用于退出循环
        }
        return count;
    }

    /**
     * 【优化】
     * 有几个1就只用进行几次循环
     * 执行用时 :1 ms, 在所有 Java 提交中击败了98.91% 的用户
     * 内存消耗 :36.1 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int hammingWeight2(int n) {
        int count = 0;
        while (n != 0) {
            n &= (n - 1); // 通过与运算来实现置0
            ++count;
        }
        return count;
    }

    @Test
    public void test() {
        assert hammingWeight2(0b00000000000000000000000000001011) == 3;
        assert hammingWeight2(0b00000000000000000000000010000000) == 1;
        assert hammingWeight2(0b11111111111111111111111111111101) == 31;
    }
}

class 判断是否为幂次 {
    /**
     * 是否为2的幂次
     */
    public boolean is2Power(int n) {
        return (n & (n - 1)) == 0;
    }

    /**
     * 是否为4的幂次
     */
    public boolean is4Power(int n) {
        // 先判断是否为2的幂次
        return (n & (n - 1)) == 0 && (n & 0xaaaaaaaa) == 0;
    }

    @Test
    public void test() {
        assert !is2Power(-2);
        assert is2Power(1);
        assert is2Power(2);
        assert is2Power(4);
        assert !is2Power(5);
        assert !is2Power(6);
        assert is2Power(8);
    }

    @Test
    public void test2() {
        assert !is4Power(-2);
        assert is4Power(1);
        assert !is4Power(2);
        assert is4Power(4);
        assert !is4Power(5);
        assert !is4Power(8);
        assert is4Power(16);
    }
}
