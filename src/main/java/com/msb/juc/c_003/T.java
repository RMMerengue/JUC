/**
 * synchronized关键字
 * 对某个对象加锁
 */

package main.java.com.msb.juc.c_003;

public class T {
    private int count = 10;

    public synchronized void m(){//等同于再方法的代码执行时要sychronized(this)
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }
}
