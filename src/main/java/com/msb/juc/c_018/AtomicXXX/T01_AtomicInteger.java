/**
 * 解决同样问题的更高效的方法，使用AtomXXX类
 * AtomXXX类本身方法都是原子性，但不能保证多个方法连续调用是原子性的
 */

package main.java.com.msb.juc.c_018.AtomicXXX;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class T01_AtomicInteger {
    AtomicInteger count = new AtomicInteger(0);

    void m() {
        for (int i = 0; i < 10000; i++) {
            count.incrementAndGet();//count++
        }
    }

    public static void main(String[] args) {
        T01_AtomicInteger t = new T01_AtomicInteger();

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t::m, "thread-" + i));
        }

        threads.forEach((o)->o.start());

        threads.forEach((o)->{
            try {
                o.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });
        System.out.println(t.count);
    }

}
