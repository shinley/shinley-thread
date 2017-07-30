package com.shinley.thread.chapter6.lession8;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Created by shinley on 17-7-30.
 */
public class Decrementer implements Runnable {

    private AtomicIntegerArray vector;

    public Decrementer(AtomicIntegerArray vector) {
        this.vector=vector;
    }

    @Override
    public void run() {
        for (int i=0; i<vector.length(); i++) {
            vector.getAndDecrement(i);
        }
    }
}
