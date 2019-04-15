package leetcode.isvalid;

import java.util.Stack;

class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().isValid("()"));
    }

    public boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }
        Stack<Character> deque = new Stack<>();
        for (Character chr : s.toCharArray()) {
            if ("([{".indexOf(chr) >= 0) {
                deque.push(chr);
            } else if (")]}".indexOf(chr) >= 0) {
                if (deque.isEmpty()) {
                    return false;
                }
                char now = deque.pop();
                if ("([{".indexOf(now) != ")]}".indexOf(chr)) {
                    return false;
                }
            }
        }
        return deque.isEmpty();
    }
}