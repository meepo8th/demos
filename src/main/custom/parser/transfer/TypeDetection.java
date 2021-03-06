package custom.parser.transfer;

import org.apache.commons.lang3.StringUtils;

/**
 * ‡|:类型识别器
 */
public class TypeDetection {
    String[] splitRegexList = new String[]{":", "‡", "§", "-†", "†"};
    String[] splitChars = new String[]{":", "‡", "§", "-†", "†"};
    private String inputString;
    private String rtn;
    private String splitRegex;
    private String splitChar;

    public TypeDetection(String inputString, String rtn) {
        this.inputString = inputString;
        this.rtn = rtn;
    }

    public TypeDetection(String inputString, String rtn, String[] splitRegexList, String[] splitChars) {
        this.inputString = inputString;
        this.rtn = rtn;
        this.splitRegexList = splitRegexList;
        this.splitChars = splitChars;
    }

    public TypeDetection detection() {
        if (StringUtils.isNotBlank(rtn)) {
            for (int i = 0; i < splitRegexList.length; i++) {
                if (inputString.contains(splitRegexList[i])) {
                    splitRegex = splitRegexList[i];
                    splitChar = splitChars[i];
                    break;
                }
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