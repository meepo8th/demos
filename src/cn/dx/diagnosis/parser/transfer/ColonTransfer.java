package cn.dx.diagnosis.parser.transfer;

import cn.dx.diagnosis.parser.transfer.exception.TransferException;
import cn.dx.diagnosis.parser.transfer.inter.Transfer;
import org.apache.commons.lang.StringUtils;

/**
 * 冒号(‡|:)转换器
 */
public class ColonTransfer implements Transfer {

    private static final String SPLIT_UNIT = "&|∪";
    private static String ENCODE_COLON = "";
    private static final String DECODE_COLON = ":：‡§†";

    static {
        for (int i = 0; i < DECODE_COLON.length(); i++) {
            ENCODE_COLON += (char) (Character.MAX_VALUE - i);
        }
    }

    @Override
    public String trans(String inputString) throws TransferException {
        String rtn = inputString;
        String splitRegex = "";
        String splitChar = "";
        TypeDetection typeDetection = new TypeDetection(inputString, rtn).detection();
        splitRegex = typeDetection.getSplitRegex();
        splitChar = typeDetection.getSplitChar();
        if (StringUtils.isNotBlank(splitRegex)) {
            if (inBrackets(inputString)) {
                StringBuilder buffer = new StringBuilder();
                inputString = inputString.substring(1, inputString.length() - 1);
                String prefix = inputString.split(splitRegex)[0];
                String after = inputString.split(splitRegex)[1];
                String[] splits = after.split(";|；");
                for (String split : splits) {
                    if (StringUtils.isNotBlank(split)) {
                        String replaceTmp = transUnit(prefix + splitChar + split, splitRegex, splitChar) + ";";
                        buffer.append(replaceTmp);
                    }
                }
                rtn = buffer.toString();
            } else {
                rtn = transUnit(inputString, splitRegex, splitChar);
            }
        }
        return rtn;
    }

    /**
     * 是否是冒号处理器可以处理的
     *
     * @param inputString
     * @return
     */
    @Override
    public boolean canTrans(String inputString) {
        return StringUtils.isNotBlank(inputString) && (inputString.contains("：") || inputString.contains("‡") || inputString.contains(":") || inputString.contains("†"));
    }

    /**
     * 是否在括号内
     *
     * @param inputString
     * @return
     */
    private boolean inBrackets(String inputString) {
        return (inputString.startsWith("[") && inputString.endsWith("]"))
                || (inputString.startsWith("{") && inputString.endsWith("}"))
                || (inputString.startsWith("≮") && inputString.endsWith("≯"));
    }

    /**
     * 单元转换
     *
     * @param inputString
     * @return
     */
    private String transUnit(String inputString, String splitRegex, String splitChar) {
        StringBuilder buffer = new StringBuilder();
        String prefix = inputString.split(splitRegex)[0];
        String after = inputString.split(splitRegex)[1];
        int lastPoition = 0;
        for (int i = 0; i < after.length(); i++) {
            int index = SPLIT_UNIT.indexOf(after.charAt(i));
            if (index >= 0) {
                buffer.append(prefix + splitChar + after.substring(lastPoition, i) + SPLIT_UNIT.charAt(index));
                lastPoition = i + 1;
            } else if (i == after.length() - 1) {
                buffer.append(prefix + splitChar + after.substring(lastPoition, i + 1));
            }
        }
        return buffer.toString();
    }

    /**
     * 编码冒号防止被重复解析
     *
     * @param unCodeStr
     * @return
     */
    public String encodeColon(String unCodeStr) {
        StringBuilder rtn = new StringBuilder("");
        for (char ch : unCodeStr.toCharArray()) {
            if (DECODE_COLON.indexOf(ch) >= 0) {
                rtn.append(ENCODE_COLON.charAt(DECODE_COLON.indexOf(ch)));
            } else {
                rtn.append(ch);
            }
        }
        return rtn.toString();
    }

    /**
     * 解码冒号
     *
     * @param enCodeStr
     * @return
     */
    public String decodeColon(String enCodeStr) {
        StringBuilder rtn = new StringBuilder("");
        for (char ch : enCodeStr.toCharArray()) {
            if (ENCODE_COLON.indexOf(ch) >= 0) {
                rtn.append(DECODE_COLON.charAt(ENCODE_COLON.indexOf(ch)));
            } else {
                rtn.append(ch);
            }
        }
        return rtn.toString();
    }

    /**
     * ‡|:类型识别器
     */
    private class TypeDetection {
        private String inputString;
        private String rtn;
        private String splitRegex;
        private String splitChar;

        public TypeDetection(String inputString, String rtn) {
            this.inputString = inputString;
            this.rtn = rtn;
        }

        public TypeDetection detection() {
            if (StringUtils.isNotBlank(rtn)) {
                if (inputString.contains("：") || inputString.contains(":")) {
                    splitRegex = ":|：";
                    splitChar = ":";
                } else if (inputString.contains("‡")) {
                    splitRegex = "‡";
                    splitChar = "‡";
                } else if (inputString.contains("§")) {
                    splitRegex = "§";
                    splitChar = "§";
                } else if (inputString.contains("-†")) {
                    splitRegex = "-†";
                    splitChar = "-†";
                } else if (inputString.contains("†")) {
                    splitRegex = "†";
                    splitChar = "†";
                }
            }
            return this;
        }

        public String getSplitRegex() {
            return splitRegex;
        }

        public String getSplitChar() {
            return splitChar;
        }
    }
}

