package array;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/can-place-flowers/
 */
public class T605_种花问题 {
    // 列表两边设置虚拟值0
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length && n > 0; i++) {
            int leftValue = (i - 1) < 0 ? 0 : flowerbed[i - 1];
            int rightValue = (i + 1) >= flowerbed.length ? 0 : flowerbed[i + 1];
            if (leftValue == 0 && flowerbed[i] == 0 && rightValue == 0) {
                n--;
                i++;
            }
        }
        return n == 0;
    }

    // 优化三目运算符
    public boolean canPlaceFlowers2(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length && n > 0; i++) {
            if ((i == 0 || flowerbed[i - 1] == 0)
                    && flowerbed[i] == 0
                    && ((i == (flowerbed.length - 1) || flowerbed[i + 1] == 0))) {
                n--;
                i++;
            }
        }
        return n == 0;
    }

    @Test
    public void test() {
        // System.out.println(canPlaceFlowers(new int[]{1, 0, 1}, 1));
        // System.out.println(canPlaceFlowers(new int[]{1, 0, 0, 1}, 1));

        assert canPlaceFlowers2(new int[]{0, 0, 1, 0, 0}, 1);
        assert canPlaceFlowers2(new int[]{0}, 1);
        assert canPlaceFlowers2(new int[]{0, 0}, 1);
        assert canPlaceFlowers2(new int[]{0, 0, 1}, 1);
        assert canPlaceFlowers2(new int[]{1, 0, 0, 0, 1}, 1);
        assert !canPlaceFlowers2(new int[]{1, 0, 0, 0, 1}, 2);
        assert canPlaceFlowers2(new int[]{1, 0, 0, 0, 0, 0, 0, 0, 1}, 3);
    }
}
