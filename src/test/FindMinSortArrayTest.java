import lintcode.FindMinSortArray;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FindMinSortArrayTest {
    @Test
    public void test() {
//        assertEquals(FindMinSortArray.findMin(new int[]{4, 5, 0, 1, 2, 3}), 0);
//        assertEquals(FindMinSortArray.findMin(new int[]{4, 5, 0, 1, 2}), 0);
//        assertEquals(FindMinSortArray.findMin(new int[]{0, 1, 2, 3, 4, 5}), 0);
        assertEquals(FindMinSortArray.findMin(new int[]{1, 2, 3, 4, 5, 0}), 0);
        assertEquals(FindMinSortArray.findMin(new int[]{1}), 1);
    }
}