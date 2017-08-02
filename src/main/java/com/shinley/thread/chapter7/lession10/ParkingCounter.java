package com.shinley.thread.chapter7.lession10;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by shinley on 17-7-30.
 */
public class ParkingCounter extends AtomicInteger {
    private int maxNumber;

    public ParkingCounter(int maxNumber) {
        set(0);
        this.maxNumber = maxNumber;
    }

    /**
     * carIn()操作将实际汽车数与停车场（可容纳的汽车数）的最大值进行比较。如果它们相等，这辆汽车不能进行停车场并返回false值。
     * 否则，它使用以下的原子操作结构：
     * 1.用一个本地变量获取原子对象的值。
     * 2.用一个不同的变量来存储新值。
     * 3.使用compareAndSet()方法尝试将旧值替换成新值。如果这个方法返回true，作为参数传入的旧值是这个变量的值，因此，它使值变化。
     * 随着carIn()方法返回true值，这个操作将以原子方式完成。如果compareAndSet()方法返回false值，
     * 作为参数传入的旧值不是这个变量的值（其他线程已修改它），所以这个操作不能以原子方式完成。这个操作将重新开始，直到它可以以原子方式完成。
     *
     * @return
     */
    public boolean carIn() {
        for (; ; ) {
            int value = get();
            if (value == maxNumber) {
                System.out.printf("ParkingCounter: The parking lot is full.\n");
                return false;
            } else {
                int newValue = value + 1;
                boolean changed = compareAndSet(value, newValue);
                if (changed) {
                    System.out.printf("ParkingCounter: A car has entered.\n");
                    return true;
                }
            }
        }
    }

    /**
     * carOut()方法与carIn()方法类似。你已实现两个Runnable对象，使用carIn()和carOut()来模拟停车的活动。
     * 当你执行这个程序，你可以看出停车场没有克服汽车在停车场的最大值。
     *
     * @return
     */
    public boolean carOut() {
        for (; ; ) {
            int value = get();
            if (value == 0) {
                System.out.printf("ParkingCounter: The parking lot is empty.\n");
                return false;
            } else {
                int newValue = value - 1;
                boolean changed = compareAndSet(value, newValue);
                if (changed) {
                    System.out.printf("ParkingCounter: A car has gone out.\n");
                    return true;
                }
            }
        }
    }
}
