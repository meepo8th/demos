package leetcode.buddyStrings;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public boolean buddyStrings(String A, String B) {
        if (A.length() != B.length()) {
            return false;
        }

        List<Integer> diffrents = new ArrayList<Integer>();
        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) != B.charAt(i)) {
                diffrents.add(i);
                if (diffrents.size() > 2) {
                    break;
                }
            }
        }
        if (diffrents.size() == 2) {
            return A.charAt(diffrents.get(0)) == B.charAt(diffrents.get(1)) && A.charAt(diffrents.get(1)) == B.charAt(diffrents.get(0));
        } else if (diffrents.size() == 0) {
            for (int i = 0; i < A.length(); i++) {
                for (int j = i + 1; j < B.length(); j++) {
                    if (A.charAt(i) == B.charAt(j)) {
                        return true;

                    }
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().buddyStrings("ab".toUpperCase(), "ba"));
    }
}