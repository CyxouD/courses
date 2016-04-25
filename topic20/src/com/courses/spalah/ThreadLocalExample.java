package com.courses.spalah;

/**
 * @author Ievgen Tararaka
 */
public class ThreadLocalExample {
    public static void main(String[] args) {
        example();
    }

    public static void example() {
        ThreadLocal<String> threadLocal = new ThreadLocal<String>(){
            @Override
            protected String initialValue() {
                return "some string";
            }
        };
        Thread t1 = new Thread(new MyRunnable(threadLocal), "worker#1");
        t1.start();
        Thread t2 = new Thread(new MyRunnable(threadLocal), "worker#2");
        t2.start();
    }
}

class MyRunnable implements Runnable {
    private ThreadLocal<String> threadLocal;

    public MyRunnable(ThreadLocal<String> threadLocal) {
        this.threadLocal = threadLocal;
    }

    @Override
    public void run() {
        System.out.println("THREAD LOCAL INITIAL=" + threadLocal.get());
        threadLocal.set(Thread.currentThread().getName()); // все равно будет значение для текущего потока
        System.out.println("THREAD LOCAL=" + threadLocal.get());
    }
}
