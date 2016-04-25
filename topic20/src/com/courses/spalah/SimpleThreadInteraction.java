package com.courses.spalah;

/**
 * @author Ievgen Tararaka
 */
public class SimpleThreadInteraction {
    public static void main(String[] args) throws InterruptedException {
        Runnable pizzaCookerSenior = new PizzaCooker(10);
        Runnable pizzaCookerJunior = new PizzaCooker(1000);

        Thread seniorWorker = new Thread(pizzaCookerSenior, "senior");
        Thread juniorWorker = new Thread(pizzaCookerJunior, "junior");
        Thread[] workers = {seniorWorker, juniorWorker};

        for (Thread worker : workers) {
            worker.start();
            worker.join(2000);
            worker.interrupt();
            worker.join();
            System.out.printf("-----------\n");
            System.out.println(worker.getName() + " FINISHED HIS WORK!");
            System.out.printf("-----------\n");
        }
    }
}

class PizzaCooker implements Runnable {
    private int restTime;

    public PizzaCooker(int restTime) {
        this.restTime = restTime;
    }

    @Override
    public void run() {
        String[] stuffToDo = {"Make the base",
                "Cook stuffing",
                "Sprinkle with cheese",
                "Bake"};

        for (String toDo : stuffToDo) {
            System.out.println(toDo);
            System.out.println("RESTING: " + restTime);
            try {
                Thread.sleep(restTime);
            } catch (InterruptedException e) {
                System.out.println("I WAS STOPPED !");
                try {
                    System.out.println("IT'S TIME TO COMPLAIN 10 SEC MORE!");
                    Thread.sleep(10000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                return;
            }
        }
    }
}
