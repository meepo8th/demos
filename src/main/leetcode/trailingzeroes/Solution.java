package leetcode.trailingzeroes;

class Solution {
    public int trailingZeroes(int n) {
        int sum = 0;
        for (long i = 5; i <= n; i *= 5) {
            sum += n / i;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().trailingZeroes(1808548329));
    }
}