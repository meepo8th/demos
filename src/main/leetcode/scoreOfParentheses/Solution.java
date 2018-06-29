package leetcode.scoreOfParentheses;

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int scoreOfParentheses(String S) {
        int sum = 0;
        int count = 0;
        int level = 0;
        Deque<Character> characters = new ArrayDeque<>();
        for (int i = 0; i < S.length(); i++) {
            switch (S.charAt(i)) {
                case '(':
                    characters.push(' ');
                    break;
                case ')':
                    characters.pop();
                    if (characters.isEmpty()) {
                        count=(0==count?1:count*2);
                    }else{

                    }
                    sum+=count;
                    break;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().scoreOfParentheses("(()(()))"));
    }
}