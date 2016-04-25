package com.courses.spalah;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ievgen Tararaka
 */
public class RaceConditions {

    public static void main(String[] args) throws InterruptedException {
        int result;
        do {
            Counter c1 = new Counter();
            List<Thread> threadList = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                threadList.add(new CounterThread(String.valueOf(i), 1, c1));
            }
            threadList.forEach(Thread::start);

            Thread.sleep(100);
            result = c1.count;
            System.out.println("RESULT: " + c1.count);
        } while (result == 10);
    }

    public static class Counter {
        private int count = 0;

        public void add(int value) {
            System.out.println("Thread:" + Thread.currentThread().getName());
            this.count = this.count + value;
            System.out.println("Thread:" + Thread.currentThread().getName() + " , count=" + count);
        }

        public void addSynchronized(int value) {
            synchronized (this) { // синхронизация по текущему экземпляру
                System.out.println("Thread:" + Thread.currentThread().getName());
                this.count = this.count + value;
                System.out.println("Thread:" + Thread.currentThread().getName() + " , count=" + count);
            }
        }
    }

    public static class CounterThread extends Thread {
        private int value;
        private Counter counter;

        public CounterThread(String name, int value, Counter counter) {
            super(name);
            this.value = value;
            this.counter = counter;
        }

        @Override
        public void run() {
            counter.add(value);
        }
    }
}
