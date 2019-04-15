package leetcode;

public class ListNode {
    public int val;
    public ListNode next;

    ListNode(int x) {
        val = x;
    }

    public static ListNode initFromArray(int[] array) {
        ListNode head = new ListNode(array[0]);
        ListNode cur = head;
        for (int i = 1; i < array.length; i++) {
            cur.next = new ListNode(array[i]);
            cur = cur.next;
        }
        return head;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                '}';
    }
}
