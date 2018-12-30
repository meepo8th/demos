package funny;

import java.util.Arrays;
import java.util.Random;

/**
 * @author admin
 */
public class PiFromMonteCarlo {
    public static double piFromMonteCarlo(long times) {
        double circleCount = 0;
        double allCount = 0;
        Random random = new Random();
        double x;
        double y;
        for (long i = 0; i < times; i++) {
            x = random.nextDouble();
            y = random.nextDouble();
            allCount += 1;
            if (Math.pow(x - 0.5, 2) + Math.pow(y - 0.5, 2) <= 0.25) {
                circleCount += 1;
            }
        }
        return circleCount * 4.0 / allCount;
    }

    public static void main(String[] args) {
        test("1", "2", "3");
        test(new String[]{"a", "b", "c"});
    }

    public static void test(String... abs) {
        System.out.println(Arrays.toString(abs));
    }
}
