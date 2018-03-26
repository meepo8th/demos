package cn.dx.diagnosis.parser.transfer;

import cn.dx.diagnosis.parser.transfer.exception.TransferException;
import cn.dx.diagnosis.parser.transfer.inter.Transfer;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 归一化替换转换器
 */
public class NormalizationReplaceTransfer implements Transfer {
    private static final Map<String, String> replaceMap = new HashMap<>(16);

    static {
        //常规替换
        replaceMap.put(" +", "");
        replaceMap.put("#", "和");
        replaceMap.put("∨", "和（或）");
        replaceMap.put("\\\\", "或者");
        replaceMap.put("\\\\", "或者");
        replaceMap.put("\\*", "伴发");
        replaceMap.put("≡", "");
        //防止全角半角误输,统一转为半角
        replaceMap.put("！", "!");
        replaceMap.put("；", ";");
        replaceMap.put("：", ":");
        //转义C32【】这种防止参与[]转换
        replaceMap.put("(◎.*?C[0-9]{2})\\[([^\\]]*)\\]", "$1【$2】");
        //方括号直接去掉后典型符号生效范围会改变，预先替换到内部
        replaceMap.put("(￥)(\\[)", "$2$1");
        //方括号直接去掉后权重生效范围会改变，预先替换到内部
        replaceMap.put("(\\[)([^\\]]*)(\\])([!|ª|º|★|√])", "$1$4$2$3");
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
