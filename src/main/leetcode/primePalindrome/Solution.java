package leetcode.primePalindrome;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        long l1 = System.currentTimeMillis();
        System.out.println(solution.isPrime(100090001));
        System.out.println(System.currentTimeMillis() - l1);
    }

    public int primePalindrome(int N) {
        for (int num = N; num <= 2 * N; num = getNextEchoNumber(num)) {
            if (isPrime(num)) {
                return num;
            }
        }
        return N;
    }

    /**
     * 是否是素数
     *
     * @param num
     * @return
     */
    private boolean isPrime(int num) {
        List<Integer> primes = new ArrayList();
        primes.add(2);
        while (primes.get(primes.size() - 1) <= Math.sqrt(num)) {
            for (int prime : primes) {
                if (num % prime == 0) {
                    return false;
                }
            }
            primes.add(num);
        }
        return true;
    }

    /**
     * 获取下一个回文数
     *
     * @param num
     * @return
     */
    private int getNextEchoNumber(int num) {
        for (int i = num + 1; ; i++) {
            if (isEcho(i)) {
                return i;
            }
        }
    }

    /**
     * 是否是回文数
     *
     * @param i
     * @return
     */
    private boolean isEcho(int i) {
        String str = String.valueOf(i);
        for (int index = 0; index <= str.length() / 2; index++) {
            if (str.charAt(index) != str.charAt(str.length() - 1 - index)) {
                return false;
            }
        }
        return true;
    }
}