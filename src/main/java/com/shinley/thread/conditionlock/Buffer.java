package com.shinley.thread.conditionlock;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {

  private LinkedList<String> buffer;
  private int maxSize;
  private ReentrantLock lock;
  private Condition lines;
  private Condition space;
  private boolean pendingLines;

  public Buffer(int maxSize) {
    this.maxSize = maxSize;
    buffer = new LinkedList<>();
    lock = new ReentrantLock();       // 生产和消费用的是同一个锁对象
    lines = lock.newCondition();      // 同一个锁下的条件
    space = lock.newCondition();      // 同一个锁下的条件
    pendingLines = true;
  }

  public void insert(String line) {
    lock.lock();
    try {
      while (buffer.size() == maxSize) {  // 如果buffer中达到最大值, 就休眼
        space.await();
      }
      buffer.offer(line);                 // 追加数据
      System.out.printf("%s: Inserted Line: %d\n", Thread.
          currentThread().getName(), buffer.size());
      lines.signalAll();                  // 唤醒消费线程消费
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }

  public String get() {
    String line = null;
    lock.lock();
    try {
      while ((buffer.size() == 0) && (hasPendingLines())) {  // 如果 buffer.size为0 ,但是处于追加状态, 就等待追加完毕
        lines.await();
      }
      if (hasPendingLines()) {
        line = buffer.poll();                 // 获取数据
        System.out.printf("%s: Line Readed: %d\n", Thread.
            currentThread().getName(), buffer.size());

        space.signalAll();                  // 唤醒生产线程生产(追加数据)
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
    return line;
  }

  public void setPendingLines(boolean pendingLines) { // 设置为正在追加的状态
    this.pendingLines = pendingLines;
  }

  public boolean hasPendingLines() {  // 正在追加 或者 buffer.size > 0 都认为有数据
    return pendingLines || buffer.size() > 0;
  }


}
