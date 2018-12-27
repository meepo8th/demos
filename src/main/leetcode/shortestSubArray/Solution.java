package leetcode.shortestSubArray;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class Solution {
    public static int shortestSubarray(int[] A, int K) {
        return 0;
    }

    @Test
    void shortestSubarrayTest() {
        Assert.assertEquals(shortestSubarray(new int[]{1}, 1), 1);
        Assert.assertEquals(shortestSubarray(new int[]{1, 2}, 4), -1);
        Assert.assertEquals(shortestSubarray(new int[]{2, -1, 2}, 3), 3);
    }
}