package com.shinley.thread.chapter6.lession7;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 当一个线程正在操作一个原子变量时，即使其他线程也想要操作这个变量，类的实现中含有一个检查那步骤操作是否完成的机制。
 * 基本上，操作获取变量的值，改变本地变量值，然后尝试以新值代替旧值。如果旧值还是一样，那么就改变它。
 * 如果不一样，方法再次开始操作。这个操作称为 Compare and Set（校对注：简称CAS，比较并交换的意思）。
 */
public class Account {
    private AtomicLong balance;

    public Account(){
        balance=new AtomicLong();
    }

    public long getBalance() {
        return balance.get();
    }

    public void setBalance(long balance) {
        this.balance.set(balance);
    }

    public void addAmount(long amount) {
        this.balance.getAndAdd(amount);
    }

    public void subtractAmount(long amount) {
        this.balance.getAndAdd(-amount);
    }
}
