package cn.dx.diagnosis.parser.transfer;

import cn.dx.diagnosis.parser.bean.Brackets;
import cn.dx.diagnosis.parser.transfer.exception.BracketsException;
import cn.dx.diagnosis.parser.transfer.exception.TransferException;
import cn.dx.diagnosis.parser.transfer.inter.Transfer;
import org.apache.commons.lang3.StringUtils;

import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * 括号转换器
 */
public class BracketsTransfer implements Transfer {
    private Set<Character> left = new HashSet<>();
    private Set<Character> right = new HashSet<>();
    private ColonTransfer colonTransfer = new ColonTransfer();


    public BracketsTransfer(char left, char right) throws BracketsException {
        this.left.add(left);
        this.right.add(right);
        checkBrackets();
    }

    public BracketsTransfer(Set<Character> left, Set<Character> right) throws BracketsException {
        this.left = left;
        this.right = right;
        checkBrackets();
    }


    /**
     * 隐藏默认构造器 并抛出异常防止反射调用
     *
     * @throws BracketsException
     */
    private BracketsTransfer() throws BracketsException {
        checkBrackets();
    }

    private void checkBrackets() throws BracketsException {
        if (null == left || null == right || left.isEmpty() || right.isEmpty() || (left.size() != right.size())) {
            throw new BracketsException(BracketsException.BRACKET_NOT_SET);
        }
    }

    @Override
    public String trans(String inputString) throws TransferException {
        String processStr = inputString;
        if (canTrans(inputString)) {
            Character lastBracketsLeft = ' ';
            for (Deque<Brackets> squareBracketsDeque = Brackets.matchBrackets(processStr, left, right); !squareBracketsDeque.isEmpty(); squareBracketsDeque = Brackets.matchBrackets(processStr, left, right)) {
                String prefix = "";
                String after = "";
                if (!squareBracketsDeque.isEmpty()) {
                    Brackets brackets = squareBracketsDeque.pop();
                    prefix = processStr.substring(0, brackets.getLeft());
                    after = processStr.substring(brackets.getRight() + 1);
                    String afterColon = processOnebrackets(processStr, brackets);

                    Character bracketsLeft = processStr.charAt(brackets.getLeft());
                    Character bracketsRight = processStr.charAt(brackets.getRight());
                    lastBracketsLeft = bracketsLeft;
                    if ('[' != bracketsLeft || ']' != bracketsRight) {
                        afterColon = "" + bracketsLeft + afterColon + bracketsRight;
                        processStr = prefix + TransferCode.encode(afterColon) + after;
                    } else {
                        processStr = prefix + TransferCode.decodeCanRepeat(TransferCode.encode(afterColon)) + after;
                    }
                } else if ('[' == lastBracketsLeft) {
                    processStr = TransferCode.decode(processStr);
                }

            }
        }
        return processStr;
    }

    /**
     * 是否是方括号处理器可以处理的
     *
     * @param inputString
     * @return
     */
    @Override
    public boolean canTrans(String inputString) {
        if (StringUtils.isNotBlank(inputString)) {
            int leftPos = -1;
            int rightPos = -1;
            for (int i = 0; i < inputString.length(); i++) {
                if (left.contains(inputString.charAt(i)) && leftPos < 0) {
                    leftPos = i;
                }
                if (right.contains(inputString.charAt(i)) && rightPos < 0) {
                    rightPos = i;
                }
            }
            return (leftPos >= 0) && (rightPos > leftPos);
        }
        return false;
    }


    /**
     * 处理一个括号
     *
     * @param processStr
     * @param brackets
     * @return
     */
    private String processOnebrackets(String processStr, Brackets brackets) throws TransferException {
        String unProcessStr = processStr.substring(brackets.getLeft(), brackets.getRight() + 1);
        if (colonTransfer.canTrans(unProcessStr)) {
            //转换冒号
            return colonTransfer.trans(unProcessStr);
        } else {
            //直接去除括号
            return processStr.substring(brackets.getLeft() + 1, brackets.getRight());
        }
    }

    public Set<Character> getLeft() {
        return left;
    }

    public void setLeft(Set<Character> left) {
        this.left = left;
    }

    public Set<Character> getRight() {
        return right;
    }

    public void setRight(Set<Character> right) {
        this.right = right;
    }

}



