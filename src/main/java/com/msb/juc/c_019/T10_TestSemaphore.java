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

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class T10_TestSemaphore {
   public static void main(String[] args) {
      Semaphore s = new Semaphore(1);

      new Thread(()->{
         try {
            s.acquire();
            System.out.println("T1 running...");
            Thread.sleep(200);
            System.out.println("T1 running...");
         }catch (InterruptedException e){
            e.printStackTrace();
         }finally {
            s.release();
         }
      }).start();

      new Thread(()->{
         try {
            s.acquire();
            System.out.println("T2 running...");
            Thread.sleep(200);
            System.out.println("T2 running...");
         }catch (InterruptedException e){
            e.printStackTrace();
         }finally {
            s.release();
         }
      }).start();
   }
}
