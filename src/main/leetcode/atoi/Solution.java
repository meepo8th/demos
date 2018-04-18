package leetcode.atoi;

/**
 * atoi,如果超过了int范围就取最近的值，非法输入返回0
 */
public class Solution {
    public int myAtoi(String str) {
        int sign = 1;
        int start = 0;
        char[] chrArray = str.trim().toCharArray();
        if (chrArray.length == 0) return 0;
        if (chrArray[0] == '-') {
            sign = -1;
            start = 1;
        } else if (chrArray[0] == '+') {
            sign = 1;
            start = 1;
        }
        long tmp = 0;
        for (int i = start; chrArray.length > start && i < chrArray.length; i++) {
            if (chrArray[i] >= '0' && chrArray[i] <= '9') {
                tmp = (tmp * 10 + chrArray[i] - '0');
                if (tmp >= Integer.MAX_VALUE) {
                    break;
                }
            } else {
                break;
            }
        }
        long result = tmp * sign;
        if (result > Integer.MAX_VALUE) {
            result = Integer.MAX_VALUE;
        } else if (result < Integer.MIN_VALUE) {
            result = Integer.MIN_VALUE;
        }
        return (int) result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().myAtoi("-2147483648"));
        System.out.println(2 ^ 2 ^ 5);
    }
}