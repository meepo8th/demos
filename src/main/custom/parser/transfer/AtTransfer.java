package custom.parser.transfer;

import custom.parser.bean.Ats;
import custom.parser.transfer.exception.TransferException;
import custom.parser.transfer.inter.Transfer;
import org.apache.commons.lang3.StringUtils;

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

    /**
     * 是否是部位处理器可以处理的
     *
     * @param inputString
     * @return
     */
    @Override
    public boolean canTrans(String inputString) {
        return StringUtils.isNotBlank(inputString) && inputString.contains("@");
    }
}

