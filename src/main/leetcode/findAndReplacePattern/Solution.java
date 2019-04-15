package leetcode.findAndReplacePattern;

import java.util.*;

class Solution {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> rtn = new ArrayList<>();
        Map<Character, List<Integer>> patternInfo = findPattern(pattern);
        Map<Character, List<Integer>> patternWord;
        for (String word : words) {
            patternWord = findPattern(word);
            if (patternMatch(patternWord, patternInfo)) {
                rtn.add(word);
            }
        }
        return rtn;
    }

    private Map<Character, List<Integer>> findPattern(String pattern) {
        Map<Character, List<Integer>> rtn = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            if (!rtn.containsKey(pattern.charAt(i))) {
                rtn.put(pattern.charAt(i), new ArrayList<>());
            }
            rtn.get(pattern.charAt(i)).add(i);
        }
        return rtn;
    }

    private boolean patternMatch(Map<Character, List<Integer>> pattern1, Map<Character, List<Integer>> pattern2) {
        if (pattern1.size() == pattern2.size()) {
            Collection<List<Integer>> values1 = pattern1.values();
            Collection<List<Integer>> values2 = pattern2.values();
            for (List<Integer> value1 : values1) {
                if (!values2.contains(value1)) {
                    return false;
                }
            }
            for (List<Integer> value2 : values2) {
                if (!values1.contains(value2)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}