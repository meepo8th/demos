package codewars;

public class SolutionFactZeroEnd {
    public static int zeros(int n) {
        int count = 0, mod5, div5;
        for (int i = 5; i <= n; i += 5) {
            div5 = i;
            while (div5 > 0) {
                mod5 = div5 % 5;
                if (mod5 != 0) {
                    break;
                }
                count++;
                div5 = div5 / 5;
            }
        }
        return count;
    }
}