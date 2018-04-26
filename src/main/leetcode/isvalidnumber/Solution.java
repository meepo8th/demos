package leetcode.isvalidnumber;

/**
 * 是否是有效数字
 */
class Solution {
    public boolean isNumber(String s) {
        if (null != s && !s.trim().equals("")) {
            s = s.trim().toLowerCase();
            return isFloat(s) || isENumber(s);
        }
        return false;
    }

    private boolean isENumber(String s) {
        String[] split = s.split("e");
        if ((s + " ").split("e").length > 2) {
            return false;
        }
        String[] nums = new String[2];
        int j = 0;
        for (int i = 0; i < split.length; i++) {
            if (split[i].length() > 0) {
                if (j >= 2) {
                    return false;
                }
                nums[j++] = split[i];
            }
        }
        return j == 2 && isFloat(nums[0]) && isFloat(nums[1]) && !nums[1].contains(".");
    }

    private boolean isFloat(String num) {
        int pointPos = -1;
        for (int i = 0; i < num.length(); i++) {
            char chr = num.charAt(i);
            if ("+-0123456789.".indexOf(chr) < 0) {
                return false;
            }
            if ('.' == chr) {
                if (pointPos >= 0 || num.length() == 1) {
                    return false;
                } else {
                    pointPos = i;
                }
            }
            if ('-' == chr) {
                if (i != 0) {
                    return false;
                }
            }
            if ('+' == chr) {
                if (i != 0) {
                    return false;
                }
            }
        }
        return num.replaceAll("[-|\\+|\\.]", "").length() > 0;
    }


    public static void main(String[] args) {
        System.out.println(new Solution().isNumber("6e6.5"));
    }
}