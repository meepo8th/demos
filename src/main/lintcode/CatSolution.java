package lintcode;

import java.util.*;

/**
 * LintCode Cat
 */
public class CatSolution {

    /**
     * 水仙花数
     *
     * @param n
     * @return
     */
    public static List<Integer> getNarcissisticNumbers(int n) {
        // write your code here
        List<Integer> rtn = new ArrayList<>();
        int[] powMap = new int[10];
        for (int i = 0; i < powMap.length; i++) {
            powMap[i] = (int) Math.pow(i, n);
        }
        int start = Math.max(0, (int) Math.pow(10, n - 1) - 1);
        int end = (int) Math.pow(10, n);
        for (int i = start; i < end; i++) {
            int now = i;
            int sum = 0;
            while (now > 0) {
                sum += powMap[now % 10];
                now = now / 10;
            }
            if (i == sum) {
                rtn.add(i);
            }
        }
        return rtn;
    }

    /**
     * 旋转字符串（O(n)空间)
     *
     * @param str
     * @param offset
     * @return
     */
    public static String rotateString(char[] str, int offset) {
        if (str.length == 0) {
            return "";
        }
        offset = (offset + str.length) % str.length;
        String start = new String(str);
        String end = start.substring(start.length() - offset) + start.substring(0, start.length() - offset);
        for (int i = 0; i < start.length(); i++) {
            str[i] = end.charAt(i);
        }
        return end;
    }


    /**
     * 是否唯一字符串
     *
     * @param str
     * @return
     */
    public boolean isUnique(String str) {
        if (str.length() > Character.MAX_VALUE) {
            return false;
        }
        Set<Character> cache = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            if (cache.contains(str.charAt(i))) {
                return false;
            }
            cache.add(str.charAt(i));
        }
        return true;
    }

    /**
     * 查找第一个不重复的字符
     *
     * @param str
     * @return
     */
    public char firstUniqChar(String str) {
        Map<Character, Integer> cache = new LinkedHashMap<>();
        for (int i = 0; i < str.length(); i++) {
            if (cache.containsKey(str.charAt(i))) {
                cache.put(str.charAt(i), cache.get(str.charAt(i)) + 1);
            } else {
                cache.put(str.charAt(i), 0);
            }
        }
        for (Map.Entry<Character, Integer> entry : cache.entrySet()) {
            if (entry.getValue() == 0) {
                return entry.getKey();
            }
        }
        return '0';
    }

    /**
     * 反转单词
     *
     * @param s
     * @return
     */
    public static String reverseWords(String s) {
        StringBuffer sb = new StringBuffer("");
        // write your code here
        String[] cache = s.replaceAll(" +", " ").split(" ");
        for (int i = cache.length - 1; i >= 0; i--) {
            sb.append(cache[i] + " ");
        }
        return sb.toString().trim();
    }

    /**
     * 语法错误计数
     *
     * @param s
     * @return
     */
    public static int count(String s) {
        // Write your code here.
        int count = 0;
        String[] sentences = s.split("\\.");
        for (String sentence : sentences) {
            sentence = sentence.trim();
            if (sentence.length() == 0) {
                continue;
            }
            if (Character.isLowerCase(sentence.charAt(0))) {
                count++;
            }
            String[] words = sentence.split(",| +");
            for (String word : words) {
                for (int i = 0; i < word.length(); i++) {
                    if (Character.isUpperCase(word.charAt(i)) && i > 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    /**
     * 出现最多的字符数
     *
     * @param str
     * @return
     */
    public int mostFrequentlyAppearingLetters(String str) {
        Map<Character, Integer> cache = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            if (cache.containsKey(str.charAt(i))) {
                cache.put(str.charAt(i), cache.get(str.charAt(i)) + 1);
            } else {
                cache.put(str.charAt(i), 1);
            }
        }
        int max = 0;
        for (Map.Entry<Character, Integer> entry : cache.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
            }
        }
        return max;
    }

    /**
     * 合法表达式
     *
     * @param str
     * @return
     */
    public static boolean isLegalIdentifier(String str) {
        return str.length() > 0 && (str.charAt(0) < '0' || str.charAt(0) > '9') && str.replaceAll("[a-z|A-Z|0-9|_]", "").length() == 0;
    }

    public static void main(String[] args) {
        System.out.println(isLegalIdentifier("LintCode"));
    }
}