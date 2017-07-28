package com.shinley.thread.chapter5.lession4;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * Created by shinley on 17-7-28.
 */
public class Main {
    public static void main(String[] args) {
        int array[] = new int[100];
        Task task = new Task(array, 0, 100);
        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(task);
        pool.shutdown();

        // 等待任务结束.
        try {
            pool.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 检查这个任务或它的子任务是否已经抛出异常
        if (task.isCompletedAbnormally()) {
            System.out.printf("Main: An exception has ocurred\n");
            System.out.printf("Main: %s\n", task.getException());
        }

        // 打印出任务返回的结果
        System.out.printf("Main: Result: %d", task.join());
    }
}
