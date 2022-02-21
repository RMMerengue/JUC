package main.java.com.msb.juc.c_017;

public class T {
    String s1 = "Hello";
    String s2 = "Hello";

    void m1() {
        synchronized (s1){

        }
    }

    void m2() {
        synchronized (s1){

        }
    }
}
