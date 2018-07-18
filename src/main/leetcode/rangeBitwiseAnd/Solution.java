package leetcode.rangeBitwiseAnd;

class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        int[] cache = new int[32];
        if (m == n) {
            return m;
        } else if (n - m <= 32) {
            long rtn = m;
            for (long i = m + 1; i <= n; i++) {
                rtn = rtn & i;
            }
            return (int)rtn;
        }
        for (int tmp = m, i = 0; tmp != 0; i++) {
            cache[i] = tmp & 0x1 & existZero(m, n, i);
            tmp >>= 1;
        }
        int rtn = 0;
        for (int i = 0; i < 32; i++) {
            rtn += (Math.pow(2, i) * cache[i]);
        }
        return rtn;
    }

    private int existZero(int m, int n, int i) {
        if (Math.pow(2, i + 1) <= n) {
            if (Math.pow(2, i + 1) >= m) {
                return 0;
            } else if (Math.pow(2, i) <= m) {
                return 0;
            }
        }
        return 1;
    }


    public static void main(String[] args) {
        System.out.println(new Solution().rangeBitwiseAnd(6, 7));
    }
}