package com.shinley.thread.chapter6.lession7;

/**
 * 创建一个Bank类，并实现 Runnable 接口。这个类会模拟从一个账号提款。
 */
public class Bank implements Runnable {

    private Account account;

    public Bank(Account account) {
        this.account=account;
    }

    @Override
    public void run() {
        for (int i=0; i<10; i++){
            account.subtractAmount(1000);
        }
    }
}
