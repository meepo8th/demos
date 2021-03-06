import codewars.SumOfK;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SumOfKTest {

    public static String transAngelValue(int nowValue) {
        String rtn = "";
        int[] unit = new int[]{3600, 60, 1};
        String[] units = new String[]{"°", "′", "″"};

        for (int i = 0; i < 3; i++) {
            int cur = nowValue / unit[i];
            nowValue = nowValue % unit[i];
            if (cur > 0) {
                rtn += ("" + cur + units[i]);
            }
        }
        return rtn;
    }

    public static void main(String args[]) {
        System.out.println(transAngelValue(7223));
    }

    @Test
    public void BasicTests1() {
        System.out.println("****** Basic Tests small numbers******");
        List<Integer> ts = new ArrayList<>(Arrays.asList(50, 55, 56, 57, 58));
        int n = SumOfK.chooseBestSum(163, 3, ts);
        assertEquals(163, n);
        ts = new ArrayList<>(Arrays.asList(50));
        Integer m = SumOfK.chooseBestSum(163, 3, ts);
        assertEquals(null, m);
        ts = new ArrayList<>(Arrays.asList(91, 74, 73, 85, 73, 81, 87));
        n = SumOfK.chooseBestSum(230, 3, ts);
        assertEquals(228, n);
    }
}
















