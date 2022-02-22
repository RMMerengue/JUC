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

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class T07_TestCyclicBarrier{
   public static void main(String[] args) {
      CyclicBarrier barrier = new CyclicBarrier(20, new Runnable() {
         @Override
         public void run() {
            System.out.println("Ready, start!");
         }
      });

      for (int i = 0; i < 100; i++) {
         new Thread(()->{
            try{
               barrier.await();
            }catch (InterruptedException e){
               e.printStackTrace();
            }catch (BrokenBarrierException e){
               e.printStackTrace();
            }
         }).start();
      }


   }
}
