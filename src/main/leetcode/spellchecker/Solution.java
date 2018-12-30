package leetcode.spellchecker;

import java.util.*;

class Solution {
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
        rtn.add(new HashMap<>());
        rtn.add(new HashMap<>());
        rtn.add(new HashMap<>());
        for(String word:wordlist){
            if(!rtn.get(0).containsKey(word)){
                rtn.get(0).put(word,word);
            }
            if(!rtn.get(1).containsKey(word.toLowerCase())){
                rtn.get(1).put(word.toLowerCase(),word);
            }
            if(!rtn.get(2).containsKey(word.toLowerCase().replaceAll("[aeiou]","i"))){
                rtn.get(2).put(word.toLowerCase().replaceAll("[aeiou]","i"),word);
            }
        }
        return rtn;
    }

    private String find(String query, List<Map<String, String>> preCache) {
            if (preCache.get(0).containsKey(query)) {
                return preCache.get(0).get(query);
            }else{
                if(preCache.get(1).containsKey(query.toLowerCase())){
                    return preCache.get(1).get(query.toLowerCase());
                }else if(preCache.get(2).containsKey(query.toLowerCase().replaceAll("[aeiou]","i"))){
                    return preCache.get(2).get(query.toLowerCase().replaceAll("[aeiou]","i"));
                }
            }

        return "";
    }

    public static void main(String[] args) {
        System.out.println(Arrays.asList(new Solution().spellchecker(new String[]{"KiTe", "kite", "hare", "Hare"}, new String[]{"kite", "Kite", "KiTe", "Hare", "HARE", "Hear", "hear", "keti", "keet", "keto"})));
    }
}