package lintcode;

public class FindMinSortArray {
    /**
     * @param nums: a rotated sorted array
     * @return: the minimum number in the array
     */
    public static int findMin(int[] nums) {
        // write your code here
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return nums[0] < nums[1] ? nums[0] : nums[1];
        }
        int start = 0;
        int end = nums.length - 1;
        int mid = 0;

        while (start <= end) {
            if (start == end) {
                mid = start;
                break;
            } else if (start == end - 1) {
                mid = nums[start] < nums[end] ? start : end;
                break;
            }
            mid = (start + end) / 2;
            if (nums[mid] > nums[end]) {
                start = mid;
            } else {
                end = mid;
            }

        }
        return nums[mid];
    }
}