package leetcode.reorderedPowerOf2;

import java.util.Arrays;

class Solution {
    static char[][] allPowerOf2 = new char[32][];

    static {
        allPowerOf2[0] = String.valueOf(1).toCharArray();
        for (int i = 1; i < 31; i++) {
            allPowerOf2[i] = String.valueOf(2 << i).toCharArray();
            Arrays.sort(allPowerOf2[i]);
        }
    }

    public boolean reorderedPowerOf2(int N) {
        char[] ns = String.valueOf(N).toCharArray();
        Arrays.sort(ns);
        for (char[] powerOf2 : allPowerOf2) {
            if (Arrays.equals(ns, powerOf2)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().reorderedPowerOf2(16));
    }
}