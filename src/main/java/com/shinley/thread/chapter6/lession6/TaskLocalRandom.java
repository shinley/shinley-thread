package com.shinley.thread.chapter6.lession6;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by shinley on 17-7-29.
 */
public class TaskLocalRandom implements Runnable {

    public TaskLocalRandom() {
        ThreadLocalRandom.current();
    }

    @Override
    public void run() {
        String name=Thread.currentThread().getName();
        for (int i=0; i<10; i++){
            System.out.printf("%s: %d\n",name,ThreadLocalRandom.current().nextInt(10));
        }
    }
}
