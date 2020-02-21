package offer;

import org.junit.jupiter.api.Test;

public class T02_实现Singleton模式 {

    @Test
    public void test1() {
        Singleton1 singleton1 = Singleton1.getInstance();
        Singleton1 singleton2 = Singleton1.getInstance();
        assert singleton1 == singleton2;
    }

    @Test
    public void test2() {
        Singleton2 singleton1 = Singleton2.getInstance();
        Singleton2 singleton2 = Singleton2.getInstance();
        assert singleton1 == singleton2;
    }

    @Test
    public void test3() {
        Singleton3 singleton1 = Singleton3.getInstance();
        Singleton3 singleton2 = Singleton3.getInstance();
        assert singleton1 == singleton2;
    }

    @Test
    public void test4() {
        Singleton4 singleton1 = Singleton4.getInstance();
        Singleton4 singleton2 = Singleton4.getInstance();
        assert singleton1 == singleton2;
    }

    @Test
    public void test5() {
        Singleton5 singleton1 = Singleton5.getInstance();
        Singleton5 singleton2 = Singleton5.getInstance();
        assert singleton1 == singleton2;
    }
}

/**
 * 懒汉式，简单，但是线程不安全
 */
class Singleton1 {
    private static Singleton1 singleton;

    private Singleton1() {
    }

    public static Singleton1 getInstance() {
        if (singleton == null) singleton = new Singleton1();
        return singleton;
    }
}

/**
 * 懒汉式，线程安全
 */
class Singleton2 {
    private static Singleton2 singleton;

    private Singleton2() {
    }

    public static synchronized Singleton2 getInstance() {
        // 注意，不能锁住一个null对象
        if (singleton == null) singleton = new Singleton2();
        return singleton;
    }
}

/**
 * 【常用】饿汉式，线程安全
 */
class Singleton3 {
    private static Singleton3 singleton = new Singleton3();

    private Singleton3() {
    }

    public static Singleton3 getInstance() {
        return singleton;
    }
}

/**
 * 双重锁，线程安全
 */
class Singleton4 {
    // volatile可以保证可见性，即当一个线程修改了变量，另外的线程能读到这个修改的值
    private volatile static Singleton4 singleton;

    private Singleton4() {
    }

    public static Singleton4 getInstance() {
        if (singleton == null) {
            // 这里要锁住类
            synchronized (Singleton4.class) {
                if (singleton == null) singleton = new Singleton4();
            }
        }
        return singleton;
    }
}

/**
 * 【常用】登记式/静态内部类，线程安全
 */
class Singleton5 {
    private Singleton5() {
    }

    public static final Singleton5 getInstance() {
        return SingletonHolder.SINGLETON;
    }

    private static class SingletonHolder {
        private static final Singleton5 SINGLETON = new Singleton5();
    }
}
