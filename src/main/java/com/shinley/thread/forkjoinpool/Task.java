package com.shinley.thread.forkjoinpool;

import java.util.List;
import java.util.concurrent.RecursiveAction;

public class Task extends RecursiveAction {

  private static final long serialVersionUID = 1L;
  private List<Product> products;
  private int first;
  private int last;
  private double increment;


  public Task(List<Product> products, int first, int last, double increment) {
    this.products = products;
    this.first = first;
    this.last = last;
    this.increment = increment;
  }

  @Override
  protected void compute() {
    if (last - first < 10) {    // 如果当前任务小于10个元素, 就不再拆分
      updatePrices();
    } else {                    // 当前任务大于10个元素, 继续拆分任务
      int middle = (last + first) / 2;
      System.out.printf("Task: Pending tasks: %s\n", getQueuedTaskCount());
      Task t1 = new Task(products, first, middle + 1, increment);
      Task t2 = new Task(products, middle + 1, last, increment);
      invokeAll(t1, t2);        // 执行所有任务
    }
  }

  private void updatePrices() {
    for (int i = first; i < last; i++) {
      Product product = products.get(i);
      product.setPrice(product.getPrice() * (1 + increment));
    }
  }
}
