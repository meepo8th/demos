package lintcode;

import java.util.Stack;

public class MyQueue {
    Stack<Integer> stack;
    Stack<Integer> otherStack;

    public MyQueue() {
        // do intialization if necessary
        stack=new Stack<>();
        otherStack = new Stack<>();
    }

    /*
     * @param element: An integer
     * @return: nothing
     */
    public void push(int element) {
        // write your code here
        if(stack.isEmpty()){
            while(!otherStack.isEmpty()){
                stack.push(otherStack.pop());
            }
        }
        stack.push(element);

    }

    /*
     * @return: An integer
     */
    public int pop() {
        // write your code here
        if(otherStack.isEmpty()) {
            while (!stack.isEmpty()) {
                otherStack.push(stack.pop());
            }
        }
        return otherStack.pop();
    }

    /*
     * @return: An integer
     */
    public int top() {
        // write your code here
        int pop = pop();
        otherStack.push(pop);
        return pop;
    }

    public static void main(String[] args){
        MyQueue myQueue = new MyQueue();
        myQueue.push(1);
        System.out.println(myQueue.pop());
        myQueue.push(2);
        myQueue.push(3);
        System.out.println(myQueue.top());
        System.out.println(myQueue.pop());
    }
}