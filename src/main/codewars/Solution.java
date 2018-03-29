package codewars;

public class Solution {

    public static String toCamelCase(String s) {
        StringBuffer sb = new StringBuffer();
        int distance = 'A' - 'a';
        boolean needCamel = false;
        for (int i = 0; null != s && i < s.length(); i++) {
            if (s.charAt(i) == '-' || s.charAt(i) == '_') {
                needCamel = true;
            } else {
                if (needCamel && 'a' <= s.charAt(i) && s.charAt(i) <= 'z') {
                    sb.append((char) (s.charAt(i) + distance));
                } else {
                    sb.append(s.charAt(i));
                }
                needCamel=false;

            }
        }
        return sb.toString();
    }
}