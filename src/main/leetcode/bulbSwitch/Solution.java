package leetcode.bulbSwitch;

class Solution {
    public int bulbSwitch(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            int now = 0;
            for (int j = 1; j * j <= i; j++) {
                if (i % j == 0) {
                    now++;
                }
            }
            if (now % 2 == 0) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().bulbSwitch(6));
    }
}