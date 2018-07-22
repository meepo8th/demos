package leetcode.recordPowOf2;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public boolean reorderedPowerOf2(int N) {
        int[] allPows = new int[32];
        for (int i = 0; i < 32; i++) {
            allPows[i] = 1 << i;
        }
        for (int pow : allPows) {
            if (N == pow || canTranslate(String.valueOf(pow), String.valueOf(N))) {
                return true;
            }
        }
        return false;
    }

    private boolean canTranslate(String s, String s1) {
        char[] char1 = new char['9' - '0' + 1];
        char[] char2 = new char['9' - '0' + 1];
        if (s.length() == s1.length()) {
            for (char chr : s.toCharArray()) {
                char1[chr - '0'] += 1;
            }
            for (char chr : s1.toCharArray()) {
                char2[chr - '0'] += 1;
            }
            for (int i = 0; i < '9' - '0'; i++) {
                if (char1[i] != char2[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().reorderedPowerOf2(123456789));
    }
}