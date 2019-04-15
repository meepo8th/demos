package codility;

public class Solution {
    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(51712));
        System.out.println(new Solution().solution(new int[]{1, 3, 3}));
    }

    public int solution(int N) {
        // write your code in Java SE 8
        int length = 0;
        int maxLength = 0;
        boolean start = false;
        int n = N;
        for (int i = n & 0x1; n > 0; n >>= 1, i = n & 0x1) {
            if (0 == i) {
                if (start) {
                    length += 1;
                }
            } else {
                start = true;
                if (length > maxLength) {
                    maxLength = length;
                }
                length = 0;
            }
        }
        return maxLength;
    }

    public int[] solution(int[] A, int K) {
        // write your code in Java SE 8
        if (A.length > 0) {
            int n = K % A.length;
            if (n > 0) {
                int[] a = new int[n];
                System.arraycopy(A, A.length - n, a, 0, n);
                System.arraycopy(A, 0, A, n, A.length - n);
                System.arraycopy(a, 0, A, 0, n);
            }
        }
        return A;
    }

    public int solution(int[] A) {
        // write your code in Java SE 8
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int a : A) {
            if (a > max) {
                max = a;
            }
            if (a < min) {
                min = a;
            }
        }
        return (min == 1 && max - min == A.length - 1 && detailCompare(A, max, min)) ? 1 : 0;
    }

    public boolean detailCompare(int[] A, int max, int min) {
        if (max - min > A.length) {
            return false;
        }
        boolean[] B = new boolean[max];
        for (int a : A) {
            B[a - 1] = true;
        }
        for (boolean b : B) {
            if (!b) {
                return false;
            }
        }
        return true;
    }
}