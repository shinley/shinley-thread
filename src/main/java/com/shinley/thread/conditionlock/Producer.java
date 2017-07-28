package com.shinley.thread.conditionlock;

public class Producer implements Runnable {

  private FileMock mock;
  private Buffer buffer;

  public Producer(FileMock mock, Buffer buffer) {
    this.mock = mock;
    this.buffer = buffer;
  }

  /**
   * 模拟了从文件中读取数据到buffer中
   */
  @Override
  public void run() {
    buffer.setPendingLines(true);   // 设置正在追加数据中...
    while (mock.hasMoreLines()) {
      String line = mock.getLine();
      buffer.insert(line);          // 往buffer中插入数据
    }
    buffer.setPendingLines(false);  // 设置结束追加数据状态
  }
}
