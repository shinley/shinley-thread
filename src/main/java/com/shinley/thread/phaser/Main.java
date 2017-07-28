package com.shinley.thread.phaser;

import java.util.concurrent.Phaser;
/**
 * 多个线程执行相同的phase, 当所有线程全部到达后,才会执行.
 */
public class Main {
  public static void main(String[] args) {

    // 创建 含3个参与者的 Phaser 对象。
    Phaser phaser = new Phaser(3);

    // 创建3个 FileSearch 对象，每个在不同的初始文件夹里搜索.log扩展名的文件。
    FileSearch system = new FileSearch("C:\\Windows", "log", phaser);
    FileSearch apps = new FileSearch("C:\\Program Files", "log", phaser);
    FileSearch documents = new FileSearch("C:\\Documents And Settings", "log", phaser);

    // 创建并开始一个线程来执行第一个 FileSearch 对象。
    Thread systemThread = new Thread(system, "System");
    systemThread.start();

    // 创建并开始一个线程来执行第二个 FileSearch 对象。
    Thread appsThread = new Thread(apps, "Apps");
    appsThread.start();

    // 创建并开始一个线程来执行第三个 FileSearch 对象。
    Thread documentsThread = new Thread(documents, "Documents");
    documentsThread.start();

    // 等待3个线程们的终结。
    try {
      systemThread.join();
      appsThread.join();
      documentsThread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // 使用isFinalized()方法把Phaser对象的结束标志值写入操控台。
    System.out.println("Terminated: " + phaser.isTerminated());
  }
}
