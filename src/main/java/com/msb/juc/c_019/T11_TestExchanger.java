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

import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;

public class T11_TestExchanger {
   static Exchanger<String> exchanger = new Exchanger<>();

   public static void main(String[] args) {
      new Thread(()->{
         String s = "T1";
         try {
            s = exchanger.exchange(s);
         }catch (InterruptedException e){
            e.printStackTrace();
         }
         System.out.println(Thread.currentThread().getName() + " " +s);
      }, "t1").start();

      new Thread(()->{
         String s = "T2";
         try {
            s = exchanger.exchange(s);
         }catch (InterruptedException e){
            e.printStackTrace();
         }
         System.out.println(Thread.currentThread().getName() + " " +s);
      }, "t2").start();
   }
}
