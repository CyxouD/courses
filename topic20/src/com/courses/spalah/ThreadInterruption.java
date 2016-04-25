package com.courses.spalah;

/**
 * @author Ievgen Tararaka
 */
public class ThreadInterruption {
    public static void main(String[] args) throws InterruptedException {
        join();
    }

    public static void sleep() throws InterruptedException {
        Thread.sleep(1000); // заставляет заснуть текущий поток на 1000 миллисекунд
    }

    public static void interruptWithException() throws InterruptedException {
        Runnable interruptedRunnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(50); // нас могут попытаться прервать пока мы спим
                        System.out.println("I'M WORKING HARD!");
                    } catch (InterruptedException e) {
                        System.out.println("HEY! I WAS INTERRUPTED :(");
                        return;
                    }
                }
            }
        };

        Thread t1 = new Thread(interruptedRunnable);
        t1.start();
        Thread.sleep(300); // даем t1 немного поработать
        t1.interrupt(); // прерываем поток в t1
    }

    public static void interruptWithFlag() throws InterruptedException {
        Runnable interruptedRunnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println("I'M WORKING HARD!");
                        if (Thread.interrupted()) throw new InterruptedException(); // проверяем на прерывание
                    } catch (InterruptedException e) {
                        System.out.println("HEY! I WAS INTERRUPTED :(");
                        return;
                    }
                }
            }
        };

        Thread t1 = new Thread(interruptedRunnable);
        t1.start();
        Thread.sleep(1); // даем t1 немного поработать
        t1.interrupt(); // прерываем поток в t1
    }

    public static void checkInterrupted() throws InterruptedException {
        Thread current = Thread.currentThread();
        Runnable interruptedRunnable = new Runnable() {
            @Override
            public void run() {
                while (!current.isInterrupted()) { // ничего не делаем пока основной поток не был прерван
                    System.out.println("DOING NOTHING =(");
                }
                for (int i = 0; i < 10; i++) {
                    System.out.println("I'M WOKING HARD");
                }
            }
        };
        Thread t1 = new Thread(interruptedRunnable);
        t1.start();
        Thread.sleep(1); // даем t1 немного поработать
        current.interrupt(); // прерываем текущий поток
    }

    public static void join() throws InterruptedException {
        Thread current = Thread.currentThread();
        Runnable interruptedRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    current.join(); // ждем пока основной потока закончит свою работу
                } catch (InterruptedException e) {
                    System.out.println("HEY! I WAS INTERRUPTED :(");
                    return;
                }
                for (int i = 0; i < 10; i++) {
                    System.out.println("I'M WOKING HARD");
                }
            }
        };

        Thread t1 = new Thread(interruptedRunnable);
        t1.start();
        Thread.sleep(100); // имитируем бурную деятельность
        System.out.println("FINISH MAIN ACTIVITY");
    }
}
