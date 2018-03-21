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
        return LEFT + bracketsTransfer.trans(inputString) + RIGHT;
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

    public static void main(String[] args) throws TransferException {
        System.out.println(new SubSpeciesBracketsTransfer().trans("≮肢端变化：起病早期§@手掌@@足底@硬肿#肤色变红；恢复期§指趾末端脱皮≯"));
    }
}

