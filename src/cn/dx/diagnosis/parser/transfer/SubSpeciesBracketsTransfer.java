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
        System.out.println(new SubSpeciesBracketsTransfer().trans("≮儿童哮喘‡＞3岁;喘息反复发作;发作时两肺哮鸣音,呼气延长;支气管舒张剂有明显疗效;除外其他原因的喘息、胸闷和咳嗽;支气管舒张试验阳性≯"));
    }
}

