package cn.dx.diagnosis.parser.transfer;

import cn.dx.diagnosis.parser.bean.Brackets;
import cn.dx.diagnosis.parser.transfer.exception.TransferException;
import cn.dx.diagnosis.parser.transfer.inter.Transfer;
import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * []转换器
 */
public class SquareBracketsTransfer implements Transfer {
    private static final char LEFT = '[';
    private static final char RIGHT = ']';
    ColonTransfer colonTransfer = new ColonTransfer();

    @Override
    public String trans(String inputString) throws TransferException {
        String processStr = inputString;
        if (StringUtils.isNotBlank(inputString) && inputString.indexOf(LEFT) >= 0) {
            Deque<Brackets> squareBracketsDeque = Brackets.matchBrackets(processStr, LEFT, RIGHT);
            StringBuilder processed = new StringBuilder();
            while (!squareBracketsDeque.isEmpty()) {
                Brackets brackets = squareBracketsDeque.pop();
                processed.append(processStr.substring(0, brackets.getLeft()) + processOnebrackets(processStr, brackets) + processStr.substring(brackets.getRight() + 1));
            }
            processStr = processed.toString();
        }
        return processStr;
    }

    /**
     * 处理一个括号
     *
     * @param processStr
     * @param brackets
     * @return
     */
    private String processOnebrackets(String processStr, Brackets brackets) throws TransferException {
        return colonTransfer.trans(processStr.substring(brackets.getLeft(), brackets.getRight() + 1));
    }


    public static void main(String[] args) throws TransferException {
        System.out.println(new SquareBracketsTransfer().trans("123;[A:B|C|D;E;F];456"));
    }
}

