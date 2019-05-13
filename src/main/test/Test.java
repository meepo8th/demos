package test;

import com.mysql.cj.util.StringUtils;
import org.apache.commons.codec.binary.Hex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Test {
    public static void setRandom100() {
        int[] array = new int[100];
        for (int i = 0; i < 100; i++) {
            array[i] = i + 1;
        }
        for (int i = 0; i < 100; i++) {
            int j = new Random().nextInt(100);
            int tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
        System.out.println(Arrays.toString(array));
    }

    /**
     * @param str
     * @param content
     * @return
     */
    public static Long parseContentOfPosAndLength(String str, Long content) {
        String direct = str.split("@")[1];
        String posAndLength = str.split("@")[0];
        int start = Integer.valueOf(posAndLength.split("\\|")[0]);
        int length = Integer.valueOf(posAndLength.split("\\|")[1]);
        Long rtn = content;
        switch (direct) {
            case "0+":
                rtn <<= start;
                rtn >>>= 64 - length;
                break;
            default:
                break;
        }
        return rtn;
    }

    public static String compressString(String unCompress) {
        StringBuilder compressSb = new StringBuilder();
        int count = 0;
        char last = 0;
        for (char chr : unCompress.toCharArray()) {
            if (chr == last) {
                count++;
            } else {
                addCompress(compressSb, count, last);
                count = 1;
            }
            last = chr;
        }
        addCompress(compressSb, count, last);
        return compressSb.toString();
    }

    private static void addCompress(StringBuilder compressSb, int count, char last) {
        if (count > 1) {
            compressSb.append(count);
        }
        compressSb.append(last);
    }
    public static int hammingWeight(int n) {
        int count=0;
        int now=n;
        for(int i=0;i<31;i++){
            now = now>>>1;
            System.out.println(now);
            System.out.println(Integer.toBinaryString(now));
            count+=(now&0x1);
        }
        return count;
    }
    public int reverseBits(int n) {
        Integer.reverse(n);
        int rtn=0;
        for(int i=0;i<32;i++){
            rtn = rtn|(((n>>>i)&0x1)<<(32-i));
        }
        return rtn;
    }
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(new Integer[]{1,2,3,4}));
        list.add(5);
        Random random = new Random();
        for(int i=0;i<100;i++) {
            System.out.println(random.nextInt(10));
        }
        System.out.println(list);

    }
}
