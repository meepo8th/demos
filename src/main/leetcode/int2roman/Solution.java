package leetcode.int2roman;

class Solution {
    public String intToRoman(int num) {
        char[] romanChars = new char[]{'I', 'V', 'X', 'L', 'C', 'D', 'M'};
        int[] romanValues = new int[]{1, 5, 10, 50, 100, 500, 1000};
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            for (int i = romanValues.length - 1; i >= 0; i--) {
                if (num / romanValues[i] > 0) {
                    int last = num % romanValues[i];
                    if (last % 4 == 0) {

                    }
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().intToRoman(3));
    }
}