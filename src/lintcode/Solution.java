package lintcode;

/**
 * Definition for ListNode
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */


public class Solution {
    /*
     * @param head: ListNode head is the head of the linked list 
     * @param m: An integer
     * @param n: An integer
     * @return: The head of the reversed ListNode
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        // write your code here
        return null;
    }

    public static int parseAnswer(String answerStr) {
        int flag = -1;
        int[] flags = new int[]{1, 2, 4, 8, 16, 32};
        char[] answers = new char[]{'A', 'B', 'C', 'D', 'E'};
        if (null != answerStr && (!"".equals(answerStr))) {
            answerStr = answerStr.toUpperCase();
            for (int i = 0; i < answers.length; i++) {
                if (answerStr.contains("" + answers[i])) {
                    flag += flags[i];
                }
            }
        }
        return flag;
    }

    public static int checkAnswer(String answer, String answerStr) {
        int answerFlag = parseAnswer(answer);
        int strFlag = parseAnswer(answerStr);
        return answerFlag ^ strFlag;
    }

    public static void main(String[] args) {
        System.out.println(checkAnswer("AB", "ABC"));
        System.out.println(checkAnswer("AB", "BA"));
        System.out.println(checkAnswer("AB", "BAC"));
        System.out.println(checkAnswer("ACB", "ABC"));
        System.out.println(checkAnswer("ABCD", "BAC"));
        System.out.println(checkAnswer("BCD", "A"));
    }
}