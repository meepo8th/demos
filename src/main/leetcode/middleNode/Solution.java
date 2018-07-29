package leetcode.middleNode;


import leetcode.ListNode;

class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode step1 = head;
        ListNode step2 = head;
        while(null!=step2){
            step2=step2.next;
            if(null!=step2){
                step2=step2.next;
                step1=step1.next;
            }
        }
        return step1;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().middleNode(ListNode.initFromArray(new int[]{1, 2, 3, 4, 5})));
        System.out.println(new Solution().middleNode(ListNode.initFromArray(new int[]{1, 2, 3, 4, 5,6})));
    }
}