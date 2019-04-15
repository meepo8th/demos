package leetcode.peakindexinmountainarray;

class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().peakIndexInMountainArray(new int[]{0, 2, 1, 0}));
    }

    public int peakIndexInMountainArray(int[] A) {
        int last = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            if (A[i] < last) {
                return i - 1;
            }
            last = A[i];
        }
        return -1;
    }
}