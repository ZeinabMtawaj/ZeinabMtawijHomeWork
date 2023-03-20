package org.example;
import junit.framework.TestCase;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

public class FrequencyTest extends TestCase {


    private int[] randomArray(int size) {
        Random rd = new Random();
        int[] array = new int[size];
        int min = 1;
        int max = 100000;

        for (int i = 0; i < array.length; i++) {
            array[i] = rd.nextInt(max-min+1) + min;
        }
        return array;
    }

    private int[] intStream(int size) {
        Random rd = new Random();
        int min = 1;
        int max = 100000;
        IntStream intStream = rd.ints(size, min, max + 1);
        return intStream.toArray();
    }

    public void testFrequencySeq() {

        int size = 100000000;
        int[] arr = randomArray(size);

        Frequency frequency = new Frequency(arr, 0, arr.length - 1,arr[2]);
        long start = System.currentTimeMillis();
        long freq = frequency.computeSeq();
        long endTimer = System.currentTimeMillis() - start;
        System.out.printf("Sequential Time execution for Random Array of size %d is %d frequency is %d\n", size, endTimer, freq);

    }

    public void testFrequencyPP() {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","7");

        int size = 100000000;
        int[] arr = randomArray(size);

        Frequency frequency = new Frequency(arr, 0, arr.length - 1,arr[2]);
        long start = System.currentTimeMillis();
        ForkJoinPool.commonPool().invoke(frequency);
        long endTimer = System.currentTimeMillis() - start;
        System.out.printf("Parallel Time execution for Random Array of size %d is %d ms frequency is %d\n", size, endTimer, frequency.frequency);
//        assertEquals(15,res);
    }
//
    public void testFrequencyStream() {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","7");

        int size = 100000000;
        int[] arr = randomArray(size);

        Frequency frequency = new Frequency(arr, 0, arr.length - 1,arr[2]);
        long start = System.currentTimeMillis();
        frequency.computeStream();
        long endTimer = System.currentTimeMillis() - start;
        System.out.printf("Parallel Stream Time execution for Random Array of size %d is %d ms frequency is %d\n", size, endTimer, frequency.frequency);
//        assertEquals(15,res);
    }

    public void testFrequencyStreamSeq() {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","7");

        int size = 100000000;
        int[] arr = randomArray(size);

        Frequency frequency = new Frequency(arr, 0, arr.length - 1,arr[2]);
        long start = System.currentTimeMillis();
        frequency.computeStreamSeq();
        long endTimer = System.currentTimeMillis() - start;
        System.out.printf("Parallel Stream Time execution for Random Array of size %d is %d ms frequency is %d\n", size, endTimer, frequency.frequency);
//        assertEquals(15,res);
    }

    public void resource() {
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(ForkJoinPool.commonPool().getParallelism());
    }

}
