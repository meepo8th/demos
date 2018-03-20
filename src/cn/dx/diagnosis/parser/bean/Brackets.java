package cn.dx.diagnosis.parser.bean;

import cn.dx.diagnosis.parser.transfer.exception.BracketsException;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 括号类
 */
public class Brackets {
    public static final String BRACKET_NOT_MATCH = "括号数量不匹配";
    private int left = -1;//左括号位置
    private int right = -1;//右括号位置

    public Brackets(int left, int right) {
        this.left = left;
        this.right = right;
    }

    public Brackets() {
    }

    public Brackets(int left) {
        this.left = left;
    }

    /**
     * 匹配左右括号
     *
     * @param processStr
     * @param left
     * @param right
     * @return
     * @throws BracketsException
     */
    public static Deque<Brackets> matchBrackets(String processStr, final char left, final char right) throws BracketsException {
        ArrayDeque<Brackets> squareBracketsDeque = new ArrayDeque<>();
        ArrayDeque<Brackets> tmpDeque = new ArrayDeque<>();
        for (int i = 0; i < processStr.length(); i++) {
            if (left == processStr.charAt(i)) {
                squareBracketsDeque.push(new Brackets(i));
            } else if (right == processStr.charAt(i)) {
                tmpDeque.clear();
                while (!squareBracketsDeque.isEmpty()) {
                    Brackets squareBrackets = squareBracketsDeque.pop();
                    if (squareBrackets.getRight() < 0) {
                        squareBrackets.setRight(i);
                        tmpDeque.push(squareBrackets);
                        break;
                    } else {
                        tmpDeque.push(squareBrackets);
                    }
                }
                while (!tmpDeque.isEmpty()) {
                    squareBracketsDeque.push(tmpDeque.pop());
                }
            }
        }
        checkBracketsDeque(squareBracketsDeque);
        return squareBracketsDeque;
    }

    /**
     * 检查是否匹配
     *
     * @param bracketsArrayDeque
     * @throws BracketsException
     */
    private static void checkBracketsDeque(ArrayDeque<Brackets> bracketsArrayDeque) throws BracketsException {
        for (Brackets bracket : bracketsArrayDeque) {
            if (!bracket.match()) {
                throw new BracketsException(BRACKET_NOT_MATCH);
            }
        }
    }

    /**
     * 是否匹配
     *
     * @return
     */
    private boolean match() {
        return left < 0 || right < 0;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Brackets{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}