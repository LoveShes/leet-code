package offer;

import java.util.Arrays;

import org.junit.jupiter.api.Test;


/**
 * https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/
 */
public class T05_替换空格 {
    /**
     * 【字符串拼接】
     * 执行用时 :0 ms, 在所有 Java 提交中击败了100.00% 的用户
     * 内存消耗 :37.6 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public String replaceSpace(String s) {
        if (s == null)
            return "";
        char[] chars = s.toCharArray();
        if (chars.length == 0)
            return "";
        StringBuilder sb = new StringBuilder();
        for (char ch : chars) {
            if (ch == ' ') {
                sb.append("%20");
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    /**
     * 【原地修改】对于C/C++语言而言 这里是从后往前复制 注意，String对象无法修改内容，这里使用StringBuffer来模拟C语言下的算法
     */
    public String replaceSpace2(StringBuffer s) {
        if (s == null)
            return "";
        int len1 = s.length();
        // 弄出足够多的空间
        for (int i = 0; i < len1; i++) {
            if (s.charAt(i) == ' ') {
                s.append("  "); // 2个空格
            }
        }
        int len2 = s.length();
        // 从后往前复制
        while (len1 > 0 && len2 > len1) { // 如果没有空格，则len2不会增加，就没必要执行while循环
            char ch = s.charAt(--len1);
            if (ch == ' ') {
                s.setCharAt(--len2, '0');
                s.setCharAt(--len2, '2');
                s.setCharAt(--len2, '%');
            } else {
                s.setCharAt(--len2, ch);
            }
        }
        return s.toString();
    }

    /**
     * 【相关题目】 有升序数组nums1和升序数组nums2，nums1有足够空间，现要求将nums2中的数插入到nums1中去，并且保持有序
     */
    public int[] merge2Array(int[] nums1, int[] nums2, int len1, int len2) {
        int p1 = len1 - 1;
        int p2 = len2 - 1;
        while (p1 >= 0 && p2 >= 0) {
            if (nums2[p2] > nums1[p1]) {
                nums1[p1 + p2 + 1] = nums2[p2];
                p2--;
            } else {
                nums1[p1 + p2 + 1] = nums1[p1];
                p1--;
            }
        }
        if (p1 < 0) {
            nums1[0] = nums2[0];
        }
        return nums1;
    }

    @Test
    public void test() {
        System.out.println(replaceSpace("We are happy."));
        System.out.println("We are happy.");
        System.out.println(replaceSpace2(new StringBuffer("We are happy.")));
    }

    @Test
    public void test2() {
        int[] nums1 = { 1, 4, 7, 8, 10, 12, 0, 0, 0, 0 };
        int[] nums2 = { -99, 3, 10, 11 };
        System.out.println(Arrays.toString(merge2Array(nums1, nums2, 6, 4)));
    }
}
