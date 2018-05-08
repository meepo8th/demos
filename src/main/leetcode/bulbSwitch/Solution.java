package leetcode.bulbSwitch;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int bulbSwitch(int n) {
        int count = 0;
        Map<Integer, Integer> cache = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            int now = 0;
            for (int j = 1; j * j <= i; j++) {
                if (i % j == 0) {
                    if (j * j == i) {
                        now += 1;
                    } else {
                        now += 2;
                    }
                }
            }
            cache.put(i, now);
            if (now % 2 == 1) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        long l1 = System.currentTimeMillis();
        System.out.println(new Solution().bulbSwitch(999999));
        System.out.println(System.currentTimeMillis() - l1);
    }
}