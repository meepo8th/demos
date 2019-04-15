package leetcode.maxdist2closest;

class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().maxDistToClosest(new int[]{1, 0, 0, 1}));
    }

    public int maxDistToClosest(int[] seats) {
        int[] maxSizes = new int[]{-1, -1, -1};
        int nowSize = 0;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 0) {
                nowSize++;
            } else {
                if (maxSizes[0] == -1) {
                    maxSizes[0] = nowSize;
                } else {
                    maxSizes[1] = Math.max(nowSize, maxSizes[1]);
                }
                nowSize = 0;
            }
        }
        maxSizes[2] = nowSize;
        int middleSize = (maxSizes[1] - 1) / 2 + 1;
        return Math.max(Math.max(middleSize, maxSizes[0]), maxSizes[2]);
    }
}