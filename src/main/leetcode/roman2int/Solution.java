package leetcode.roman2int;

class Solution {
    public int romanToInt(String s) {
        int rtn = 0;
        int[] number = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] flags = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int start = 0;
        while (s.length() > 0) {
            for (int i = start; i < flags.length; i++) {
                if (s.startsWith(flags[i])) {
                    rtn += number[i];
                    start = i;
                    s = s.substring(flags[i].length());
                }
            }
        }
        return rtn;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().romanToInt("VIII"));
    }
}