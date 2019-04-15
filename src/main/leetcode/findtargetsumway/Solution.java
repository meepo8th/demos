package leetcode.findtargetsumway;

import java.util.HashMap;
import java.util.Map;

/**
 * 找到目标数，动态规划思想
 */
class Solution {
    public static void main(String[] args) {
        long l1 = System.currentTimeMillis();
        System.out.println(new Solution().findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
        System.out.println(System.currentTimeMillis() - l1);
    }

    public int findTargetSumWays(int[] nums, int S) {
        Map<Integer, Integer>[] cache = new HashMap[nums.length];
        cache[0] = new HashMap<>();
        putValue(cache[0], nums[0], 1);
        putValue(cache[0], nums[0] * -1, 1);
        for (int i = 1; i < nums.length; i++) {
            Map<Integer, Integer> map = cache[i - 1];
            cache[i] = new HashMap<>();
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int num1 = entry.getKey() + nums[i];
                int num2 = entry.getKey() - nums[i];
                putValue(cache[i], num1, entry.getValue());
                putValue(cache[i], num2, entry.getValue());
            }
        }
        return cache[nums.length - 1].containsKey(S) ? cache[nums.length - 1].get(S) : 0;
    }

    private void putValue(Map<Integer, Integer> map, int num, int value) {
        if (!map.containsKey(num)) {
            map.put(num, value);
        } else {
            map.put(num, value + map.get(num));
        }

    }
}