package leetcode.stoneGame;

class Solution {
    public boolean stoneGame(int[] piles) {
        int start = 0, end = piles.length - 1;
        int totalLi = 0;
        int totalOther = 0;
        while (start <= end) {
            if (piles[start] >= piles[end]) {
                totalLi += piles[start++];
            } else {
                totalLi += piles[end--];
            }
            if (start <= end) {
                if (piles[start] >= piles[end]) {
                    totalOther += piles[start++];
                } else {
                    totalOther += piles[end--];
                }
            }
        }
        return totalLi > totalOther;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().stoneGame(new int[]{3, 7, 2, 3}));
    }
}