package cn.dx.diagnosis.parser.transfer;

import org.apache.commons.lang.StringUtils;

/**
 * ‡|:类型识别器
 */
public class TypeDetection {
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