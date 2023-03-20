package org.example;


import java.util.concurrent.RecursiveTask;
import java.util.HashMap;
public class Fibonacci extends RecursiveTask<Long> {
    final long n;
    private final HashMap<Long, Long> memo;

    public Fibonacci(long n, HashMap<Long, Long> memo ) {
        this.n = n;
        this.memo = memo;
    }


    public Long compute() {

        if (memo.containsKey(n)) {
            return memo.get(n);
        }


        if(n > 20) {

            Fibonacci f1 = new Fibonacci(n - 1, memo);
            f1.fork();
            Fibonacci f2 = new Fibonacci(n - 2, memo);
            long result =  f2.compute() + f1.join();
            memo.put(n, result);
            return result;
        }else{
            return computeSeq();
        }
    }

    public long computeSeq() {
        if (n <= 1)
            return n;
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        Fibonacci f1 = new Fibonacci(n - 1, memo);
        Fibonacci f2 = new Fibonacci(n - 2, memo);
        long result = f2.computeSeq() + f1.computeSeq();
        memo.put(n, result);
        return result;
    }

}
