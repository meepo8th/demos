package leetcode.maxarea;

/**
 *
 */
class Solution {
    public int maxArea(int[] height) {
        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;
        int valueLeft;
        int valueRight;
        while (left < right) {
            valueLeft = height[left];
            valueRight = height[right];
            maxArea = Math.max(maxArea, Math.min(valueLeft, valueRight) * (right - left));
            if (valueLeft < valueRight) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maxArea(new int[]{1, 2, 3}));
    }
}