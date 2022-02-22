/**
 * reentrantlock用于替代synchronized
 * 由于m1锁定this，只有m1执行完毕的时候，m2才能执行
 *
 * 使用reentrantlock可以完成同样的功能
 * 需要注意的是，必须要手动释放锁
 *
 * 使用reentrantlock可以进行“尝试锁定”trylock
 *
 * 使用reentrantlock还可以调用lockInterruptibly方法
 * 在一个线程等待锁的过程中，可以被打断
 */
package main.java.com.msb.juc.c_019;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T05_ReentrantLock5 extends Thread{
   private static ReentrantLock lock = new ReentrantLock(true);//参数true表示为公平锁
   public void run(){
      for (int i = 0; i < 100; i++) {
         lock.lock();
         try {
            System.out.println(Thread.currentThread().getName()+"getLock");
         }finally {
            lock.unlock();
         }
      }
   }

   public static void main(String[] args) {
      T05_ReentrantLock5 rl = new T05_ReentrantLock5();
      Thread th1 = new Thread(rl);
      Thread th2 = new Thread(rl);
      th1.start();
      th2.start();
   }
}
