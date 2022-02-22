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

import java.beans.IntrospectionException;
import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class T09_TestReadWriteLock {
   static Lock lock = new ReentrantLock();
   private static int value;

   static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
   static Lock readLock = readWriteLock.readLock();
   static Lock writeLock = readWriteLock.writeLock();

   public static void read(Lock lock) {
      try {
         lock.lock();
         Thread.sleep(1000);
         System.out.println("read over!");
      }catch (InterruptedException e){
         e.printStackTrace();
      }finally {
         lock.unlock();
      }
   }

   public static void write(Lock lock, int v) {
      try {
         lock.lock();
         Thread.sleep(1000);
         value = v;
         System.out.println("read over!");
      }catch (InterruptedException e){
         e.printStackTrace();
      }finally {
         lock.unlock();
      }
   }

   public static void main(String[] args) {
      Runnable readR = ()->read(lock);
      Runnable writeR = ()->write(lock, new Random().nextInt());

      for (int i = 0; i < 18; i++) {
         new Thread(readR).start();
      }
      for (int i = 0; i < 2; i++) {
         new Thread(writeR).start();
      }
   }
}
