package cn.dx.diagnosis.parser.transfer;

import cn.dx.diagnosis.parser.transfer.exception.TransferException;
import cn.dx.diagnosis.parser.transfer.inter.Transfer;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 替换转换器
 */
public class ReplaceTransfer implements Transfer {
    private static final Map<String, String> replaceMap = new HashMap<>(8);

    static {
        replaceMap.put("#", "和");
        replaceMap.put("∨", "和（或）");
        replaceMap.put("\\\\", "或者");
        replaceMap.put("\\*", "伴发");
        replaceMap.put("≡", "伴发");
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
