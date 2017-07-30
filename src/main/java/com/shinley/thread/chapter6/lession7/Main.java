package com.shinley.thread.chapter6.lession7;


public class Main {
    public static void main(String[] args) {
        // 创建一个 Account 对象，设置它的余额为 1000。
        Account account = new Account();
        account.setBalance(1000);

        // 创建新的 Company 任务和一个线程运行它。
        Company company = new Company(account);
        Thread companyThread = new Thread(company);

        // 创建一个新的 Bank 任务和一个线程运行它。
        Bank bank = new Bank(account);
        Thread bankThread = new Thread(bank);

        // 在操控台写上账号的初始余额。
        System.out.printf("Account : Initial Balance: %d\n", account.getBalance());

        // 开始线程。
        companyThread.start();
        bankThread.start();

        // 使用 join() 方法等待线程的完结并把账号最终余额写入操控台。
        try {
            companyThread.join();
            bankThread.join();
            System.out.printf("Account : Final Balance: %d\n", account.getBalance());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
