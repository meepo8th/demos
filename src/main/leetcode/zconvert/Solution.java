package leetcode.zconvert;

/**
 * Z字型变换
 */
class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().convert("PAYPALISHIRING", 2));
        System.out.println(new Solution().convert("PAYPALISHIRING", 3));
        System.out.println(new Solution().convert("PAYPALISHIRING", 4));
        System.out.println(new Solution().convert("PAYPALISHIRING", 3).equals("PAHNAPLSIIGYIR"));
        System.out.println(new Solution().convert("PAYPALISHIRING", 4).equals("PINALSIGYAHRPI"));
    }

    public String convert(String s, int numRows) {
        if (1 == numRows) {
            return s;
        }
        char[] rtn = new char[s.length()];
        char[] target = s.toCharArray();
        int rtnTh = 0;
        int circle = numRows * 2 - 2;
        for (int i = 0; i < numRows; i++) {
            int step = circle - i * 2;
            if (step == 0) {
                step = circle;
            }
            for (int pos = i; pos < target.length; pos += circle) {
                rtn[rtnTh++] = target[pos];
                if (step != circle && pos + step < target.length) {
                    rtn[rtnTh++] = target[pos + step];
                }
            }
        }
        return String.valueOf(rtn);
    }
}