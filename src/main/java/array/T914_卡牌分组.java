package array;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/x-of-a-kind-in-a-deck-of-cards/
 */
public class T914_卡牌分组 {
    /**
     * 执行用时 :3 ms, 在所有 Java 提交中击败了84.46% 的用户
     * 内存消耗 :41.3 MB, 在所有 Java 提交中击败了5.64%的用户
     */
    public boolean hasGroupsSizeX(int[] deck) {
        if (deck == null || deck.length <= 1) return false;
        int[] hash = new int[10001];
        for (int i = 0; i < deck.length; i++) {
            hash[deck[i]]++;
        }
        // 求一堆数的最大公约数
        int last = 0;
        for (int h : hash) {
            if (h == 0) continue;
            last = gcd(last, h);
            if (last == 1) return false;
        }
        return true;
    }

    // a、b为0则返回对方
    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    @Test
    public void test() {
        assert hasGroupsSizeX(new int[]{1, 2, 3, 4, 4, 3, 2, 1});
        assert !hasGroupsSizeX(new int[]{1, 1, 1, 2, 2, 2, 3, 3});
        assert !hasGroupsSizeX(new int[]{1});
        assert hasGroupsSizeX(new int[]{1, 1});
        assert hasGroupsSizeX(new int[]{1, 1, 2, 2, 2, 2});
        assert hasGroupsSizeX(new int[]{1, 1, 1, 1, 2, 2, 2, 2, 2, 2});
        assert !hasGroupsSizeX(new int[]{1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3});
        assert !hasGroupsSizeX(new int[]{1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3});
    }
}
