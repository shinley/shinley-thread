package com.shinley.thread.chapter6.lession1;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by shinley on 17-7-29.
 */
public class PollTask implements Runnable {

    private ConcurrentLinkedDeque<String> list;

    public PollTask(ConcurrentLinkedDeque<String> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            list.pollFirst();
            list.pollLast();
        }
    }
}
