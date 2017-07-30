package com.shinley.thread.chapter6.lession7;

/**
 * 创建一个公司类，名为 并实现 Runnable 接口。这个类会模拟公司付款。
 */
public class Company implements Runnable {

    private Account account;

    public Company(Account account) {
        this.account=account;
    }

    @Override
    public void run() {
        for (int i=0; i<10; i++){
            account.addAmount(1000);
        }
    }
}
