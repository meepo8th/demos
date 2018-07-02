package leetcode.lemonadechange;

class Solution {
    public boolean lemonadeChange(int[] bills) {
        int[] lasts = new int[4];
        int price = 5;
        for (int bill : bills) {
            if (!bill(bill, lasts, price)) {
                return false;
            }
            lasts[bill / price - 1] = lasts[bill / price - 1] + 1;
        }
        return true;
    }

    /**
     * 一次交易
     *
     * @param bill
     * @param lasts
     * @param price
     * @return
     */
    private boolean bill(int bill, int[] lasts, int price) {
        int last = bill - price;
        if (last == 0) {
            return true;
        }
        int nowLast;
        for (int i = lasts.length - 1; i >= 0; i--) {
            nowLast = (i + 1) * price;
            if (last >= nowLast && lasts[i] >= last / nowLast) {
                lasts[i] -= last / nowLast;
                last = last % nowLast;
                if (last == 0) {
                    return true;
                }
            }
        }
        return last == 0;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().lemonadeChange(new int[]{10, 10}));
        System.out.println(new Solution().lemonadeChange(new int[]{5, 5, 5, 10, 20}));
        System.out.println(new Solution().lemonadeChange(new int[]{5, 5, 10, 10, 20}));
    }
}