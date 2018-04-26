package leetcode.trailingzeroes;

/**
 * 阶乘末尾的0，其实就是找5的个数
 */
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