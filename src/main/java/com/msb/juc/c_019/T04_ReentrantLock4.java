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

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T04_ReentrantLock4 {
    public static void main(String[] args) {
       Lock lock = new ReentrantLock();

       Thread t1 = new Thread(()->{
           try {
               lock.lock();
               System.out.println("t1 start");
               TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
           }catch (InterruptedException e){
               System.out.println("interrupted!");
           }finally {
               lock.unlock();
           }
       });
       t1.start();

       Thread t2 = new Thread(()->{
           try {
               lock.lockInterruptibly();
               System.out.println("t2 start");
               TimeUnit.SECONDS.sleep(5);
               System.out.println("t2 end");
           } catch (InterruptedException e){
               System.out.println("interrupted");
           }finally {
               lock.unlock();
           }
       });
       t2.start();

       try {
           TimeUnit.SECONDS.sleep(1);
       }catch (InterruptedException e){
           e.printStackTrace();
       }
       t2.interrupt();
    }
}
