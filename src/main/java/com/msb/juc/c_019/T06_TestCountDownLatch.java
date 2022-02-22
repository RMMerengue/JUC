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

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

public class T06_TestCountDownLatch extends Thread{
   public static void main(String[] args) {

   }

   private static void usingCountDownLatch() {
      Thread[] threads = new Thread[100];
      CountDownLatch latch = new CountDownLatch(threads.length);

      for (int i = 0; i < threads.length; i++) {
         threads[i] = new Thread(()->{
            int result = 0;
            for (int j = 0; j < 10000; j++) {
               result += j;
            }
            latch.countDown();
         });
      }

      for (int i = 0; i < threads.length; i++) {
         threads[i].start();
      }

      try {
         latch.await();
      }catch (InterruptedException e){
         e.printStackTrace();
      }
      System.out.println("end latch");
   }
   
   private static void usingJoin() {
      Thread[] threads = new Thread[100];
      for (int i = 0; i < threads.length; i++) {
         threads[i] = new Thread(()->{
            int result = 0;
            for (int j = 0; j < 10000; j++) {
               result += j;
            }
         });
      }

      for (int i = 0; i < threads.length; i++) {
         threads[i].start();
      }

      for (int i = 0; i < threads.length; i++) {
         try {
            threads[i].join();
         }catch (InterruptedException e){
            e.printStackTrace();
         }
      }
   }
}
