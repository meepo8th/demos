package demo;


import java.util.Arrays;
import java.util.Random;

/**
 * 进制编码
 *
 * @author Administrator
 */
public class HexadecimalEncoder {
    private String encodeStr = "jklm0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghinopqrstuvwxyz";
    private char[] encodeArray;
    private int encodeLength;
    private int codeSize = 1;
    Random random = new Random();

    public HexadecimalEncoder() {
        init();
    }

    private void init() {
        encodeArray = encodeStr.toCharArray();
        Arrays.sort(encodeArray);
        encodeLength = (encodeArray.length / codeSize) * codeSize;
    }

    public HexadecimalEncoder(String encodeStr) {
        this.encodeStr = encodeStr;
        init();
    }

    public HexadecimalEncoder(String encodeStr, int codeSize) {
        this.encodeStr = encodeStr;
        this.codeSize = codeSize;
        init();
    }

    public HexadecimalEncoder(int codeSize) {
        this.codeSize = codeSize;
        init();
    }

    /**
     * 编码
     *
     * @param unEncode
     * @return
     */
    public String encode(String unEncode) {
        return encode(Long.valueOf(unEncode));
    }

    /**
     * 编码
     *
     * @param unEncode
     * @return
     */
    public String encode(long unEncode) {
        StringBuffer encode = new StringBuffer();
        int size = encodeLength / codeSize;
        while (unEncode > 0) {
            encode.append(encodeArray[(int) (unEncode % size + size * random.nextInt(codeSize))]);
            unEncode = unEncode / size;
        }
        return encode.reverse().toString();
    }

    /**
     * 解码
     *
     * @param unEncode
     * @return
     */
    public String decode2Str(String unEncode) {
        return String.valueOf(decode2Long(unEncode));
    }

    /**
     * 解码
     *
     * @param unEncode
     * @return
     */
    public long decode2Long(String unEncode) {
        long encode = 0;
        int size = encodeLength / codeSize;
        for (int i = 0; i < unEncode.length(); i++) {
            encode = encode * size + indexOf(unEncode.charAt(i)) % size;
        }
        return encode;
    }

    /**
     * 二分查找c在encodeStr中的位置，如果不存在返回0
     *
     * @param c
     * @return
     */
    private int indexOf(char c) {
        int index = 0;
        int low = 0;
        int high = encodeLength - 1;
        int mid;
        while (low <= high) {
            mid = (low + high) / 2;
            if (encodeArray[mid] > c) {
                high = mid - 1;
            } else if (encodeArray[mid] < c) {
                low = mid + 1;
            } else {
                index = mid;
                break;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        HexadecimalEncoder hexadecimalEncoder = new HexadecimalEncoder(10);
        String testStr = "100000001";
        long l1 = System.currentTimeMillis();
        for (int i = 0; i < 3000000; i++) {
            testStr.equals(hexadecimalEncoder.decode2Str(hexadecimalEncoder.encode(testStr)));
        }
        System.out.println(System.currentTimeMillis() - l1);
    }
}
