package cn.dx.diagnosis.parser.transfer;

import cn.dx.diagnosis.parser.bean.Ats;
import cn.dx.diagnosis.parser.transfer.exception.BracketsException;
import cn.dx.diagnosis.parser.transfer.exception.TransferException;
import cn.dx.diagnosis.parser.transfer.inter.Transfer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * []转换器
 */
public class SquareBracketsTransfer implements Transfer {
    private static final char LEFT = '[';
    private static final char RIGHT = ']';

    BracketsTransfer bracketsTransfer = new BracketsTransfer(LEFT, RIGHT);

    public SquareBracketsTransfer() throws BracketsException {
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

