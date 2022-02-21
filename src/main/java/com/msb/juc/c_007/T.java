/**
 * synchronized关键字
 * 对某个对象加锁
 */

package main.java.com.msb.juc.c_007;

import com.sun.javaws.IconUtil;

public class T{
    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + " m1 start...");
        try{
            Thread.sleep(10000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m1 end");
    }

    public void m2() {
        try {
            Thread.sleep(5000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m2 ");
    }

    public static void main(String[] args) {
        T t = new T();

        new Thread(t::m1, "t1").start();
        new Thread(t::m2, "t2").start();
    }
}
