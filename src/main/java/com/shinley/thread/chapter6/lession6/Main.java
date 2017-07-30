package com.shinley.thread.chapter6.lession6;

/**
 * Created by shinley on 17-7-29.
 */
public class Main {
    public static void main(String[] args) {
        Thread threads[]=new Thread[3];
        for (int i=0; i<3; i++) {
            TaskLocalRandom task=new TaskLocalRandom();
            threads[i]=new Thread(task);
            threads[i].start();
        }

    }
}
