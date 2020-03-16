package offer;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.junit.jupiter.api.Test;

public class T53III_数组中数值和下标相等的元素 {
    /**
     * 【线性查找】
     */
    public int equalNumber(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == i) return i;
            i++;
        }
        return -1;
    }

    /**
     * 【二分查找】
     */
    public int equalNumber2(int[] nums) {
        int begin = 0;
        int end = nums.length;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (nums[mid] > mid) {
                end = mid;
            } else if (nums[mid] < mid) {
                begin = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    @Test
    public void test() {
        assert equalNumber(new int[]{-3, -1, 1, 3, 5}) == 3;
        assert equalNumber(new int[]{-3, -1, 1, 2, 5}) == -1;

        assert equalNumber2(new int[]{-3, -1, 1, 3, 5}) == 3;
        assert equalNumber2(new int[]{-3, -1, 1, 2, 5}) == -1;
    }
}
