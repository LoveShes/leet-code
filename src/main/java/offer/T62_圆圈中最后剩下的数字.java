package offer;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/
 */
public class T62_圆圈中最后剩下的数字 {
    /**
     * 【超出时间限制】
     */
    public int lastRemaining(int n, int m) {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int index = (m - 1) % list.size();
        while (list.size() >= 2) {
            list.remove(index);
            // 题目删掉之后是从下一个开始数的
            index = (index + m - 1) % list.size();
        }
        return list.get(0);
    }

    /**
     * 【数学方法】
     * f(n,m)=(f(n−1,m)+m)%n
     * 表示的是n个人报数，每报到m时杀掉那个人，最终胜利者的编号，模上n是为了避免数组越界
     * 核心在于关注胜利者的下标位置是怎么变的。每杀掉一个人，其实就是把这个数组向前移动了m位。然后逆过来（向后移动m位），就可以得到这个递推式。
     * https://blog.csdn.net/u011500062/article/details/72855826
     * 执行用时 :7 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :36.6 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public int lastRemaining2(int n, int m) {
        int last = 0;
        for (int i = 2; i <= n; i++) {
            last = (last + m) % i;
        }
        return last;
    }

    @Test
    public void test() {
        System.out.println(lastRemaining(5, 3)); // 3
        System.out.println(lastRemaining(10, 17)); // 2

        System.out.println(lastRemaining2(5, 3)); // 3
        System.out.println(lastRemaining2(10, 17)); // 2
        System.out.println(lastRemaining2(10, 1)); // 9
        System.out.println(lastRemaining2(1, 1)); // 0
    }
}
