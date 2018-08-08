package leetcode.numDistinct;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;

class Solution {
    public int numDistinct(String s, String t) {
        return 0;
    }

    @Test
    public void test() {
        Solution solution = new Solution();
        Assertions.assertEquals(solution.numDistinct("rabbbit", "rabbit"), 3);
        Assertions.assertEquals(solution.numDistinct("babgbag", "bag"), 5);
        Assertions.assertEquals(solution.numDistinct("", ""), 1);
    }
}