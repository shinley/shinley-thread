package com.shinley.thread.phaser.simplephase;

import java.util.concurrent.Phaser;

public class MainPhase {

  public static void main(String[] args) {
    Phaser phaser = new Phaser(3);

    Thread thread01 = new Thread(new ThreadRunable(phaser), "thread01");
    thread01.start();

    Thread thread02 = new Thread(new ThreadRunable(phaser), "thread02");
    thread02.start();

    Thread thread03 = new Thread(new ThreadRunable(phaser), "thread03");
    thread03.start();
  }

}

class ThreadRunable implements Runnable {

  Phaser phaser;

  public ThreadRunable(Phaser phaser) {
    this.phaser = phaser;
  }

  @Override
  public void run() {
    for (int i = 0; i < 3; i++) {
      System.out.println("准备执行:" + phaser.getPhase() + " name:" + Thread.currentThread().getName());
      phaser.arriveAndAwaitAdvance();
      System.out.println("执行完毕" + phaser.getPhase() + " name" + Thread.currentThread().getName());
    }
  }
}