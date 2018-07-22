package demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Lock {
    static Object lockObj;
    static ReentrantLock reentrantLock = new ReentrantLock();
    static Thread lockTread = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().getId());
            reentrantLock.lock();

            try {
                /**
                 * 休眠10s 持有锁
                 */
                Thread.sleep(10 * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            reentrantLock.unlock();
            System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().getId());
        }

    });


    public static void main(String[] args) throws InterruptedException {
        lockTread.start();
        while (true) {
            if (!reentrantLock.isLocked()) {
                break;
            }
            System.out.println(System.currentTimeMillis());
            Thread.sleep(1000);
        }
    }

}
