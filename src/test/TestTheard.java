package test;

/**
 * Created by admin on 2018/1/30.
 */
public class TestTheard implements Runnable {
    static Integer count = 0;
    final static Object lockObj = new Object();

    @Override
    public void run() {
        for (int i = 0; i < 10000000; i++) {
            synchronized (lockObj) {
                count++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestTheard testTheard = new TestTheard();
        Thread t1 = new Thread(testTheard);
        Thread t2 = new Thread(testTheard);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(testTheard.count);
    }
}
