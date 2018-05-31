package demo;

import org.apache.commons.lang3.StringUtils;


public class Main {
    public static void main(String[] args) {
        translate("3");
    }

    public static void translate(String areaDesc) {
        long days = -1;
        if (StringUtils.isNotBlank(areaDesc)) {
            int start = -1;
            int end = -1;
            for (int i = 0; i < areaDesc.length(); i++) {
                if (isValidNumber(areaDesc.charAt(i))) {
                    if (start == -1) {
                        start = i;
                    }
                } else if (isValidUnit(areaDesc.charAt(i))) {
                    if (start >= 0 && end == -1) {
                        end = i;
                    }
                } else {
                    System.err.println("格式错误：" + areaDesc);
                    break;
                }

                if (start >= 0 && end >=start) {
                    System.out.println("number:" + areaDesc.substring(start, end) + ",unit:" + areaDesc.substring(end , end+1));
                    start = -1;
                    end = -1;
                }
            }
        }
    }

    private static boolean isValidUnit(char c) {
        return "岁月天".indexOf(c) >= 0;
    }

    private static boolean isValidNumber(char c) {
        return "01234567890.-".indexOf(c) >= 0;
    }
}