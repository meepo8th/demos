package test;

import codewars.SolutionFactZeroEnd;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class FindMinSortArrayFactZeroEndTest {
  @Test
  public void testZeros() throws Exception {
    assertThat(SolutionFactZeroEnd.zeros(0), is(0));
    assertThat(SolutionFactZeroEnd.zeros(6), is(1));
    assertThat(SolutionFactZeroEnd.zeros(14), is(2));
  }
}