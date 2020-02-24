package offer;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof/
 */
public class T12_矩阵中的路径 {
    /**
     * TODO
     */
    public boolean exist(char[][] board, String word) {
        return false;
    }

    @Test
    public void test() {
        char[][] board = {
                {'a', 'b', 'c', 'e'},
                {'s', 'f', 'c', 's'},
                {'a', 'd', 'e', 'e'}
        };
        String word = "bfce";

        System.out.println(exist(board, word));
    }
}
