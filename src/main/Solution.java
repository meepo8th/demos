class Solution {
    public String convert(String s, int numRows) {
        String rtn = "";

        return rtn;
    }

    public Number get3() {
        return new Number() {
            @Override
            public int intValue() {
                return 3;
            }

            @Override
            public long longValue() {
                return 3;
            }

            @Override
            public float floatValue() {
                return 3;
            }

            @Override
            public double doubleValue() {
                return 3;
            }
        };
    }

    public static void main(String[] args) {
        System.out.println(new Solution().get3().intValue());
    }
}