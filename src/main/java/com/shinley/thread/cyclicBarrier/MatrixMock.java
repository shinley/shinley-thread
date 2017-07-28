package com.shinley.thread.cyclicBarrier;

import java.util.Random;

public class MatrixMock {

  private int data[][];

  public MatrixMock(int size, int length, int number) {
    // 初始化构造函数将使用的变量和对象。
    int counter = 0;
    data = new int[size][length];
    Random random = new Random();

    // 用随机数字填充矩阵。每生成一个数字就与要查找的数字对比，如果相等，就增加counter值。
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < length; j++) {
        data[i][j] = random.nextInt(10);
        if (data[i][j] == number) {
          counter++;
        }
      }
    }
  }

  public int[] getRow(int row) {
    if ((row >= 0) && (row < data.length)) {
      return data[row];
    }
    return null;
  }

}
