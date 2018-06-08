package leetcode.test;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 队列实现的栈
 *
 * @author Administrator
 */
public class QueueStack<T> {
    Queue<T> nowQue = new ArrayDeque<>();
    Queue<T> otherQue = new ArrayDeque<>();


    public T pop() {
        T last = null;
        while (!nowQue.isEmpty()) {
            last = nowQue.peek();
            nowQue.poll();
            if (!nowQue.isEmpty()) {
                otherQue.offer(last);
            }
        }
        switchQueue();
        return last;
    }

    /**
     * 交换当前队列
     */
    private void switchQueue() {
        Queue<T> tmp = nowQue;
        nowQue = otherQue;
        otherQue = tmp;
    }

    public boolean push(T obj) {
        return nowQue.offer(obj);
    }

    public boolean isEmpty() {
        return nowQue.isEmpty();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{\"QueueStack\":{");
        sb.append("\"nowQue\":")
                .append(nowQue);
        sb.append(",\"otherQue\":")
                .append(otherQue);
        sb.append("}}");
        return sb.toString();
    }

    public static void main(String[] args) {
        QueueStack<String> queueStack = new QueueStack();
        queueStack.push("1");
        queueStack.push("2");
        queueStack.push("3");
        queueStack.push("4");
        while (!queueStack.isEmpty()) {
            System.out.println(queueStack.pop());
        }
    }
}
