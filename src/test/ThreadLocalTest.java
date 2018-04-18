import codewars.EncodeResistorColors;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ThreadLocalTest {
    @Test
    public void ThreadLocalTestTest() {
        assertEquals("brown black black gold", EncodeResistorColors.encodeResistorColors("10 ohms"));
    }

    static ThreadLocal<Data> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set(new Data());
        for (int i = 0; i < 100; i++) {
            new TestThread().start();
        }
        System.out.println(Arrays.toString(threadLocal.get().data));

    }

    static class Data {
        String[] data = new String[]{"1", "2", "3"};
    }

    static class TestThread extends Thread {
        @Override
        public void run() {
            threadLocal.set(new Data());
            threadLocal.get().data[0] = Thread.currentThread().getName();
            System.out.println(Arrays.toString(threadLocal.get().data));
        }
    }
}
