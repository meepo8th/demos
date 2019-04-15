package demo;

import org.apache.commons.lang3.StringUtils;


public class Main {


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


    public static void main(String[] args) {
        long l1 = System.currentTimeMillis();
        BitMap bitMap = new BitMap(Integer.MAX_VALUE / 2);
        for (int i = 1; i < bitMap.length; i++) {
            try {
                //Assert.check(!bitMap.get(i));
                bitMap.set(i);
                //Assert.check(bitMap.get(i));
            } catch (AssertionError e) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(System.currentTimeMillis() - l1);
        System.out.println(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
    }

    static class BitMap {
        long[] cache;
        long length;

        public BitMap(int length) {
            this.length = length;
            cache = new long[length / 64 + 1];
        }

        public boolean get(int i) {
            return (cache[i / 64] >> i % 64 & 0x1L) == 1;
        }

        public void set(int i) {
            cache[i / 64] = cache[i / 64] | 0x1L << i % 64;
        }
    }

}