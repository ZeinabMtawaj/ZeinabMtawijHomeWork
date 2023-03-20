package org.example;
import junit.framework.TestCase;

import java.util.HashMap;


public class FibonacciTest extends TestCase {

    long n=40;

    public void testFiboPP(){
        long start = System.currentTimeMillis();
        HashMap<Long, Long> memo = new HashMap<Long, Long>();
        Fibonacci fib = new Fibonacci(n, memo);
        long res = fib.compute();
        long end = System.currentTimeMillis()-start;
        System.out.printf("Fibonacci for %d is %d, and parallel execution took %d ms\n",n,res,end);
    }

    public void testFiboSeq(){
        long start = System.currentTimeMillis();
        HashMap<Long, Long> memo = new HashMap<Long, Long>();
        Fibonacci fib = new Fibonacci(n, memo);
        long res = fib.computeSeq();
        long end = System.currentTimeMillis()-start;
        System.out.printf("Fibonacci for %d is %d, and sequential execution took %d ms\n",n,res,end);
    }
}
