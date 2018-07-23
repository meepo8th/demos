package leetcode.minEatingSpeed;

import java.util.Arrays;

class Solution {
    public int minEatingSpeed(int[] piles, int H) {
        int size = piles.length;
        Arrays.sort(piles);

        if (size == H) {

        } else if (size < H) {

        } else if (size >= H) {

        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().minEatingSpeed(new int[]{3, 6, 7, 11}, 8));
        System.out.println(new Solution().minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 5));
        System.out.println(new Solution().minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 6));
        System.out.println(new Solution().minEatingSpeed(new int[]{3, 6, 7, 11}, 8));
    }
}