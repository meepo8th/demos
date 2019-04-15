package leetcode.nthMagicalNumber;

class Solution {
    public static void main(String[] args) {
        System.out.println(1000000000L * 40000);
        System.out.println(new Solution().nthMagicalNumber(1, 2, 3));
        System.out.println(new Solution().nthMagicalNumber(4, 2, 3));
        System.out.println(new Solution().nthMagicalNumber(5, 2, 4));
        System.out.println(new Solution().nthMagicalNumber(3, 6, 4));
        System.out.println(new Solution().nthMagicalNumber(1000000000, 40000, 40000));
        System.out.println(new Solution().nthMagicalNumber(1000000000, 39999, 40000));

    }

    public int nthMagicalNumber(int N, int A, int B) {
        long i = 1;
        if (A == B) {
            return (int) (((long) A * N) % ((int) Math.pow(10, 9) + 7));
        }
        for (int count = 0; count < N; i++) {
            if (i % A == 0 && i % B == 0) {
                count++;
            } else if (i % A == 0) {
                count++;
            } else if (i % B == 0) {
                count++;
            }
        }
        return (int) ((i - 1) % (Math.pow(10, 9) + 7));
    }
}