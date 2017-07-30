package com.shinley.thread.chapter6.lession8;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * CAS(compare-and-swap)操作为并发操作对象的提供更好的性能，CAS操作通过以下3个步骤来实现对变量值得修改：
 * 1.获取当前内存中的变量的值
 * 2.用一个新的临时变量(temporal variable)保存改变后的新值
 * 3.如果当前内存中的值等于变量的旧值，则将新值赋值到当前变量；否则不进行任何操作
 */
public class Incrementer implements Runnable {

    private AtomicIntegerArray vector;

    public Incrementer(AtomicIntegerArray vector) {
        this.vector=vector;
    }

    @Override
    public void run() {
        for (int i=0; i<vector.length(); i++){
            vector.getAndIncrement(i);
        }
    }
}
