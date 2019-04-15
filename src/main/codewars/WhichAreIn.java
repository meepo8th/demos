package codewars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WhichAreIn {

    public static String[] inArray(String[] array1, String[] array2) {
        List<String> rtn = new ArrayList<String>();
        for (String str1 : array1) {
            for (String str2 : array2) {
                if (str2.indexOf(str1) >= 0) {
                    rtn.add(str1);
                    break;
                }
            }
        }
        String[] arrays = rtn.toArray(new String[]{});
        Arrays.sort(rtn.toArray(arrays));
        return arrays;
    }
}
