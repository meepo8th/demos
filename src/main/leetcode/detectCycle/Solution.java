package leetcode.detectCycle;

import leetcode.ListNode;

public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (null == head) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        ListNode start = head;
        boolean cycled = false;
        while (null != fast.next && null != fast.next.next && null != slow.next) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                cycled = true;
                break;
            }
        }
        if (cycled) {
            while (slow != start) {
                slow = slow.next;
                start = start.next;
            }
            return start;
        }
        return null;
    }
}