package com.barabanov.leetcode.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

/***
 * 1114
 */
public class PrintInOrder
{
    public static void main(String[] args) throws InterruptedException
    {
        PrintInOrder printInOrder = new PrintInOrder();

        Runnable firstSout = () -> System.out.print("first");
        Runnable secSout = () -> System.out.print("second");
        Runnable thirdSout = () -> System.out.print("third");

        Thread thread1 = new Thread(() -> {
            try {
                printInOrder.first(firstSout);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            try {
                printInOrder.second(secSout);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread2.start();

        Thread thread3 = new Thread(() -> {
            try {
                printInOrder.third(thirdSout);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread3.start();
    }

    private int printed = 0;
    private final Object monitor = new Object();

    public void first(Runnable printFirst) throws InterruptedException
    {
        synchronized (monitor)
        {
            while (printed != 0)
            {
                monitor.wait();
            }

            printFirst.run();

            printed++;
            monitor.notifyAll();
        }


    }

    public void second(Runnable printSecond) throws InterruptedException
    {
        synchronized (monitor)
        {
            while (printed != 1)
            {
                monitor.wait();
            }

            printSecond.run();

            printed++;
            monitor.notifyAll();
        }

    }

    public void third(Runnable printThird) throws InterruptedException
    {
        synchronized (monitor)
        {
            while (printed != 2)
            {
                monitor.wait();
            }

            printThird.run();

            printed = 0;
            monitor.notifyAll();
        }
    }
}
