package codewars;

import java.util.*;

public class Mixing {

    public static String mix(String s1, String s2) {
        StringBuffer sb = new StringBuffer();
        int[] cache1 = new int[26];
        int[] cache2 = new int[26];
        mergeString(s1, cache1);
        mergeString(s2, cache2);
        Map<Integer, List<String>> cacheMap = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < 26; i++) {
            if (cache1[i] < 2 && cache2[i] < 2) {
                continue;
            }
            char prefix;
            int count;
            if (cache1[i] > cache2[i]) {
                prefix = '1';
                count = cache1[i];
            } else if (cache1[i] == cache2[i]) {
                prefix = '=';
                count = cache1[i];
            } else {
                prefix = '2';
                count = cache2[i];
            }
            if (!cacheMap.containsKey(count)) {
                cacheMap.put(count, new ArrayList<>());
            }
            cacheMap.get(count).add("/" + prefix + ":" + generateStr(i, count));
        }

        for (Map.Entry<Integer, List<String>> entry : cacheMap.entrySet()) {
            entry.getValue().sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });
            for (String str : entry.getValue()) {
                sb.append(str);
            }
        }
        return sb.length() > 0 ? sb.substring(1) : "";
    }

    private static String generateStr(int chr, int count) {
        String rtn = "";
        for (int i = 0; i < count; i++) {
            rtn += (char) (chr + 'a');
        }
        return rtn;
    }

    private static void mergeString(String s1, int[] cache) {
        for (int i = 0; null != s1 && i < s1.length(); i++) {
            char ch = s1.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                cache[ch - 'a']++;
            }
        }
    }
}