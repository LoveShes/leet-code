package alogorithm;

import org.junit.jupiter.api.Test;

import java.util.Stack;

/**
 * [!重点复习]
 * https://leetcode-cn.com/problems/trapping-rain-water/
 */
public class T42_接雨水 {
    /**
     * 【暴力】
     * 都没独立做出来，只考虑了左低右高的情况，忽略了左高右低的情况
     * 具体思路为：计算每根柱子的储水量（为左右柱子两侧最大高度者的较小值减去此柱子的高度），然后统计总和
     * 剩下的解法都是以暴力为基础的
     * <p>
     * 执行用时 :99 ms, 在所有 Java 提交中击败了7.64% 的用户
     * 内存消耗 :39.5 MB, 在所有 Java 提交中击败了11.78%的用户
     */
    public int trap(int[] height) {
        if (height == null) return 0;
        int ans = 0;
        // 遍历每根柱子，边界2根柱子是不能储水的
        for (int i = 1; i < height.length - 1; i++) {
            int leftMax = 0, rightMax = 0;
            // 计算当前柱子左边柱子的最大高度
            // 注意，要加上当前柱子，万一当前柱子最高，则该柱子是不能储水的
            for (int j = 0; j <= i; j++) {
                leftMax = Math.max(leftMax, height[j]);
            }
            // 计算当前柱子右边柱子的最大高度
            for (int j = i; j < height.length; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }
            // 计算当前柱子的储水量
            ans += Math.min(leftMax, rightMax) - height[i];
        }
        return ans;
    }

    /**
     * 【动态规划】
     * 按上面的思路进行优化，记忆化
     * 执行用时 :2 ms, 在所有 Java 提交中击败了45.29% 的用户
     * 内存消耗 :39.3 MB, 在所有 Java 提交中击败了11.78%的用户
     */
    public int trap2(int[] height) {
        if (height == null || height.length == 0) return 0;
        int n = height.length;
        // 定义dp
        // dp[i][0] 表示下标i的柱子左边的最大值
        // dp[i][1] 表示下标i的柱子右边的最大值
        int[][] dp = new int[n][2];
        dp[0][0] = height[0]; // 最左、最右都储不了水
        dp[n - 1][1] = height[n - 1];
        // 先算出所有柱子左边（含自身）的最大值
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(height[i], dp[i - 1][0]);
        }
        // 再算出所有柱子右边（含自身）的最大值
        for (int i = n - 2; i >= 0; i--) {
            dp[i][1] = Math.max(height[i], dp[i + 1][1]);
        }
        // 统计数目
        int ans = 0;
        for (int i = 1; i < n - 1; i++) {
            ans += Math.min(dp[i][0], dp[i][1]) - height[i];
        }
        return ans;
    }

    /**
     * 【推荐-双指针】
     * 优化dp数组，边算左右最大值的时候边求出当前柱子的储水量
     * 执行用时 :1 ms, 在所有 Java 提交中击败了99.98% 的用户
     * 内存消耗 :39.3 MB, 在所有 Java 提交中击败了11.78%的用户
     */
    public int trap3(int[] height) {
        if (height == null || height.length == 0) return 0;
        int ans = 0;
        int leftMax = 0, rightMax = 0, left = 0, right = height.length - 1;
        while (left <= right) {
            // 因为总是选小的那边柱子进行计算
            // 所以只要leftMax只要小于右边随便一个值（哪怕不是最大的）就会取leftMax进行计算
            if (leftMax <= rightMax) {
                leftMax = Math.max(leftMax, height[left]);
                ans += leftMax - height[left++];
            } else {
                rightMax = Math.max(rightMax, height[right]);
                ans += rightMax - height[right--];
            }
        }
        return ans;
    }

    /**
     * 【单调栈】
     * 一层一层计算，遇到低于栈顶的就入栈，遇到高于等于的就出栈
     * 执行用时 :4 ms, 在所有 Java 提交中击败了29.81% 的用户
     * 内存消耗 :39.3 MB, 在所有 Java 提交中击败了12.03%的用户
     */
    public int trap4(int[] height) {
        if (height == null || height.length == 0) return 0;
        // 单调栈，存凹槽的下标
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        // 遍历每个柱体
        for (int i = 0; i < height.length; i++) {
            // height[stack.peek()] < height[i]可带等号也可不带
            // 不带等号相同元素就会放入栈中，等遍历元素大于栈顶元素才会进行计算
            // 带等号则不会把相同元素放入栈中，会进如内部的处理（pop掉原来的）
            while (!stack.isEmpty() && height[stack.peek()] <= height[i]) {
                int bottomIdx = stack.pop();
                // 保持栈的严格递减性，全部相等只留一个
                while (!stack.isEmpty() && height[stack.peek()] == height[bottomIdx]) {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    ans += (Math.min(height[stack.peek()], height[i]) - height[bottomIdx]) * (i - stack.peek() - 1);
                }
            }
            stack.push(i);
        }
        return ans;
    }

    @Test
    public void test() {
        int[][] height = {
                {4, 3, 1, 0, 1, 2, 4},
                {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1},
                {0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0},
                {1, 2, 3, 0, 1, 3, 1, 5, 10, 1, 0, 0, 0, 5, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        int[] ans = {13, 6, 0, 26, 0};
        testTrap1(height, ans);
        testTrap2(height, ans);
        testTrap3(height, ans);
        testTrap4(height, ans);
    }

    public void testTrap1(int[][] height, int[] ans) {
        for (int i = 0; i < height.length; i++) {
            int get = trap(height[i]);
            assert get == ans[i] : get;
        }
    }

    public void testTrap2(int[][] height, int[] ans) {
        for (int i = 0; i < height.length; i++) {
            int get = trap2(height[i]);
            assert get == ans[i] : get;
        }
    }

    public void testTrap3(int[][] height, int[] ans) {
        for (int i = 0; i < height.length; i++) {
            int get = trap3(height[i]);
            assert get == ans[i] : get;
        }
    }

    public void testTrap4(int[][] height, int[] ans) {
        for (int i = 0; i < height.length; i++) {
            int get = trap4(height[i]);
            assert get == ans[i] : get;
        }
    }
}
