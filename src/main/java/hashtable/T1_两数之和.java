package hashtable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/two-sum/
 */
public class T1_两数之和 {

    // 暴力法
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    break;
                }
            }
        }
        return result;
    }

    // 哈希表法
    public int[] twoSum2(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        map.put(nums[0], 0);
        for (int i = 1; i < nums.length; i++) {
            map.put(nums[i], i);
            if (map.containsKey(target - nums[i])) {
                result[0] = i;
                result[1] = map.get(target - nums[i]);
                break;
            }
        }
        return result;
    }

    // 针对已排序数组
    public int[] twoSum3(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (nums[start] + nums[end] != target) {
            if (nums[start] + nums[end] < target) {
                ++start;
            }
            if (nums[start] + nums[end] > target) {
                --end;
            }
            if (start == end) {
                return new int[]{-1,-1};
            }
        }
        return new int[]{start, end};
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1, 3, 4, 7, 9, 13, 15, 17};
        int target = 19;
        int[] result = new T1_两数之和().twoSum3(nums, target);
        System.out.println(Arrays.toString(result));
    }
}
