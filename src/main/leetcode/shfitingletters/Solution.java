package leetcode.shfitingletters;

class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().shiftingLetters("abc", new int[]{3, 5, 9}));
    }

    public String shiftingLetters(String S, int[] shifts) {
        char[] array = S.toCharArray();
        int sum = 0;
        for (int i = array.length - 1; i >= 0; i--) {
            sum += shifts[i] % 26;
            array[i] = (char) ((sum + array[i] - 'a') % 26 + 'a');
        }
        return new String(array);
    }
}