package lintcode;

/**
 * Created by admin on 2017/9/28.
 */
public class ListNode {
    int val;
    ListNode next;

    public ListNode(int x) {
        val = x;
        next = null;
    }

    public ListNode() {
    }

    public ListNode(int[] array) {
        ListNode pre = null;
        for (int i = 0; i < array.length; i++) {
            if (null == pre) {
                val = array[i];
                pre = this;
            } else {
                pre.next = new ListNode();
                pre.next.val = array[i];
                pre = pre.next;
            }
        }
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
