package com.courses.spalah;

/**
 * @author Ievgen Tararaka
 */
public class ThreadCommunication {
    public static void main(String[] args) throws InterruptedException {
        missedSignal();
    }

    public static void waitNotify() throws InterruptedException {
        Object obj = new Object();
        Thread thread = new Thread(new WaitingWorker(obj), "worker#1");
        thread.start();

        Thread.sleep(1000);
        System.out.println("MAIN THREAD SLEEP FOR 1 SEC");

        synchronized (obj) {
            obj.notify();
            Thread.sleep(2000);
            System.out.println("SLEEP AFTER NOTIFY 2 SEC");
        }
    }

    public static void missedSignal() throws InterruptedException {
        Object obj = new Object();
        Thread thread = new Thread(new MissWaitingWorker(obj), "miss waiting worker#1");
        thread.start();

        Thread.sleep(2000);
        System.out.println("MAIN THREAD SLEEP FOR 2 SEC");

        synchronized (obj) {
            obj.notify();
            Thread.sleep(2000);
            System.out.println("SLEEP AFTER NOTIFY 2 SEC");
        }
    }

    public static void waitNotifyAll() throws InterruptedException {
        Object obj = new Object();

        Thread thread1 = new Thread(new WaitingWorker(obj), "worker#1");
        thread1.start();
        Thread thread2 = new Thread(new WaitingWorker(obj), "worker#2");
        thread2.start();
        Thread thread3 = new Thread(new WaitingWorker(obj), "worker#3");
        thread3.start();

        Thread.sleep(1000);
        System.out.println("MAIN THREAD SLEEP FOR 1 SEC");

        synchronized (obj) {
            obj.notifyAll();
            Thread.sleep(2000);
            System.out.println("SLEEP AFTER NOTIFY 2 SEC");
        }
    }
}

class Signal {
    private boolean workDone;

    public synchronized boolean isWorkDone() {
        return workDone;
    }

    public synchronized void setWorkDone(boolean workDone) {
        this.workDone = workDone;
    }
}

class SignalWorker implements Runnable {
    private Signal signal; // сигнал для общения между потоками

    @Override
    public void run() {
        while (!signal.isWorkDone()) {
            // ничего не делаем, но расходуем процессорное время =(
        }
    }
}

class MissWaitingWorker extends WaitingWorker {
    public MissWaitingWorker(Object object) {
        super(object);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " WAITING IN NOT SYNCHRONIZED BLOCK");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (object) {
            try {
                object.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis() + " " + Thread.currentThread().getName() + " DO MY WORK");
        }
    }
}

class WaitingWorker implements Runnable {
    public Object object;

    public WaitingWorker(Object object) {
        this.object = object;
    }

    @Override
    public void run() {
        synchronized (object) {
            System.out.println(Thread.currentThread().getName() + " WAITING");
            try {
                object.wait();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis() + " " + Thread.currentThread().getName() + " DO MY WORK");
        }
    }
}