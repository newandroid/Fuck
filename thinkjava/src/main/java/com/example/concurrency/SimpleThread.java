package com.example.concurrency;

/**
 * Created by Administrator on 2018/2/12.
 */

public class SimpleThread {

    public static void show() {
        long totalTime = 12 * 1000;

        showMessage("Start a MessageLoop thread");
        long startTime = System.currentTimeMillis();
        Thread t = new Thread(new MessageLoop());
        t.start();
        showMessage("Waiting for MessageLoop thread to finish");

        while (t.isAlive()) {
            try {
                showMessage("Still waiting...");
                t.join(1000);
                if (System.currentTimeMillis() - startTime > totalTime && t.isAlive()) {
                    showMessage("I cann't wait");
                    t.interrupt();
                    t.join();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        showMessage("Finally!");
    }

    private static void showMessage(String msg) {
        String threadName = Thread.currentThread().getName();
        System.out.format("%s:%s%n", threadName, msg);

    }

    private static class MessageLoop implements Runnable {
        String[] srdData;

        public MessageLoop() {
            srdData = new String[]{
                    "Lucy eat milk",
                    "Piano eat tomato",
                    "Simon eat water",
                    "Jack eat snake"
            };
        }


        @Override
        public void run() {
            for (int i = 0; i < srdData.length; i++) {
                try {
                    if (Thread.interrupted()) {
                        System.out.println("Inner thread interrupted!!");
                    }
                    Thread.sleep(4000);


                } catch (InterruptedException e) {
                    System.out.println("I wasn't done!!!");
                }
                showMessage(srdData[i]);
            }
        }
    }
}
