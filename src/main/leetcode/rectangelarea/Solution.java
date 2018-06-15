package leetcode.rectangelarea;

class Solution {
    public int rectangleArea(int[][] rectangles) {
        int sum = 0;
        long mod = (long) (Math.pow(10,9)+7);
        for (int[] rectangle:rectangles) {
            long cache = 1L*(rectangle[3]-rectangle[1])*(rectangle[2]-rectangle[0]);
            sum+=cache%(mod);
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().rectangleArea(new int[][]{{0, 0, 2, 2}, {1, 0, 2, 3}, {1, 0, 3, 1}}));
        System.out.println(new Solution().rectangleArea(new int[][]{{0, 0, 1000000000, 1000000000}}));
    }
}