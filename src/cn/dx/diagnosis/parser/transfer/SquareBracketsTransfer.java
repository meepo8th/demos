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


    @Override
    public String trans(String inputString) throws TransferException {
        String processStr = inputString;
        if (StringUtils.isNotBlank(inputString) && inputString.indexOf(LEFT) >= 0) {
            Deque<Brackets> squareBracketsDeque = Brackets.matchBrackets(processStr, LEFT, RIGHT);
        }
        return processStr;
    }


    public static void main(String[] args) throws TransferException {
        System.out.println(new SquareBracketsTransfer().trans("￥[血气分析:血pH值↓&PaO2↓&HCO3-↓;PCO2↑&BE↑;代谢性酸中毒]"));
    }
}

