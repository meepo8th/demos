package leetcode.searchrange;

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] rtn = new int[]{-1, -1};
        if (nums.length > 0) {
            int start = 0;
            int end = nums.length - 1;
            while (start < end) {
                System.out.format("start:%d___end:%d\n", start, end);
                int nowPos = (start + end) / 2;
                if (nums[nowPos] < target) {
                    start = nowPos;
                } else if (nums[nowPos] > target) {
                    end = nowPos;
                } else {
                    System.out.format("%d___%d\n", nums[nowPos], nowPos);
                    break;
                }
                System.out.format("start:%d___end:%d\n", start, end);
            }
        }
        return rtn;
    }

    public int binarySearch(int[] nums, int target) {
        if (nums.length > 0) {
            int start = 0;
            int end = nums.length - 1;
            while (start <= end) {
                int nowPos = (start + end) / 2;
                if (nums[nowPos] < target) {
                    start = nowPos+1;
                } else if (nums[nowPos] > target) {
                    end = nowPos-1;
                } else {
                    return nowPos;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().binarySearch(new int[]{1, 3, 3,3,3,5,7}, 3));
    }
}