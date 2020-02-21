package offer;

import org.junit.jupiter.api.Test;

public class T03_数组中重复的数字 {
    public int findRepeatNumber(int[] nums) {
        return 0;
    }

    @Test
    public void test() {
        int[] nums = {2, 3, 1, 0, 2, 5, 3};
        int result = findRepeatNumber(nums);
        assert result == 2 || result == 3;
    }
}
