package lintcode;

import java.util.Stack;

public class MinStack {
    Stack<Integer> stack;
    Stack<Integer> minStack;

    /*
     * @param a: An integer
     */
    public MinStack(int a) {
        // do intialization if necessary
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    /*
     * @param number: An integer
     * @return: nothing
     */
    public void push(int number) {
        // write your code here
        if (!minStack.isEmpty()) {
            int top = minStack.pop();
            minStack.push(top);
            if (top > number) {
                minStack.push(number);
            } else {
                minStack.push(top);
            }
        } else {
            minStack.push(number);
        }
        stack.push(number);
    }

    /*
     * @return: An integer
     */
    public int pop() {
        // write your code here
        minStack.pop();
        return stack.pop();
    }

    /*
     * @return: An integer
     */
    public int min() {
        int top = minStack.pop();
        minStack.push(top);
        return top;
    }
}