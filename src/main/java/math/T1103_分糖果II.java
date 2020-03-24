package math;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/distribute-candies-to-people/
 */
public class T1103_分糖果II {
    /**
     * 【普通循环】
     * 执行用时 :2 ms, 在所有 Java 提交中击败了44.07% 的用户
     * 内存消耗 :37.2 MB, 在所有 Java 提交中击败了5.27%的用户
     */
    public int[] distributeCandies(int candies, int num_people) {
        int[] res = new int[num_people];
        int index = 0;
        while (candies > 0) {
            int num = index + 1;
            num = Math.min(num, candies);
            res[(index++) % num_people] += num;
            candies -= num;
        }
        return res;
    }

    /**
     * 【找数学规律】
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :36.8 MB, 在所有 Java 提交中击败了5.27%的用户
     */
    public int[] distributeCandies2(int candies, int num_people) {
        /*
         * 假设完整的发了p次，最后一次剩余的糖果数为r，则有 糖果数C = p(1+p)/2 + r
         * 由于有 0 <= r < p+1，推出 0 <= C - p(1+p)/2 < p+1
         * 故有不等式方程组
         * ① p^2 + p - 2C <= 0
         * ② p^2 + 3p + 2-2C
         * 又由p>0，可得 (sqrt(1+8C)-1)/2 - 1 < p <= (sqrt(1+8C)-1)/2
         * 可得，p = floor((sqrt(1+8C)-1)/2)，r = C - p(1+p)/2
         *
         * 由于发了p轮完整的，则对n个小朋友均发了 rows = p/n 轮，最后一轮不完整的发了 cols = p%n 个小朋友
         * 对于第i个小朋友而言（i从0开始计数），糖果数由2部分组成
         * ① 完整的一轮中，res[i] = (i+1) + (i+1+n) + (i+1+2n) + ... + (i+1+(rows-1)n) = (i+1)*rows + n*rows(rows-1)/2
         * ② 不完整的一轮中，res[i] = i+1 + rows*n
         * 对于最后剩余的r，则分配个第cols+1个小朋友，即res[(cols+1)-1] = r;
         */
        int[] res = new int[num_people];
        int p = (int) (Math.sqrt(0.25 + 2 * candies) - 0.5); // 这里注意要防止candies过大导致溢出
        int r = candies - ((p * (p + 1)) >> 1); // 移位运算一定要括起来（优先级低）
        int rows = p / num_people;
        int cols = p % num_people;
        for (int i = 0; i < num_people; i++) {
            res[i] = (i + 1) * rows + num_people * rows * (rows - 1) / 2;
            if (i < cols) {
                res[i] += i + 1 + rows * num_people;
            }
        }
        res[cols] += r;
        return res;
    }

    @Test
    public void test() {
        System.out.println(Arrays.toString(distributeCandies(1, 2))); // [1,0]
        System.out.println(Arrays.toString(distributeCandies2(1, 2))); // [1,0]
        System.out.println(Arrays.toString(distributeCandies(2, 1))); // [2]
        System.out.println(Arrays.toString(distributeCandies2(2, 1))); // [2]
        System.out.println(Arrays.toString(distributeCandies(7, 4))); // [1,2,3,1]
        System.out.println(Arrays.toString(distributeCandies2(7, 4))); // [1,2,3,1]
        System.out.println(Arrays.toString(distributeCandies(10, 3))); // [5,2,3]
        System.out.println(Arrays.toString(distributeCandies2(10, 3))); // [5,2,3]
        System.out.println(Arrays.toString(distributeCandies(1000000000, 1000))); //
        System.out.println(Arrays.toString(distributeCandies2(1000000000, 1000))); //
    }
}
