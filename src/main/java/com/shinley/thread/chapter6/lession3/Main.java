package com.shinley.thread.chapter6.lession3;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by shinley on 17-7-29.
 */
public class Main {
    public static void main(String[] args) {
        PriorityBlockingQueue<Event> queue = new PriorityBlockingQueue<>();
        Thread taskThreads[] = new Thread[5];

        for (int i = 0; i < taskThreads.length; i++) {
            Task task = new Task(i, queue);
            taskThreads[i] = new Thread(task);
        }

        // 启动前面创建的线程
        for (int i = 0; i < taskThreads.length; i++) {
            taskThreads[i].start();
        }

        // 使用join()方法，等待这5个线程的结束。
        for (int i = 0; i < taskThreads.length; i++) {
            try {
                taskThreads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 将列队真实大小和存储在它里面的事件写入到控制台。使用poll()方法从队列中取出事件。
        System.out.printf("Main: Queue Size: %d\n", queue.size());
        for (int i = 0; i < taskThreads.length * 1000; i++) {
            Event event = queue.poll();
            System.out.printf("Thread %s: Priority %d\n", event.getThread(), event.getPriority());
        }

        // 将队列最后的大小写入到控制台。
        System.out.printf("Main: Queue Size: %d\n", queue.size());
        System.out.printf("Main: End of the program\n");
    }
}
