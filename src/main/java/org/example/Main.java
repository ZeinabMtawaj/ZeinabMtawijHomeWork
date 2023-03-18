package org.example;

import java.util.Random;

public class Main {

    public static int[] createRandom(int n) {
        Random rd = new Random();
        int[] array = new int[n];
        int min = 1;
        int max = 100000;

        for (int i = 0; i < array.length; i++) {
            array[i] = rd.nextInt(max-min+1) + min;
        }
        return array;
    }
    public static void main(String[] args) {

//        First Question
        int[] array = createRandom(10000000);
        Frequency frequency = new Frequency(array, 0, 999, array[2]);
        long startTime = System.currentTimeMillis();
        frequency.compute();
        System.out.println("heeeeere");
        frequency.join();
        long endTime = System.currentTimeMillis();
        System.out.println("Fork/Join Time is "+ (endTime-startTime));



    }

}