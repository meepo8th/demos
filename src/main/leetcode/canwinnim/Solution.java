package leetcode.canwinnim;

/**
 * 必须是4的余数不为0才可以，不然另一个人总有方法可以拿光
 */
class Solution {
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}