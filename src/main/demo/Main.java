package demo;

import org.apache.commons.lang3.StringUtils;


public class Main {
    public static void main(String[] args) {
        System.out.println(translate("?"));
    }

    public static long translate(String areaDesc) {
        long days = -1;
        String units = "岁月天";
        int[] unitTrans = new int[]{365, 30, 1};
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
                    if (!"?".equals(areaDesc)) {
                        System.err.println("格式错误：" + areaDesc);
                        break;
                    }
                }

                if (start >= 0 && end >= start) {
                    days += (Integer.valueOf(areaDesc.substring(start, end)) * unitTrans[units.indexOf(areaDesc.substring(end, end + 1))]);
                    start = -1;
                    end = -1;
                }
            }
        }
        return days;
    }

    private static boolean isValidUnit(char c) {
        return "岁月天".indexOf(c) >= 0;
    }

    private static boolean isValidNumber(char c) {
        return "01234567890.-".indexOf(c) >= 0;
    }
}