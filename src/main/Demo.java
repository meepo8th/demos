import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

enum Test {
    ONE(1),
    TWO(2);
    private int code;

    Test(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

interface ButttonAction {
    public void click();
}

class Rect {
    //使用原子类保证并发安全
    private final static AtomicInteger useTime = new AtomicInteger(0);

    public Rect() {
        useTime.incrementAndGet();
    }

    public static int getUseTime() {
        return useTime.get();
    }
}

interface Do {
    void doSomething();
}

class MyDo implements Do {

    @Override
    public void doSomething() {
        System.out.println("i'm doing");
    }
}

class YouDo implements Do {

    @Override
    public void doSomething() {
        System.out.println("you're doing");
    }
}

class ItDo implements Do {

    @Override
    public void doSomething() {
        System.out.println("it's doing");
    }
}

class Spider {
    //此处使用阻塞队列，当队列达到最大值时会阻塞，防止内存爆炸，实际使用中需要根据需要设置队列大小或者根据业务情况使用非阻塞队列
    private Queue<String> spiderQueue = new ArrayBlockingQueue<>(10000);

    /**
     * 以一个种子url开始
     *
     * @param url
     */
    public Spider(String url) {
        spiderOne(url);
    }

    /**
     * 以多个种子开始
     *
     * @param urls
     */
    public Spider(Collection<String> urls) {
        spiderAny(urls);
    }

    /**
     * 判断是否爬取过，此处直接返回false，请根据实际情况修改实现
     *
     * @param url
     * @return
     */
    private boolean hasSpider(String url) {
        return false;
    }

    /**
     * 爬虫主工作
     */
    private void spider() {
        String nowUrl;
        while (!spiderQueue.isEmpty()) {
            nowUrl = spiderQueue.poll();
            spiderOne(nowUrl);
        }
    }

    /**
     * 开始,如果已经爬取过，不重复爬取
     *
     * @param url
     */
    public void spiderOne(String url) {
        if (!hasSpider(url)) {
            spiderQueue.offer(url);
        }
    }

    /**
     * 开始
     *
     * @param urls
     */
    private void spiderAny(Collection<String> urls) {
        for (String url : urls) {
            spiderOne(url);
        }
    }

    /**
     * 是否结束爬取
     *
     * @return
     */
    public boolean ended() {
        return spiderQueue.isEmpty();
    }
}

public class Demo {
    public static void main(String args[]) throws IOException {
        System.out.println(10000000);
    }

    public static boolean isEchoWord(String word) {
        for (int i = 0; i < word.length() + 1 / 2; i++) {
            if (word.charAt(i) != word.charAt(word.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    private static void inputWithEcho() throws IOException {
        System.out.println("please input word:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (String input = br.readLine(); !"exit".equals(input); input = br.readLine()) {
            System.out.println(isEchoWord(input));
        }
    }


}