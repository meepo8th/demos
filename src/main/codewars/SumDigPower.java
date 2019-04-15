package codewars;

import java.util.ArrayList;
import java.util.List;

class SumDigPower {
    public static long[][] cache = new long[][]{
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {pow(1, 2), pow(2, 2), pow(3, 2), pow(4, 2), pow(5, 2), pow(6, 2), pow(7, 2), pow(8, 2), pow(9, 2)},
            {pow(1, 3), pow(2, 3), pow(3, 3), pow(4, 3), pow(5, 3), pow(6, 3), pow(7, 3), pow(8, 3), pow(9, 3)},
            {pow(1, 4), pow(2, 4), pow(3, 4), pow(4, 4), pow(5, 4), pow(6, 4), pow(7, 4), pow(8, 4), pow(9, 4)},
            {pow(1, 5), pow(2, 5), pow(3, 5), pow(4, 5), pow(5, 5), pow(6, 5), pow(7, 5), pow(8, 5), pow(9, 5)},
            {pow(1, 6), pow(2, 6), pow(3, 6), pow(4, 6), pow(5, 6), pow(6, 6), pow(7, 6), pow(8, 6), pow(9, 6)},
            {pow(1, 7), pow(2, 7), pow(3, 7), pow(4, 7), pow(5, 7), pow(6, 7), pow(7, 7), pow(8, 7), pow(9, 7)},
            {pow(1, 8), pow(2, 8), pow(3, 8), pow(4, 8), pow(5, 8), pow(6, 8), pow(7, 8), pow(8, 8), pow(9, 8)},
            {pow(1, 9), pow(2, 9), pow(3, 9), pow(4, 9), pow(5, 9), pow(6, 9), pow(7, 9), pow(8, 9), pow(9, 9)},
            {pow(1, 10), pow(2, 10), pow(3, 10), pow(4, 10), pow(5, 10), pow(6, 10), pow(7, 10), pow(8, 10), pow(9, 10)},
            {pow(1, 11), pow(2, 11), pow(3, 11), pow(4, 11), pow(5, 11), pow(6, 11), pow(7, 11), pow(8, 11), pow(9, 11)},
            {pow(1, 12), pow(2, 12), pow(3, 12), pow(4, 12), pow(5, 12), pow(6, 12), pow(7, 12), pow(8, 12), pow(9, 12)},
            {pow(1, 13), pow(2, 13), pow(3, 13), pow(4, 13), pow(5, 13), pow(6, 13), pow(7, 13), pow(8, 13), pow(9, 13)},
            {pow(1, 14), pow(2, 14), pow(3, 14), pow(4, 14), pow(5, 14), pow(6, 14), pow(7, 14), pow(8, 14), pow(9, 14)},
            {pow(1, 15), pow(2, 15), pow(3, 15), pow(4, 15), pow(5, 15), pow(6, 15), pow(7, 15), pow(8, 15), pow(9, 15)},
            {pow(1, 16), pow(2, 16), pow(3, 16), pow(4, 16), pow(5, 16), pow(6, 16), pow(7, 16), pow(8, 16), pow(9, 16)},
            {pow(1, 17), pow(2, 17), pow(3, 17), pow(4, 17), pow(5, 17), pow(6, 17), pow(7, 17), pow(8, 17), pow(9, 17)},
            {pow(1, 18), pow(2, 18), pow(3, 18), pow(4, 18), pow(5, 18), pow(6, 18), pow(7, 18), pow(8, 18), pow(9, 18)},
            {pow(1, 19), pow(2, 19), pow(3, 19), pow(4, 19), pow(5, 19), pow(6, 19), pow(7, 19), pow(8, 19), pow(9, 19)}
    };

    public static long pow(long a, long b) {
        long rtn = 1;
        if (a == 0) {
            return 0;
        }
        if (b == 0) {
            return 1;
        }
        for (int i = 0; i < b; i++) {
            rtn *= a;
        }
        return rtn;
    }

    public static List<Long> sumDigPow(long a, long b) {
        return (calculateList(a, b));
    }

    private static List<Long> calculateList(long a, long b) {
        List<Long> resultList = new ArrayList<Long>();
        for (long i = a; i < b; i++) {
            if (isEureka(i)) {
                resultList.add(i);
            }
        }
        return (resultList);
    }

    private static boolean isEureka(long number) {
        String longString = String.valueOf(number);
        long sum = 0;
        for (int i = 0; i < longString.length(); i++) {
            long digit = Long.parseLong(String.valueOf(longString.charAt(i)));
            sum += Math.pow(digit, i + 1);
        }
        return (sum == number);
    }

    public static void main(String args[]) {
        Long l1 = System.currentTimeMillis();
        System.out.println(sumDigPow(0, 6000000));
        System.out.println(System.currentTimeMillis() - l1);
    }

}