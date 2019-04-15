package leetcode.singlenumberII;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整型数组，除了一个元素只出现一次外，其余每个元素都出现了三次。求出那个只出现一次的数。
 */
class Solution {

    public static void main(String[] args) {
        System.out.println(1 ^ 5);
    }

    public int singleNumber(int[] nums) {
        Map<Integer, Integer> cache = new HashMap<>();
        for (Integer i : nums) {
            if (!cache.containsKey(i)) {
                cache.put(i, 0);
            } else {
                cache.put(i, cache.get(i) + 1);
                if (cache.get(i) == 2) {
                    cache.remove(i);
                }
            }
        }
        return (int) cache.keySet().toArray()[0];
    }

    public int singleNumberNoCache(int[] nums) {
        int rtn = 0;
        for (int i : nums) {
            rtn ^= i;
        }
        return rtn;
    }
}