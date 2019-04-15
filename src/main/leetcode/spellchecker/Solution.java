package leetcode.spellchecker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class Solution {
    public static void main(String[] args) {
        System.out.println((int) (System.currentTimeMillis() / 1000));
    }

    public String[] spellchecker(String[] wordlist, String[] queries) {
        String[] rtn = new String[queries.length];
        List<Map<String, String>> preCache = preCache(wordlist);
        for (int i = 0; i < queries.length; i++) {
            rtn[i] = find(queries[i], preCache);
        }
        return rtn;
    }

    private List<Map<String, String>> preCache(String[] wordlist) {
        List<Map<String, String>> rtn = new ArrayList<>(3);
        rtn.add(new TreeMap<>());
        rtn.add(new TreeMap<>());
        rtn.add(new TreeMap<>());
        String[] words;
        for (String word : wordlist) {
            words = preCache(word);
            if (!rtn.get(0).containsKey(word)) {
                rtn.get(0).put(word, word);
            }
            if (!rtn.get(1).containsKey(words[0])) {
                rtn.get(1).put(words[0], word);
            }
            if (!rtn.get(2).containsKey(words[1])) {
                rtn.get(2).put(words[1], word);
            }
        }
        return rtn;
    }

    private String[] preCache(String word) {
        StringBuilder lower = new StringBuilder();
        StringBuilder yuan = new StringBuilder();
        char low;
        for (char ch : word.toCharArray()) {
            low = Character.toLowerCase(ch);
            lower.append(low);
            if (low == 'a' || low == 'e' || low == 'i' || low == 'o' || low == 'u') {
                yuan.append('a');
            } else {
                yuan.append(low);
            }
        }
        return new String[]{lower.toString(), yuan.toString()};
    }

    private String find(String query, List<Map<String, String>> preCache) {
        if (preCache.get(0).containsKey(query)) {
            return preCache.get(0).get(query);
        } else {
            String[] words = preCache(query);
            if (preCache.get(1).containsKey(words[0])) {
                return preCache.get(1).get(words[0]);
            } else if (preCache.get(2).containsKey(words[1])) {
                return preCache.get(2).get(words[1]);
            }
        }

        return "";
    }
}