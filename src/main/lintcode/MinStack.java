package lintcode;

import java.util.ArrayDeque;
import java.util.Deque;

class MinStack {
    Deque<Integer> stack;
    Integer min = null;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        stack = new ArrayDeque<>();
    }

    public void push(int x) {
        stack.push(x);
        if (null == min || x < min) {
            min = x;
        }
    }

    public void pop() {
        if (stack.isEmpty()) {
            return;
        }
        Integer now = stack.pop();
        if(now.equals(min)){
            Deque<Integer> cache = new ArrayDeque<>(stack.size());
            Integer nowMin = Integer.MAX_VALUE;
            while(!stack.isEmpty()){
                Integer popValue = stack.pop();
                if(popValue<nowMin){
                    nowMin=popValue;
                }
                cache.push(popValue);
            }
            min=nowMin;
            while(!cache.isEmpty()){
                stack.push(cache.pop());
            }
        }
    }

    public int top() {
        int rtn = stack.pop();
        stack.push(rtn);
        return rtn;
    }

    public int getMin() {
        return min;
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(2);
        minStack.push(0);
        minStack.push(3);
        minStack.push(0);
        minStack.pop();
        System.out.println(minStack.top());
        minStack.pop();
        System.out.println(minStack.top());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }
}