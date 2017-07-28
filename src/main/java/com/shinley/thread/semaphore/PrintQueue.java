package com.shinley.thread.semaphore;

import java.util.concurrent.Semaphore;

public class PrintQueue {
  private final Semaphore semaphore;

  public PrintQueue(){
    semaphore=new Semaphore(1);
  }


  public void printJob(Object document) {
    try {
      semaphore.acquire();
      //6.   然后，实现能随机等待一段时间的模拟打印文档的行。
      long duration = (long) (Math.random() * 10);
      System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n",
          Thread.currentThread().getName(), duration);
      Thread.sleep(duration);
      //7.    最后，释放semaphore通过调用semaphore的relaser()方法。
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      semaphore.release();
    }
  }

}
