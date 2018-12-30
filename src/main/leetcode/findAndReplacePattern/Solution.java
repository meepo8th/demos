package leetcode.findAndReplacePattern;

import java.util.*;

class Solution {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> rtn = new ArrayList<>();
        for (String word : words) {
            if (patternEqulas(word, pattern)) {
                rtn.add(word);
            }
        }
        return rtn;
    }

    private boolean patternEqulas(String word, String pattern) {
        if (word.equals(pattern)) {
            return true;
        } else if (word.length() != pattern.length()) {
            return false;
        } else {
            List<Character> listWord = new ArrayList<>();
            List<Character> listPattern = new ArrayList<>();
            for (Character a : word.toCharArray()) {
                if (!listWord.contains(a)) {
                    listWord.add(a);
                }
            }
            for (Character a : pattern.toCharArray()) {
                if (!listPattern.contains(a)) {
                    listPattern.add(a);
                }
            }
            if (listWord.size() != listPattern.size()) {
                return false;
            }

            Map<Character, List<Integer>> posPattern = new HashMap<>();
            Map<Character, List<Integer>> posWord = new HashMap<>();
            posWord(word, posWord);
            posWord(pattern, posPattern);
            return collectionEquals(posPattern.values(), posWord.values());
        }

    }

    private boolean collectionEquals(Collection<List<Integer>> collection1, Collection<List<Integer>> collection2) {
        for (List<Integer> collection : collection1) {
            if (!collection2.contains(collection)) {
                return false;
            }
        }
        for (List<Integer> collection : collection2) {
            if (!collection1.contains(collection)) {
                return false;
            }
        }
        return true;
    }

    private void posWord(String word, Map<Character, List<Integer>> posWord) {
        for (int i = 0; i < word.length(); i++) {
            if (!posWord.containsKey(word.charAt(i))) {
                posWord.put(word.charAt(i), new ArrayList<>());
            }
            posWord.get(word.charAt(i)).add(i);
        }
    }


    public static void main(String[] args) {
        System.out.println(new Solution().findAndReplacePattern(new String[]{"abc", "deq", "mee", "aqq", "dkd", "ccc"}, "abb"));
    }
}