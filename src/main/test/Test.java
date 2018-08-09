package test;

import java.util.Arrays;
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

    public static void main(String[] args) {
        System.out.println(parseContentOfPosAndLength("62|1@0+", 4L));
        System.out.println(parseContentOfPosAndLength("63|1@0+", 4L));
        System.out.println(parseContentOfPosAndLength("61|1@0+", 4L));

    }
}
