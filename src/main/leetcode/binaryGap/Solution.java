package leetcode.binaryGap;

class Solution {
    public int binaryGap(int N) {
        int start = 0;
        int end = 0;
        int max = 0;
        for (int i = 0; i < 32; i++) {
            int now = N >> i;
            if (now == 0) {
                break;
            }
            if (1 == (now & 0x1)) {
                end = i;
                if (end > start) {
                    if (((N >> start)&0x1) == 1) {
                        max = Math.max(end - start, max);
                    }
                    start = end;
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().binaryGap(8));
    }
}