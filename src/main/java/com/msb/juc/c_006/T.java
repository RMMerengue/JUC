/**
 * synchronized关键字
 * 对某个对象加锁
 */

package main.java.com.msb.juc.c_006;

public class T implements Runnable{
    private  int count = 100;

    public synchronized void run(){
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] atgs) {
        T t = new T();
        for (int i = 0; i < 100; i++) {
            new Thread(t, "THREAD" + i).start();
        }
    }
}
