package cn.dx.diagnosis.parser.transfer;

import cn.dx.diagnosis.parser.transfer.exception.TransferException;
import cn.dx.diagnosis.parser.transfer.inter.Transfer;
import org.apache.commons.lang.StringUtils;

/**
 * 冒号转换器
 */
public class ColonTransfer implements Transfer {

    private static final String splitUnit = "&|∪";

    @Override
    public String trans(String inputString) throws TransferException {
        String rtn = inputString;
        if (StringUtils.isNotBlank(rtn) && (inputString.contains("：") || inputString.contains(":"))) {
            if (inBrackets(inputString)) {
                StringBuilder buffer = new StringBuilder();
                inputString = inputString.substring(1, inputString.length() - 1);
                String prefix = inputString.split(":|：")[0];
                String[] splits = inputString.split(":|：")[1].split(";|；");
                for (String split : splits) {
                    if (StringUtils.isNotBlank(split)) {
                        String replaceTmp = transUnit(prefix + ":" + split) + ";";
                        buffer.append(replaceTmp);
                    }
                }
                rtn = buffer.toString();
            } else {
                rtn = transUnit(inputString);
            }
        }
        return rtn;
    }

    /**
     * 是否在括号内
     *
     * @param inputString
     * @return
     */
    private boolean inBrackets(String inputString) {
        return (inputString.startsWith("[") && inputString.endsWith("]"))
                || (inputString.startsWith("{") && inputString.endsWith("}"));
    }

    /**
     * 单元转换
     *
     * @param inputString
     * @return
     */
    private String transUnit(String inputString) {
        StringBuilder buffer = new StringBuilder();
        String prefix = inputString.split(":|：")[0];
        String after = inputString.split(":|：")[1];
        int lastPoition = 0;
        for (int i = 0; i < after.length(); i++) {
            int index = splitUnit.indexOf(after.charAt(i));
            if (index >= 0) {
                buffer.append(prefix + ":" + after.substring(lastPoition, i) + splitUnit.charAt(index));
                lastPoition = i + 1;
            } else if (i == after.length() - 1) {
                buffer.append(prefix + ":" + after.substring(lastPoition, i + 1));
            }
        }
        return buffer.toString();
    }


    public static void main(String[] args) throws TransferException {
        System.out.println(new ColonTransfer().trans("[A:B|C|D;E;F;]"));
    }
}

