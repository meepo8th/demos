package practice;

class Parent {
    Class thisClass() {
        return this.getClass();
    }
}

class Child extends Parent {

}

public class Solution {
    public static void main(String[] args) {
        int i = 0;
        i = i++;
        String sql = String.format("update stuInfo set stuName='%s' where stuNo='%s'", "123", "456");
        System.out.println();
    }

    /*
     * @param s: A string
     * @return: A string
     */
    public String reverseWords(String s) {
        // write your code here
        StringBuffer sb = new StringBuffer();
        String[] splits = s.split(" ");
        for (String str : splits) {
            if (!" ".equals(str)) {
                sb.append(" " + str);
            }
        }
        return sb.toString().replaceAll("^ +", "");
    }

    /*
     * @param A: An integer array
     * @param B: An integer array
     * @return: a double whose format is *.5 or *.0
     */
    public double findMedianSortedArrays(int[] A, int[] B) {
        // write your code here
        int[] sortedArray = new int[A.length + B.length];
        for (int i = 0, j = 0, k = 0; i < A.length || j < B.length; ) {
            if (i == A.length) {
                sortedArray[k++] = B[j++];
            } else if (j == B.length) {
                sortedArray[k++] = A[i++];
            } else {
                if (A[i] > B[j]) {
                    sortedArray[k++] = B[j++];
                } else {
                    sortedArray[k++] = A[i++];
                }
            }
        }
        if (sortedArray.length % 2 == 0) {
            return (1.0 * (sortedArray[sortedArray.length / 2 - 1] + sortedArray[sortedArray.length / 2])) / 2;
        } else {
            return 1.0 * sortedArray[sortedArray.length / 2];
        }
    }

    public int lengthOfLongestSubstring(String s) {
        // write your code here
        int max = 0;
        int[] flags = new int[255];
        int i = 0, j = 0;
        for (; i < s.length() && j < s.length(); ) {
            if (flags[s.charAt(j)] == 0) {
                flags[s.charAt(j++)] = 1;
            } else {
                if (j - i > max) {
                    max = j - i;
                }
                flags[s.charAt(i++)] = 0;
            }

        }
        return max > j - i ? max : j - i;
    }

    /*
     * @param n: the integer to be reversed
     * @return: the reversed integer
     */
    public int reverseInteger(int n) {
        // write your code here
        long reverseLong = 0l;
        int sign = n >= 0 ? 1 : -1;
        for (int value = sign * n; value > 0; ) {
            if (0 != reverseLong) {
                reverseLong *= 10;
            }
            reverseLong += value % 10;
            value = value / 10;
        }
        reverseLong = reverseLong * sign;
        if (reverseLong > Integer.MAX_VALUE || reverseLong < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) reverseLong;
    }

    /*
     * @param s: input string
     * @return: the longest palindromic substring
     */
    public String longestPalindrome(String s) {
        // write your code here
        int longest = s.length() > 0 ? 1 : 0;
        int longStart = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length - 1; i++) {
            int start, end;
            if (chars[i] == chars[i + 1]) {
                start = i;
                end = i + 1;
            } else if (chars[i - 1] == chars[i + 1]) {
                start = i - 1;
                end = i + 1;
            } else {
                start = i;
                end = i;
            }
            while (start >= 0 && end <= s.length() - 1 && chars[start] == chars[end]) {
                start--;
                end++;
            }
            if (end - start - 1 > longest) {
                longest = end - start + 1;
                longStart = start + 1;
            }
        }
        return s.substring(longStart, longStart + longest);
    }
}