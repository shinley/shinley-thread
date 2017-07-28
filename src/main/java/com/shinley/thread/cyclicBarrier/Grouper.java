package com.shinley.thread.cyclicBarrier;

/**
 * 现在，实现一个类来计算数字在这个矩阵里出现的总数。
 * 它使用储存了矩阵中每行里数字出现次数的 Results 对象来进行运算。
 * 创建一个类，名为 Grouper 并一定实现 Runnable 接口.
 */
public class Grouper implements Runnable {

  // 声明一个私有 Results 属性，名为 results。
  private Results results;

  // 实现类的构造函数，并初始化 Results 属性。
  public Grouper(Results results) {
    this.results = results;
  }

  // 实现 run() 方法，用来计算结果array里数字出现次数的总和。
  @Override
  public void run() {

    // 声明一个 int 变量并写在操控台写一条信息表明开始处理了。
    int finalResult = 0;
    System.out.printf("Grouper: Processing results...\n");

    // 使用 results 对象的 getData() 方法来获得每行数字出现的次数。
    // 然后，处理array的全部元素，把每个元素的值加给 finalResult 变量。
    int data[] = results.getData();
    for (int number : data) {
      finalResult += number;
    }

    // 在操控台打印结果。
    System.out.printf("Grouper: Total result: %d.\n", finalResult);
  }
}