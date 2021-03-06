/**
 * synchronized关键字
 * 对某个对象加锁
 */

package main.java.com.msb.juc.c_004;

public class T {
    private static int count = 10;

    public synchronized void m(){//等同于synchronized(T.class)
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void mm() {
        synchronized (T.class) {
            count--;
        }
    }
}
