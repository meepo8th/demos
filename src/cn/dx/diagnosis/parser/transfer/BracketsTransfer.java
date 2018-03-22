package cn.dx.diagnosis.parser.transfer;

import cn.dx.diagnosis.parser.bean.Brackets;
import cn.dx.diagnosis.parser.transfer.exception.BracketsException;
import cn.dx.diagnosis.parser.transfer.exception.TransferException;
import cn.dx.diagnosis.parser.transfer.inter.Transfer;
import org.apache.commons.lang.StringUtils;

import java.util.Deque;

/**
 * 括号转换器
 */
public class BracketsTransfer implements Transfer {
    private Character left;
    private Character right;
    private boolean removeBrackets = false;
    private ColonTransfer colonTransfer = new ColonTransfer();

    public BracketsTransfer(char left, char right) throws BracketsException {
        this.left = left;
        this.right = right;
        checkBrackets();
    }

    public BracketsTransfer(Character left, Character right, boolean removeBrackets) throws BracketsException {
        this.left = left;
        this.right = right;
        this.removeBrackets = removeBrackets;
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
        if (null == left || null == right) {
            throw new BracketsException(BracketsException.BRACKET_NOT_SET);
        }
    }

    @Override
    public String trans(String inputString) throws TransferException {
        String processStr = inputString;
        if (StringUtils.isNotBlank(inputString) && inputString.indexOf(left) >= 0) {
            for (Deque<Brackets> squareBracketsDeque = Brackets.matchBrackets(processStr, left, right); !squareBracketsDeque.isEmpty(); squareBracketsDeque = Brackets.matchBrackets(processStr, left, right)) {
                String prefix = "";
                String after = "";
                if (!squareBracketsDeque.isEmpty()) {
                    Brackets brackets = squareBracketsDeque.pop();
                    prefix = processStr.substring(0, brackets.getLeft());
                    after = processStr.substring(brackets.getRight() + 1);
                    String afterColon = processOnebrackets(processStr, brackets);
                    if (!removeBrackets) {
                        Character bracketsLeft = processStr.charAt(brackets.getLeft());
                        Character bracketsRight = processStr.charAt(brackets.getRight());
                        afterColon = "" + bracketsLeft + afterColon + bracketsRight;
                    }
                    processStr = prefix + TransferCode.encode(afterColon) + after;
                }
            }
        }
        return TransferCode.decode(processStr);
    }

    /**
     * 是否是方括号处理器可以处理的
     *
     * @param inputString
     * @return
     */
    @Override
    public boolean canTrans(String inputString) {
        return StringUtils.isNotBlank(inputString) && (inputString.indexOf(left) >= 0 && inputString.indexOf(right) > inputString.indexOf(left));
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

    public char getLeft() {
        return left;
    }

    public void setLeft(char left) {
        this.left = left;
    }

    public char getRight() {
        return right;
    }

    public void setRight(char right) {
        this.right = right;
    }

}



