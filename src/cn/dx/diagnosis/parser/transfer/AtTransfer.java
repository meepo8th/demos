package cn.dx.diagnosis.parser.transfer;

import cn.dx.diagnosis.parser.bean.Ats;
import cn.dx.diagnosis.parser.transfer.exception.TransferException;
import cn.dx.diagnosis.parser.transfer.inter.Transfer;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * @部位转换器
 */
public class AtTransfer implements Transfer {
    private static final char FLAG = '@';

    @Override
    public String trans(String inputString) throws TransferException {
        String processStr = inputString;
        if (StringUtils.isNotBlank(inputString) && inputString.indexOf(FLAG) >= 0) {
            List<Ats> listAts = Ats.matchAts(inputString);
            if (!listAts.isEmpty()) {
                StringBuilder processBuffer = new StringBuilder("{");
                String prefix = inputString.substring(0, listAts.get(0).getLeft());
                String after = inputString.substring(listAts.get(listAts.size() - 1).getRight() + 1);
                for (Ats ats : listAts) {
                    processBuffer.append(prefix);
                    processBuffer.append(inputString.substring(ats.getLeft() + 1, ats.getRight()));
                    processBuffer.append(after + ";");
                }
                processBuffer.append("}");
                processStr = processBuffer.toString();
            }
        }
        return processStr;
    }

    public static void main(String[] args) throws TransferException {
        System.out.println(new AtTransfer().trans("出生时@全身皮肤@@指趾甲@@脐带@≡黄绿色\\深绿色ª"));
    }
}

