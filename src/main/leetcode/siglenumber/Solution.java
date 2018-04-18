package leetcode.siglenumber;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个整型数组，除了一个元素只出现一次外，其余每个元素都出现了二次。求出那个只出现一次的数。
 */
class Solution {
    public int singleNumber(int[] nums) {
        int rtn=0;
        for (int i : nums) {
            rtn^=i;
        }
        return rtn;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().singleNumber(new int[]{1001, 2, 2, 3, 3, 111, 111}));
    }
}