package offer;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/
 */
public class T38_字符串的排列 {
    /**
     * 【使用Set】
     * 执行用时 :25 ms, 在所有 Java 提交中击败了72.35% 的用户
     * 内存消耗 :46 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public String[] permutation(String s) {
        Set<String> set = new HashSet<>();
        char[] chars = s.toCharArray();
        permutation(chars, 0, set);
        return set.toArray(new String[0]);
    }

    private void permutation(char[] chars, int index, Set<String> set) {
        if (index >= chars.length) {
            set.add(new String(chars));
            return;
        }
        for (int i = index; i < chars.length; i++) {
            swap(chars, index, i);
            permutation(chars, index + 1, set);
            swap(chars, index, i); // 不换回来，下次要交换的就不是原来的index位置的字母了
        }
    }

    private void swap(char[] chars, int i1, int i2) {
        char temp = chars[i1];
        chars[i1] = chars[i2];
        chars[i2] = temp;
    }

    @Test
    public void test() {
        System.out.println(Arrays.toString(permutation("abc")));
    }
}


class 字符串的组合 {
    /**
     * 【利用二进制特性】
     */
    public List<String> combination(String s) {
        char[] chars = s.toCharArray();
        List<String> result = new ArrayList<>();
        List<List<Integer>> lists = genlists(1 << chars.length);
        for (List<Integer> list : lists) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == 1) {
                    sb.append(chars[i]);
                }
            }
            result.add(sb.toString());
        }
        return result;
    }

    // 产生二进制序列
    private List<List<Integer>> genlists(int num) {
        List<List<Integer>> lists = new ArrayList<>(num);
        for (int i = 1; i < num; i++) {
            List<Integer> list = new ArrayList<>();
            int temp = i;
            while (temp != 0) {
                list.add(temp % 2);
                temp /= 2;
            }
            lists.add(list);
        }
        return lists;
    }

    @Test
    public void test() {
        // System.out.println(genlists(8));
        System.out.println(combination("abc"));
    }
}