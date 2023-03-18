package org.example;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class Frequency extends RecursiveAction {
    public long frequency =0;
    int arr[], lo, hi,number;

    public Frequency(int[] arr, int lo, int hi, int number) {
        this.arr = arr;
        this.lo = lo;
        this.hi = hi;
        this.number = number;
    }

    public long computeSeq() {
//
        for (int i = lo; i <= hi; ++i) {
            if (arr[i]==number)
                frequency +=1;
        }
        return frequency;
    }

    @Override
    protected void compute() {
        if (hi - lo > 1_000_000) {
            int mid = (lo + hi) / 2;
            Frequency left = new Frequency(arr, lo, mid, number);
            Frequency right = new Frequency(arr, mid + 1, hi, number);
            left.fork();
            right.compute();
            left.join();
            frequency = left.frequency + right.frequency;
        } else {
            frequency =computeSeq();
            }
    }

    public void computeStream() {
        frequency = Arrays.stream(arr).asLongStream().parallel().findAny().stream().count();

    }

    public void computeStreamSeq() {
        frequency = Arrays.stream(arr).asLongStream().findAny().stream().count();

    }
}

