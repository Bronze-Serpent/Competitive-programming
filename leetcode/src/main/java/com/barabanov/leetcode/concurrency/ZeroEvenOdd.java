package com.barabanov.leetcode.concurrency;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * 1116
 */
class ZeroEvenOdd
{
    public static void main(String[] args) throws InterruptedException
    {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(5);

        IntConsumer printNumber = System.out::print;

        Thread thread1 = new Thread(() -> {
            try {
                zeroEvenOdd.zero(printNumber);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            try {
                zeroEvenOdd.odd(printNumber);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread2.start();

        Thread thread3 = new Thread(() -> {
            try {
                zeroEvenOdd.even(printNumber);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread3.start();
    }

    private final Object monitor = new Object();
    private final int n;
    private int state = 0;

    public ZeroEvenOdd(int n)
    {
        this.n = n;
    }

    public void zero(IntConsumer printNumber) throws InterruptedException
    {
        for(int i = 0 ; i < n; i++)
        {
            synchronized (monitor)
            {
                while (state != 0) {
                    monitor.wait();
                }
                printNumber.accept(0);
                state = i % 2 == 0 ? 1 : 2;
                monitor.notifyAll();
            }

        }
    }


    public void even(IntConsumer printNumber) throws InterruptedException
    {
        for(int i = 2 ; i <= n; i += 2)
        {
            synchronized (monitor)
            {
                while (state != 2)
                {
                    monitor.wait();
                }
                printNumber.accept(i);
                state = 0;
                monitor.notifyAll();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException
    {
        for(int i = 1 ; i <= n; i += 2)
        {
            synchronized (monitor)
            {
                while (state != 1) {
                    monitor.wait();
                }
                printNumber.accept(i);
                state = 0;
                monitor.notifyAll();
            }
        }
    }
}
