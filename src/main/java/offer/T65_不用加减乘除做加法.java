package offer;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof/
 */
public class T65_不用加减乘除做加法 {
    /**
     * 【位运算】
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :35.9 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int add(int a, int b) {
        int sum, carry;
        while (b != 0) {
            sum = a ^ b;
            carry = (a & b) << 1; // 进位是慢慢加上去的，加上去之后可能还需要进位
            a = sum;
            b = carry;
        }
        return a;
    }

    @Test
    public void test() {
        System.out.println(add(1, 2));
        System.out.println(add(1, 0));
        System.out.println(add(-3, 5));
        System.out.println(add(3, -5));
    }
}
