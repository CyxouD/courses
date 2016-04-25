package com.courses.spalah;

import java.util.Arrays;

/**
 * @author Ievgen Tararaka
 */
public class Volatile {
    public static void main(String[] args) throws InterruptedException {
       volatileRead();
    }

    public static void volatileRead() throws InterruptedException {
        VolatileCounter volatileCounter = new VolatileCounter();
        for (int i = 0; i < 50; i++) {
            Thread thread = new Thread(new VolatileReader(volatileCounter), "worker#" + i);
            thread.start();
        }
        Thread.sleep(30);
        volatileCounter.setCount(new int[]{1, 2, 3});
    }
}

class VolatileCounter {
    private volatile int[] count = {};

    public int[] getCount() {
        return count;
    }

    public void setCount(int[] count) {
        this.count = count;
    }
}

class VolatileReader implements Runnable {
    private VolatileCounter volatileCounter;

    public VolatileReader(VolatileCounter volatileCounter) {
        this.volatileCounter = volatileCounter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100 ; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            System.out.println("COUNTER=" + Arrays.toString(volatileCounter.getCount()) + ", iteration=" + i);
        }
    }
}
