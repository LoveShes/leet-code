package math;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/add-binary/
 */
public class T67_二进制求和 {
    /**
     * 【使用StringBuilder】
     * 执行用时 :2 ms, 在所有 Java 提交中击败了96.85% 的用户
     * 内存消耗 :38.2 MB, 在所有 Java 提交中击败了5.09%的用户
     */
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int ca = 0; // 进位
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int sum = ca;
            if (i >= 0) {
                sum += a.charAt(i) - '0';
            }
            if (j >= 0) {
                sum += b.charAt(j) - '0';
            }
            sb.append(sum & 1);
            ca = sum >> 1;
        }
        sb.reverse();
        if (ca == 1) { // 有进位
            return "1" + sb.toString();
        }
        return sb.toString();
    }

    @Test
    public void test() {
        System.out.println(addBinary("11", "1")); // "100"
        System.out.println(addBinary("1010", "1011")); // "10101"
        System.out.println(addBinary("0", "0")); // "0"
    }
}
