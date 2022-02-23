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
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

 public class T12_TestLockSupport {
   public static void main(String[] args) {
      Thread t = new Thread(()->{
         for (int i = 0; i < 10; i++) {
            System.out.println(i);
            if(i == 5) {
                LockSupport.park();
            }
            try {
               TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
               e.printStackTrace();
            }
         }
      });
      t.start();

      try {
          TimeUnit.SECONDS.sleep(8);
      }catch (InterruptedException e){
          e.printStackTrace();
      }
      System.out.println("after 8 seconds");
      LockSupport.unpark(t);
   }
}
