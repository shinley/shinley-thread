package com.shinley.thread.chapter6.lession4;

import java.util.Date;
import java.util.concurrent.DelayQueue;

/**
 *
 */
public class Task implements Runnable {
    private int id;
    private DelayQueue<Event> queue;

    public Task(int id, DelayQueue<Event> queue) {
        this.id = id;
        this.queue = queue;
    }

    @Override
    public void run() {
        Date now = new Date();
        Date delay = new Date();

        // 在当前的时间下, 延迟几秒
        delay.setTime(now.getTime() + (id * 1000));
        System.out.printf("Thread %s: %s\n", id, delay);
        for (int i = 0; i < 100; i++) {
            Event event = new Event(delay);
            queue.add(event);
        }
    }
}
