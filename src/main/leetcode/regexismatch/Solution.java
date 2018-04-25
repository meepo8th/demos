package leetcode.regexismatch;

import java.util.Arrays;

class Solution {
    public boolean isMatch(String s, String p) {
        int i = 0, j = 0;
        char last = 0;
        for (; i < p.length() && j < s.length(); ) {
            if (p.charAt(i) == s.charAt(j) || p.charAt(i) == '.') {
                last = p.charAt(i);
                i++;
                j++;
            } else {
                if (p.charAt(i) == '*') {
                    if (last == s.charAt(j) || last == '.') {
                        j++;
                    } else {
                        i++;
                    }
                } else {
                    if (i < p.length() - 1 && p.charAt(i + 1) == '*') {
                        i += 2;
                    } else {
                        return false;
                    }
                }
            }
        }
        return (i == p.length() || (p.length() >= 1 && i == p.length() - 1 && p.charAt(i) == '*')) && j == s.length();
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString("你或者我或他".split("\\||或者|或")));
    }
}