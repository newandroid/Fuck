package com.example.concurrency;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by css on 2018/7/9.
 */
public class ContainerThread {
    Map<String, String> map = new HashMap<>();

    public void go() {

        new AThread().start();
        new BThread().start();
        for (int i = 0; i < 10; i++) {
            new AThread().start();
        }

    }

    private class AThread extends Thread {
        @Override
        public void run() {
            synchronized (map){
                try {
                    Thread.sleep(1 * 1000);
                    map.put("key","valueA");
                    for (String s : map.keySet()) {
                        System.out.println(map.get(s));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private class BThread extends Thread {
        @Override
        public void run() {
            synchronized (map){
                map.put("key","valueB");
                for (String s : map.keySet()) {
                    System.out.println(map.get(s));
                }
            }

        }
    }


}
