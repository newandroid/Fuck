package com.example.concurrency;

/**
 * Created by Administrator on 2018/2/12.
 */

public class CounterThread {
    int value;

    public void show() {
        new Thread(new TheadA()).start();
        new Thread(new TheadB()).start();
//        System.out.println("" + value);
    }

    private synchronized void increment(){
        value++;
    }
    private synchronized void decrement(){
        value--;
    }

    private class TheadA implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                increment();
            }
            System.out.println("---:"+value);
        }
    }

    private class TheadB implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                decrement();
            }
            System.out.println("---:"+value);
        }
    }



}
