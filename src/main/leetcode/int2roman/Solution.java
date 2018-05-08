package leetcode.int2roman;

import java.io.IOException;

class Solution {
    public String intToRoman(int num) {
        if (num <= 0) return "";
        StringBuilder ret = new StringBuilder();
        int[] number = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] flags = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        for (int i = 0; i < 13 && num > 0; i++) {
            if (num < number[i]) continue;
            while (num >= number[i]) {
                num -= number[i];
                ret.append(flags[i]);
            }

        }
        return ret.toString();
    }

    public static void main(String args[]) throws IOException, InterruptedException {
        for (int i = 0; i < 100000; i++) {
            System.out.println(1);
            System.out.print((char)13);
        }
    }
}