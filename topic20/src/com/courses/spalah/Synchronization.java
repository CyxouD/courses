package com.courses.spalah;

/**
 * @author Ievgen Tararaka
 */
public class Synchronization {
    public static void main(String[] args) {
        simpleSynchronizationMethod();
    }

    public static void monitor() {
        Object obj = new Object(); // любой объект может быть монитором в Java
        synchronized (obj) { // синхронизация происходит по обьекту, который выступает монитором

        }
    }

    public static void simpleSynchronizationBlock() {
        Object lock = new Object();
        for (int i = 1; i <= 10; i++) {
            Thread th = new Thread(new Worker(lock, 3), "worker #" + i);
            th.start();
        }
    }

    public static void simpleSynchronizationMethod() {
        Counter c = new Counter();
        for (int i = 1; i <= 5; i++) {
            Thread th = new Thread(new CounterIncrementer(c), "worker #" + i);
            th.start();
        }
    }
}

class Counter {
    private int counter = 0;
    private static int staticCounter = 0;

    public void increment() { // несинхронизированный метод.
        // Так как идет работа с разделяемыми ресурсами - то это код не потокобезопасен
        System.out.println(Thread.currentThread().getName());
        counter++;
        System.out.println("NEW VALUE=" + counter);
    }

    public synchronized void incrementSynchronized() { // синхронизированный метод
        System.out.println(Thread.currentThread().getName());
        counter++;
        System.out.println("NEW VALUE=" + counter);
    }

    public void incrementSynchronizedBlock() { // несинхронизированный метод, однако результат идентичен синхронизированному
        synchronized (this) {
            System.out.println(Thread.currentThread().getName());
            counter++;
            System.out.println("NEW VALUE=" + counter);
        }
    }

    public static synchronized void synchronizeStatic() { // синхронизированный статический метод
        System.out.println(Thread.currentThread().getName());
        staticCounter++;
        System.out.println("NEW STATIC VALUE=" + staticCounter);
    }

    public static void synchronizeStaticBlock() { // идентичный синхронизированныому статическому методу
        synchronized (Counter.class) {
            System.out.println(Thread.currentThread().getName());
            staticCounter++;
            System.out.println("NEW STATIC VALUE=" + staticCounter);
        }
    }

    public synchronized void anotherSynchronizedMethod() {
        System.out.println("ANOTHER WORK!");
    }
}

class CounterIncrementer implements Runnable {
    private Counter counter;

    public CounterIncrementer(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            counter.incrementSynchronized();
        }
    }
}

class Worker implements Runnable {
    private Object lock;
    private int times;

    public Worker(Object lock, int times) {
        this.lock = lock;
        this.times = times;
    }

    @Override
    public void run() {
        synchronized (lock) { // захватываем монитор на обьекте lock, теперь другие потоки будут ждать пока мы его не отпустим
            for (int i = 1; i <= times; i++) { // делаем полезную работу с
                System.out.println(Thread.currentThread().getName());
                System.out.println("DOING WORK STEP=" + i);
            }
        } // монитор автоматически отпускается после завершения блока синхронизации
    }
}
