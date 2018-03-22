package cn.dx.diagnosis.parser.transfer;

import cn.dx.diagnosis.parser.transfer.exception.BracketsException;
import cn.dx.diagnosis.parser.transfer.exception.TransferException;
import cn.dx.diagnosis.parser.transfer.inter.Transfer;

/**
 * ≮≯转换器
 */
public class SubSpeciesBracketsTransfer implements Transfer {
    private static final char LEFT = '≮';
    private static final char RIGHT = '≯';
    BracketsTransfer bracketsTransfer = new BracketsTransfer(LEFT, RIGHT);

    public SubSpeciesBracketsTransfer() throws BracketsException {
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

