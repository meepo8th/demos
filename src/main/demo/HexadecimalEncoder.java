package demo;


import java.math.BigInteger;

/**
 * 进制编码
 *
 * @author Administrator
 */
public class HexadecimalEncoder {
    static char[] encodeStr = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
    static int encodeLength = encodeStr.length;

    /**
     * 编码
     *
     * @param unEncode
     * @return
     */
    public static String encode(String unEncode) {
        return encode(Long.valueOf(unEncode));
    }

    /**
     * 编码
     *
     * @param unEncode
     * @return
     */
    public static String encode(long unEncode) {
        StringBuffer encode = new StringBuffer();
        while (unEncode > 0) {
            encode.append(encodeStr[(int) (unEncode % encodeLength)]);
            unEncode = unEncode / encodeLength;
        }
        return encode.reverse().toString();
    }

    /**
     * 解码
     *
     * @param unEncode
     * @return
     */
    public static String decode2Str(String unEncode) {
        return String.valueOf(decode2Long(unEncode));
    }

    /**
     * 解码
     *
     * @param unEncode
     * @return
     */
    public static long decode2Long(String unEncode) {
        long encode = 0;
        for (int i = 0; i < unEncode.length(); i++) {
            encode = encode * encodeLength + indexOf(unEncode.charAt(i));
        }
        return encode;
    }

    /**
     * 二分查找c在encodeStr中的位置，如果不存在返回0
     *
     * @param c
     * @return
     */
    private static int indexOf(char c) {
        int index = 0;
        int low = 0;
        int high = encodeLength - 1;
        int mid;
        while (low <= high) {
            mid = (low + high) / 2;
            if (encodeStr[mid] > c) {
                high = mid - 1;
            } else if (encodeStr[mid] < c) {
                low = mid + 1;
            } else {
                index = mid;
                break;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        long l1 = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            encode(9999999999L);
        }
        System.out.println(System.nanoTime() - l1);
        long time = System.currentTimeMillis();
        l1 = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            encode(time);
        }
        System.out.println(System.nanoTime() - l1);
        String unDecode = encode(9999999999L);
        l1 = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            decode2Long(unDecode);
        }
        System.out.println(System.nanoTime() - l1);
        unDecode = encode(time);
        l1 = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            decode2Long(unDecode);
        }
        System.out.println(System.nanoTime() - l1);

    }
}
