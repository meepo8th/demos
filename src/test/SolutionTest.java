package test;

import lintcode.ListNode;
import lintcode.Solution;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SolutionTest {
    @Test
    public void reverseBetweenTest() {
        assertEquals(new Solution().reverseBetween(new ListNode(new int[]{1, 2, 3, 4, 5, 6}), 2, 4).toString(), new ListNode(new int[]{1, 2, 5, 4, 3, 6}).toString());
    }
}
