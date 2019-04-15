package designmodel;

/**
 * 使用内部类完成懒汉式单例，内部类由类加载器记载，jvm类加载器是唯一进程 可以保证单例唯一加载
 *
 * @author admin
 * @date 2017/12/25
 */
public class Singleton {
    private Singleton() {
    }

    public static Singleton getInstance() {
        return SingletonHolder.getInstance();
    }

    static class SingletonHolder {
        private static Singleton singleton;

        static {
            System.out.println("init");
            singleton = new Singleton();
        }

        private SingletonHolder() {

        }

        public static Singleton getInstance() {
            return singleton;
        }
    }

}
