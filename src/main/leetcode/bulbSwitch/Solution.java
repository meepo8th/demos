package leetcode.bulbSwitch;

class Solution {
    public static void main(String[] args) {
        long l1 = System.currentTimeMillis();
        System.out.println(new Solution().bulbSwitch(99999999));
        System.out.println(System.currentTimeMillis() - l1);
    }

    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }
}