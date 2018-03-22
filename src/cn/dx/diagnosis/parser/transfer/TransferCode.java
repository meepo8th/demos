package cn.dx.diagnosis.parser.transfer;

import cn.dx.diagnosis.parser.transfer.inter.Transfer;

/**
 * 转换编/解码 防止被重复编码/解码
 */
public class TransferCode {
    private TransferCode() {
    }

    private static String ENCODE_CHAR = "";
    private static final String DECODE_CHAR = ":：‡§†[]{}≮≯";

    static {
        for (int i = 0; i < DECODE_CHAR.length(); i++) {
            ENCODE_CHAR += (char) (Character.MAX_VALUE - i);
        }
    }

    /**
     * 编码冒号防止被重复解析
     *
     * @param unCodeStr
     * @return
     */
    public static String encode(String unCodeStr) {
        StringBuilder rtn = new StringBuilder("");
        for (char ch : unCodeStr.toCharArray()) {
            if (DECODE_CHAR.indexOf(ch) >= 0) {
                rtn.append(ENCODE_CHAR.charAt(DECODE_CHAR.indexOf(ch)));
            } else {
                rtn.append(ch);
            }
        }
        return rtn.toString();
    }

    /**
     * 解码冒号
     *
     * @param enCodeStr
     * @return
     */
    public static String decode(String enCodeStr) {
        StringBuilder rtn = new StringBuilder("");
        for (char ch : enCodeStr.toCharArray()) {
            if (ENCODE_CHAR.indexOf(ch) >= 0) {
                rtn.append(DECODE_CHAR.charAt(ENCODE_CHAR.indexOf(ch)));
            } else {
                rtn.append(ch);
            }
        }
        return rtn.toString();
    }
}
