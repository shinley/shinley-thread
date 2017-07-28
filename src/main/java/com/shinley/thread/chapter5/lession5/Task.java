package com.shinley.thread.chapter5.lession5;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by shinley on 17-7-28.
 */
public class Task extends RecursiveTask<Integer> {

    private int numbers[];

    private int start, end;

    private int number;

    private TaskManager manager;

    private final static int NOT_FOUND = -1;

    public Task(int numbers[], int start, int end, int number, TaskManager manager) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
        this.number = number;
        this.manager = manager;
    }


    @Override
    protected Integer compute() {
        System.out.println("Task: " + start + ":" + end);
        int ret;
        if (end - start > 10) {
            ret = launchTasks();
        } else {
            ret = lookForNumber();
        }

        return ret;
    }

    private int lookForNumber() {
        for (int i = start; i < end; i++) {
            if (numbers[i] == number) {
                System.out.printf("Task: Number %d found in position %d\n", number, i);
                manager.cancelTasks(this);
                return i;
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return NOT_FOUND;
    }

    private int launchTasks() {
        int mid = (start + end) / 2;
        Task task1 = new Task(numbers, start, mid, number, manager);
        Task task2 = new Task(numbers, mid, end, number, manager);

        manager.addTask(task1);
        manager.addTask(task2);

        task1.fork();
        task2.fork();
        int returnValue;
        returnValue = task1.join();
        if (returnValue != -1) {
            return returnValue;
        }
        returnValue = task2.join();
        return returnValue;
    }

    public void writeCancelMessage() {
        System.out.printf("Task: Canceled task from %d to %d", start, end);
    }

}