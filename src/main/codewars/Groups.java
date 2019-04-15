package codewars;

import java.util.Stack;

public class Groups {

    public static boolean groupCheck(String s) {
        Stack<String> stack = new Stack<String>();
        char[] charArray = s.toCharArray();
        for (char chr : charArray) {
            if ("({[".indexOf(chr) >= 0) {
                stack.push(String.valueOf(chr));
            }
            if ("])}".indexOf(chr) >= 0) {
                if (stack.isEmpty() || !match(stack.pop(), chr)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static boolean match(String a, char b) {
        return "([{".indexOf(a) == ")]}".indexOf(b);
    }

    public static void main(String args[]) {
        System.out.println(groupCheck("((())]"));
    }

}