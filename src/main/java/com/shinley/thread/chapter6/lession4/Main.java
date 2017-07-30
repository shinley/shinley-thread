package com.shinley.thread.chapter6.lession4;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by shinley on 17-7-29.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<Event> queue=new DelayQueue<>();
        Thread threads[]=new Thread[5];

        for (int i=0; i<threads.length; i++){
            Task task=new Task(i+1, queue);
            threads[i]=new Thread(task);
        }

        for (int i=0; i<threads.length; i++) {
            threads[i].start();
        }

        // 等待线程执行完成
        for (int i=0; i<threads.length; i++) {
            threads[i].join();
        }

        do {
            int counter=0;
            Event event;
            do {
                event=queue.poll();
                if (event!=null) counter++;
            } while (event!=null);
            System.out.printf("At %s you have read %d events\n",new Date(),counter);
            TimeUnit.MILLISECONDS.sleep(500);
        }while (queue.size()>0);
    }
}