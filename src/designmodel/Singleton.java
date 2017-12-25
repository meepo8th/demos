package designmodel;

/**
 * @author admin
 * @date 2017/12/25
 */
public class Singleton {
    private Singleton() {
    }

    static {
        System.out.println("start");
    }

    static class SingletonHolder {
        private SingletonHolder() {

        }

        private static Singleton singleton;

        static {
            System.out.println("init");
            singleton = new Singleton();
        }

        public static Singleton getInstance() {
            return singleton;
        }
    }

    public static Singleton getInstance() {
        return SingletonHolder.getInstance();
    }

    public static void main(String[] args) {
        System.out.println("123");
        for (int i = 0; i < 1000; i++) {
            getInstance();
        }
    }
}
