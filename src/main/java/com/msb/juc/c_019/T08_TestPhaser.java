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
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Phaser;

public class T08_TestPhaser {
   static Random r = new Random();
   static MarriagePhaser phaser = new MarriagePhaser();

   static void milliSleep(int milli) {
      try {
         TimeUnit.MILLISECONDS.sleep(milli);
      } catch(InterruptedException e){
         e.printStackTrace();
      }
   }

   public static void main(String[] args) {
      phaser.bulkRegister(7);

      for (int i = 0; i < 5; i++) {

         new Thread(new Person("p"+i)).start();
      }

      new Thread(new Person("bride")).start();
      new Thread(new Person("groom")).start();
   }

   static class MarriagePhaser extends Phaser {
      @Override
      protected boolean onAdvance(int phase, int registeredParties) {
         switch (phase) {
            case 0:
               System.out.println("All arrived! " + registeredParties);
               System.out.println();
               return false;
            case 1:
               System.out.println("All full" + registeredParties);
               System.out.println();
               return false;
            case 2:
               System.out.println("All leave" + registeredParties);
               System.out.println();
               return false;
            case 3:
               System.out.println("Wedding over" + registeredParties);
               return true;
            default:
               return true;
         }
      }
   }

   static class Person implements Runnable {
      String name;
      public Person(String name){
         this.name = name;
      }

      public void arrive() {
         milliSleep(r.nextInt(1000));
         System.out.printf("%s arrive\n", name);
         phaser.arriveAndAwaitAdvance();
      }

      public void eat() {
         milliSleep(r.nextInt(1000));
         System.out.printf("%s full\n", name);
         phaser.arriveAndAwaitAdvance();
      }

      public void leave() {
         milliSleep(r.nextInt(1000));
         System.out.printf("%s leave\n", name);
         phaser.arriveAndAwaitAdvance();
      }

      public void hug() {
         if(name.equals("groom")||name.equals("bride")){
            milliSleep(r.nextInt(1000));
            System.out.printf("%s happy!\n", name);
            phaser.arriveAndAwaitAdvance();
         }else{
            phaser.arriveAndAwaitAdvance();
         }
      }

      @Override
      public void run() {
         arrive();
         eat();
         leave();
         hug();
      }
   }
}
