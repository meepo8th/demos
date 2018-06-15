package leetcode.shfitingletters;

class Solution {
    public String shiftingLetters(String S, int[] shifts) {
        char[] array = S.toCharArray();
        long sum = 0;
        for (int shift : shifts) {
            sum += shift;
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = (char) ((char) ((sum + array[i] - 'a') % 26) + 'a');
            sum -= shifts[i];
        }
        return new String(array);
    }


    public static void main(String[] args) {
        System.out.println(new Solution().shiftingLetters("abc", new int[]{3, 5, 9}));
    }
}