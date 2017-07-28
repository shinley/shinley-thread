package com.shinley.thread.semaphore;

public class Main {

  public static void main(String[] args) {
    PrintQueue printQueue = new PrintQueue();
    Thread thread[] = new Thread[10];

    for (int i = 0; i < 10; i++) {
      thread[i] = new Thread(new Job(printQueue), "Thread" + i);
    }

    // 最后，开始这10个线程们。
    for (int i = 0; i < 10; i++) {
      thread[i].start();
    }
  }

}
