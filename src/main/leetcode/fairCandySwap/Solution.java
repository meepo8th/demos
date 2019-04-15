package leetcode.fairCandySwap;

import java.util.Arrays;

class Solution {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().fairCandySwap(new int[]{1, 1}, new int[]{2, 2})));
        System.out.println(Arrays.toString(new Solution().fairCandySwap(new int[]{1, 2}, new int[]{2, 3})));
        System.out.println(Arrays.toString(new Solution().fairCandySwap(new int[]{2}, new int[]{1, 3})));
        System.out.println(Arrays.toString(new Solution().fairCandySwap(new int[]{1, 2, 5}, new int[]{2, 4})));
    }

    public int[] fairCandySwap(int[] A, int[] B) {
        int sumA = 0;
        int sumB = 0;
        for (int a : A) {
            sumA += a;
        }
        for (int b : B) {
            sumB += b;
        }
        char max = sumA > sumB ? 'A' : 'B';
        int[] maxA;
        int[] minA;
        if ('A' == max) {
            maxA = A;
            minA = B;
        } else {
            maxA = B;
            minA = A;
        }
        int value = Math.abs(sumA - sumB) / 2;
        int iA = 0;
        int jA = 0;
        for (int i = 0; i < maxA.length; i++) {
            if (maxA[i] > value) {
                for (int j = 0; j < minA.length; j++) {
                    if (maxA[i] - minA[j] == value) {
                        iA = maxA[i];
                        jA = minA[j];
                        break;
                    }
                }
            }
        }
        return 'A' == max ? new int[]{iA, jA} : new int[]{jA, iA};
    }
}