package com.courses.spalah.sorting_visualization;

import edu.princeton.cs.algs4.*;

/**
 * Created by Dima on 25.04.2016.
 */
public class AlgorithmVisualization {
    private static final int VERTICAL = 70;
    private static final int CUTOFF = 12;

    private static final int numberOfRows = 1;

    private static final int N = 100; // number of elements
    private static final int M = 100; // maxNumber

    private MergeBars mergeBars;
    private QuickBars quickBars;

    public AlgorithmVisualization(){
        mergeBars = new MergeBars();
        quickBars = new QuickBars();
    }


    public void start(){
       initDrawings();

        double[] a = new double[N];
        for (int i = 0; i < N; i++) {
            a[i] = (1 + StdRandom.uniform(M)) / (double) M;
        }

        SortThread mergeRunnable = new SortThread("Merge", a);
        SortThread quickRunnable = new SortThread("Quick", a);

        Thread mergeThread = new Thread(mergeRunnable);
        Thread quickThread = new Thread(quickRunnable);

        mergeThread.start();
        //quickThread.start();

        //edu.princeton.cs.algs4.StdDraw.show(0);
       // mergeBars.sort(a);
        //edu.princeton.cs.algs4.StdDraw.show(0);
    }

    private void initDrawings(){
        edu.princeton.cs.algs4.StdDraw.setCanvasSize(800, VERTICAL);
        edu.princeton.cs.algs4.StdDraw.show(0);
        edu.princeton.cs.algs4.StdDraw.square(.5, .5, .5);
        edu.princeton.cs.algs4.StdDraw.setXscale(-1, N);
        edu.princeton.cs.algs4.StdDraw.setYscale(-0.5, numberOfRows);
    }

    class SortThread implements Runnable{
        private String alg;
        private double[] a;

        public SortThread(String alg, double[] a){
            this.alg = alg;
            this.a = a;
        }

        @Override
        public void run() {
            if (alg.equals("Merge")){
                mergeBars.sort(a);
            }
            else if (alg.equals("Quick")){
                quickBars.sort(a);
            }
        }
    }

    public static void main(String[] args){
        AlgorithmVisualization algorithmVisualization = new AlgorithmVisualization();
        algorithmVisualization.start();
    }
}
