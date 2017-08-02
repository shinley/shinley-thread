package com.shinley.thread.chapter7.lession10;

/**
 * Created by shinley on 17-7-30.
 */
public class Sensor2 implements Runnable {

    private ParkingCounter counter;

    public Sensor2(ParkingCounter counter) {
        this.counter=counter;
    }

    @Override
    public void run() {
        counter.carIn();
        counter.carOut();
        counter.carOut();
        counter.carIn();
        counter.carIn();
        counter.carIn();
        counter.carIn();
        counter.carIn();
        counter.carIn();
    }
}
