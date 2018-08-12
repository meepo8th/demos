package leetcode.uncommonFromSentences;

import java.util.*;

class Solution {
    public String[] uncommonFromSentences(String A, String B) {
        String[] aSplit = A.split(" +");
        String[] bSplit = B.split(" +");
        Map<String, Integer> mapCount = new HashMap<>();
        for (String s : aSplit) {
            if (!mapCount.containsKey(s)) {
                mapCount.put(s, 1);
            } else {
                mapCount.put(s, mapCount.get(s) + 1);
            }
        }

        for (String s : bSplit) {
            if (!mapCount.containsKey(s)) {
                mapCount.put(s, 1);
            } else {
                mapCount.put(s, mapCount.get(s) + 1);
            }
        }
        List<String> rtn = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : mapCount.entrySet()) {
            if (entry.getValue() == 1) {
                rtn.add(entry.getKey());
            }
        }
        return rtn.toArray(new String[0]);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().uncommonFromSentences("this apple is sweet", "this apple is sour")));
        System.out.println(Arrays.toString(new Solution().uncommonFromSentences("apple apple", "banana")));
    }
}