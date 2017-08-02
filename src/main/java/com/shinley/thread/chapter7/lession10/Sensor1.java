package com.shinley.thread.chapter7.lession10;

/**
 * Created by shinley on 17-7-30.
 */
public class Sensor1 implements Runnable {
    private ParkingCounter counter;

    public Sensor1(ParkingCounter counter) {
        this.counter=counter;
    }

    @Override
    public void run() {
        counter.carIn();
        counter.carIn();
        counter.carIn();
        counter.carIn();
        counter.carOut();
        counter.carOut();
        counter.carOut();
        counter.carIn();
        counter.carIn();
        counter.carIn();
    }
}
