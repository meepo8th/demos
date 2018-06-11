package test;

import java.util.Arrays;
import java.util.List;

/**
 * 被三整除验证器
 *
 * @author Administrator
 */
public class MultipleThreeValidator {
    public static boolean multipleByThree(String number) {
        List<String> multipleBase = Arrays.asList(new String[]{"0", "3", "6", "9"});
        if (number.length() == 1) {
            if (multipleBase.contains(number)) {
                return true;
            } else {
                return false;
            }
        } else {
            int sum = 0;
            for (char ch : number.toCharArray()) {
                sum += (ch-48);
            }
            return multipleByThree(String.valueOf(sum));
        }
    }

    public static boolean multipleByThree(Long number) {
        return multipleByThree(String.valueOf(Math.abs(number)));
    }

    public static void main(String[] args){
        System.out.println(multipleByThree("361"));
        System.out.println(multipleByThree(361L));
        System.out.println(multipleByThree("360"));
        System.out.println(multipleByThree(360L));
    }
}
