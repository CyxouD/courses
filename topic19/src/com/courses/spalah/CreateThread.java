package com.courses.spalah;

/**
 * @author Ievgen Tararaka
 */
public class CreateThread {
    public static void main(String[] args) {
        threadSubclass(1);
        threadSubclass(2);
    }

    public static void simpleCreate() {
        Thread thread = new Thread(); // создание потока
        thread.start(); // старт потока (в данном случае поток стартует и сразу завершается)
    }

    public static class MyThread extends Thread { // класс, который екстендит Thread
        private int threadId;

        public MyThread(int id) {
            threadId = id; // даем айдишник потоку
        }

        public void run() { // код который будет исполняется в отдельном потоке
            for (int i = 0; i < 10; i++) {
                System.out.println("MyThread running. id = " + threadId); // выводит на экран id потока
            }
        }
    }

    public static void threadSubclass(int id) {
        MyThread myThread = new MyThread(id); // создание нашего потока
        myThread.start(); // старт потока
    }

    public static void anonymous() {
        Thread thread = new Thread() { // создание анонимного класса
            public void run() {
                System.out.println("Anonymous thread Running");
            }
        };

        thread.start();
    }

    public static class MyRunnable implements Runnable { // имплементим Runnable
        private int id;

        public MyRunnable(int id) {
            this.id = id;
        }

        @Override
        public void run() { // код нашего потока будет выполняться тут
            for (int i = 0; i < 5; i++) {
                System.out.println("Runnable is executing. id = " + id);
            }
        }
    }

    public static void runnable(int id) {
        Thread thread = new Thread(new MyRunnable(id)); // передаем наш Runnable
        thread.start(); // стартуем поток
    }

    public static void commonMistakes() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    // что то едлаем
                }
            }
        }); // создаем анонимный Runnable
        thread.run(); // вызываем run() вместо start()!;
    }

    public static void threadNames() {
        Thread thread = new Thread("my thread") { // даем имя потоку
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(getName() + " . i = " + i); // читаем имя потока
                }
            }
        };
        thread.start();
    }

    public static void currentThread() {
        Thread thread = Thread.currentThread(); // получаем текущий поток
        System.out.println(thread.getName()); // печатаем его имячко
    }
}
