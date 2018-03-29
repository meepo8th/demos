package codewars;

public class Scramblies {

    public static boolean scramble(String str1, String str2) {

        int[] cache = new int[26];
        for (char s : str1.toCharArray()) {
            cache[s - 'a']++;
        }
        for (char s : str2.toCharArray()) {
            if (cache[s - 'a'] > 0) {
                cache[s - 'a']--;
            } else {
                return false;
            }
        }
        return true;
    }
}