package alogorithm;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/assign-cookies/
 */
public class T455_分发饼干 {

    public int findContentChildren(int[] g, int[] s) {
        int count = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        int cookieIndex = s.length - 1;
        for (int i = g.length - 1; i >= 0 && cookieIndex >= 0; i--) {
            if (s[cookieIndex] >= g[i]) {
                count++;
                cookieIndex--;
            }
        }
        return count;
    }

    // 使用堆排序（优先队列）【还不如上面的方法】
    public int findContentChildren2(int[] g, int[] s) {
        int count = 0;
        List<Integer> child = new ArrayList<>(g.length);
        List<Integer> cookie = new ArrayList<>(s.length);
        for (int i = 0; i < g.length; i++) {
            child.add(g[i]);
        }
        for (int i = 0; i < s.length; i++) {
            cookie.add(s[i]);
        }

        PriorityQueue<Integer> childQueue = new PriorityQueue<>(child);
        PriorityQueue<Integer> cookieQueue = new PriorityQueue<>(cookie);

        while (!childQueue.isEmpty() && !cookieQueue.isEmpty()) {
            if (childQueue.peek() <= cookieQueue.remove()) {
                count++;
                childQueue.remove();
            }
        }
        return count;
    }

    @Test
    public void test() {
        int[] g = new int[]{10, 9, 8, 7, 1, 3, 4};
        int[] s = new int[]{5, 6, 7, 8};

        assert findContentChildren(g, s) == findContentChildren2(g, s);
        System.out.println(findContentChildren2(g, s));
        // System.out.println(findContentChildren2(new int[]{1, 2}, new int[]{1, 2, 3}));
    }
}
