package leetcode.rand10;

import java.util.Arrays;
import java.util.Random;

/**
 * The rand7() API is already defined in the parent class SolBase.
 * public int rand7();
 *
 * @return a random integer in the range 1 to 7
 */
class Solution {
    static Random random = new Random();

    public int rand7() {
        return random.nextInt(7) + 1;
    }

    public int rand10() {
        int value = -1;
        int sum;
        int[] random2 = new int[]{-1, -1, 1, 2, -1, 3, -1, 4, 5, -1, 6, -1, -1, -1, 7, 8, -1, -1, 9, -1, 10};
        while (value == -1) {
            sum = rand7() * rand7();
            if (sum < 21) {
                value = random2[sum];
            }
        }
        return value;
    }

    int answerRand10() {
        int x = 0;
        do {
            x = (rand7() - 1) * 7 + rand7();
        } while (x > 40);
        return x % 10 + 1;
    }

    public static void main(String[] args) {
        int size = 10000;
        int[] array = new int[11];
        int[] array2 = new int[11];
        Solution solution = new Solution();
        for (int i = 0; i < size; i++) {
            array[solution.rand10()] += 1;
        }
        System.out.println(Arrays.toString(array));

        for (int i = 0; i < size; i++) {
            array2[solution.answerRand10()] += 1;
        }
        System.out.println(Arrays.toString(array2));
    }
}