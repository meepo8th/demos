package cn.dx.diagnosis.parser.transfer;

import cn.dx.diagnosis.parser.transfer.exception.TransferException;
import cn.dx.diagnosis.parser.transfer.inter.Transfer;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 清理跟概率有关的符号
 */
public class CleanReplaceTransfer implements Transfer {
    private static final Map<String, String> replaceMap = new HashMap<>(10);

    static {
        //常规替换
        replaceMap.put("(n\\+|p\\+|;|!|ª|º|★|√|￥)", "");
        replaceMap.put("@", "、");
        replaceMap.put("、+", "、");
    }

    @Override
    public String trans(String inputString) throws TransferException {
        String rtn = inputString;
        if (StringUtils.isNotBlank(rtn)) {
            for (Map.Entry<String, String> entry : replaceMap.entrySet()) {
                rtn = rtn.replaceAll(entry.getKey(), entry.getValue());
            }
        }
        return rtn;
    }

    @Override
    public boolean canTrans(String inputString) {
        return true;
    }
}
