package offer;

import org.junit.jupiter.api.Test;
import tools.Times;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof/
 */
public class T45_把数组排成最小的数 {
    /**
     * 【排序法】
     * 执行用时 :13 ms, 在所有 Java 提交中击败了30.39% 的用户
     * 内存消耗 :41.6 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public String minNumber(int[] nums) {
        Integer[] array = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        Arrays.sort(array, (num1, num2) -> {
            String s1 = "" + num1 + num2;
            String s2 = "" + num2 + num1;
            return s1.compareTo(s2);
        });
        StringBuilder sb = new StringBuilder();
        for (Integer integer : array) {
            sb.append(integer);
        }
        return sb.toString();
    }

    /**
     * 【还不如用List】
     * 执行用时 :9 ms, 在所有 Java 提交中击败了64.80% 的用户
     * 内存消耗 :39.3 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public String minNumber2(int[] nums) {
        List<String> list = new ArrayList<>(nums.length);
        for (int num : nums) {
            list.add(String.valueOf(num));
        }
        list.sort((o1, o2) -> (o1 + o2).compareTo(o2 + o1));
        return String.join("", list);
    }

    /**
     * 【把list改成数组】
     * 执行用时 :7 ms, 在所有 Java 提交中击败了94.19% 的用户
     * 内存消耗 :39.4 MB, 在所有 Java 提交中击败了100.00%的用户
     */
    public String minNumber3(int[] nums) {
        String[] strings = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strings[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strings, (o1, o2) -> (o1 + o2).compareTo(o2 + o1));
        // StringBuilder和join速度差不多
        StringBuilder sb = new StringBuilder();
        for (String string : strings) {
            sb.append(string);
        }
        return sb.toString();
        // return String.join("", strings);
    }


    @Test
    public void test() {
        assert minNumber(new int[]{10, 2}).equals("102") : minNumber(new int[]{10, 2});
        assert minNumber(new int[]{3, 30, 34, 5, 9}).equals("3033459") : minNumber(new int[]{3, 30, 34, 5, 9});

        assert minNumber2(new int[]{10, 2}).equals("102") : minNumber2(new int[]{10, 2});
        assert minNumber2(new int[]{3, 30, 34, 5, 9}).equals("3033459") : minNumber2(new int[]{3, 30, 34, 5, 9});

        assert minNumber3(new int[]{10, 2}).equals("102") : minNumber2(new int[]{10, 2});
        assert minNumber3(new int[]{3, 30, 34, 5, 9}).equals("3033459") : minNumber2(new int[]{3, 30, 34, 5, 9});
    }

    @Test
    public void testSpeed() {
        int times = 1_0000;
        String[] strings = new String[10000];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = String.valueOf(i);
        }
        Times.test("StringBuilder", () -> {
            for (int i = 0; i < times; i++) {
                StringBuilder sb = new StringBuilder();
                for (String string : strings) {
                    sb.append(",").append(string);
                }
                // sb.toString(); // 加上toString()之后反而会变很慢
            }
        });
        Times.test("join", () -> {
            for (int i = 0; i < times; i++) {
                String.join(",", strings);
            }
        });

    }
}
