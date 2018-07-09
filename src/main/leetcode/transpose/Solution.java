package leetcode.transpose;

import java.util.Arrays;

class Solution {
    public int[][] transpose(int[][] A) {
        int[][] rtn = new int[A[0].length][A.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                rtn[j][i] = A[i][j];
            }
        }
        return rtn;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.asList(new Solution().transpose(new int[][]{{1,2,3},{4,5,6}})));
    }
}