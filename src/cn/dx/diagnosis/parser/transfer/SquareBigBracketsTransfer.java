package cn.dx.diagnosis.parser.transfer;

import cn.dx.diagnosis.parser.transfer.exception.BracketsException;
import cn.dx.diagnosis.parser.transfer.exception.TransferException;
import cn.dx.diagnosis.parser.transfer.inter.Transfer;

import java.util.HashSet;
import java.util.Set;

/**
 * []{}转换器
 */
public class SquareBigBracketsTransfer implements Transfer {
    private static final Set<Character> LEFT = new HashSet<>();
    private static final Set<Character> RIGHT = new HashSet<>();

    static {
        LEFT.add('[');
        RIGHT.add(']');
        LEFT.add('{');
        RIGHT.add('}');
    }

    BracketsTransfer bracketsTransfer = new BracketsTransfer(LEFT, RIGHT);

    public SquareBigBracketsTransfer() throws BracketsException {
        //do nothing
    }

    @Override
    public String trans(String inputString) throws TransferException {
        return bracketsTransfer.trans(inputString);
    }

    /**
     * 是否是方括号处理器可以处理的
     *
     * @param inputString
     * @return
     */
    @Override
    public boolean canTrans(String inputString) {
        return bracketsTransfer.canTrans(inputString);
    }

}

