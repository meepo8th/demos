package leetcode.isMonotonic;

class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().isMonotonic(new int[]{1, 1, 2}));
        System.out.println(new Solution().isMonotonic(new int[]{1, 2, 2, 3}));
        System.out.println(new Solution().isMonotonic(new int[]{6, 5, 4, 4}));
        System.out.println(new Solution().isMonotonic(new int[]{1, 3, 2}));
        System.out.println(new Solution().isMonotonic(new int[]{1, 2, 4, 5}));
        System.out.println(new Solution().isMonotonic(new int[]{1, 1, 1}));
    }

    public boolean isMonotonic(int[] A) {
        if (A.length == 1) {
            return true;
        }
        int caseCompare = 0;
        for (int i = 0; i < A.length - 1; i++) {
            if (A[i] == A[i + 1]) {
                continue;
            }
            if (caseCompare == 0) {
                if (A[i] >= A[i + 1]) {
                    caseCompare = 1;
                } else {
                    caseCompare = -1;
                }

            } else if (caseCompare == 1) {
                if (A[i] < A[i + 1]) {
                    return false;
                }
            } else {
                if (A[i] > A[i + 1]) {
                    return false;
                }
            }
        }

        return true;
    }
}