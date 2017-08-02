package com.shinley.thread.chapter7.lession2;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 你已经实现了MyPriorityTask类，（作为一个任务）它实现了Runnable接口和Comparable接口，它被存储在优先级队列中。
 * 这个类有一个Priority属性，用来存储任务的优先级。如果一个任务的这个属性有更高的值，它将被更早的执行。
 * compareTo()方法决定任务在优先级列队中的顺序。在Main类，你提交8个不同优先级的任务给执行者。
 * 你提交给执行者的第一个任务将第一个被执行。由于执行者闲置的，正在等待任务被执行，当第一个任务到达执行者时，执行者立即执行它们。
 * 你已经创建有2个执行线程的执行者，所以，前两个任务将第一个被执行。然后，剩下的任务将按它们的优先级来执行。
 */
public class Main {
    public static void main(String[] args) {
        // 创建了两个执行线程的执行者
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 1,
                TimeUnit.SECONDS, new PriorityBlockingQueue<Runnable>());

        for (int i = 0; i < 4; i++) {
            MyPriorityTask task = new MyPriorityTask("Task " + i, i);
            executor.execute(task);
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 4; i < 8; i++) {
            MyPriorityTask task = new MyPriorityTask("Task " + i, i);
            executor.execute(task);
        }

        executor.shutdown();

        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Main: End of the program.\n");
    }
}
