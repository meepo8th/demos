package codewars;

public class Max {
    public static int sequence(int[] arr) {
        int curSum = 0;
        int maxSum = 0;
        for (int i : arr) {
            curSum += i;
            if (curSum > maxSum) {
                maxSum = curSum;
            }
            if (curSum < 0) {
                curSum = 0;
            }
        }
        if (maxSum == 0 && arr.length > 0) {
            maxSum = Integer.MIN_VALUE;
            for (int i : arr) {
                if (i > maxSum) {
                    maxSum = i;
                }
            }
        }
        return maxSum;
    }
}