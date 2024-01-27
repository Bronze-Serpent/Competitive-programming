package com.barabanov.leetcode.concurrency;

/**
 * 1115
 */
public class PrintFooBarAlternately
{
    private final Object monitor = new Object();
    private boolean isFooPrinted = false;

    private int n;

    public void foo(Runnable printFoo) throws InterruptedException
    {

        for (int i = 0; i < n; i++)
        {
            synchronized (monitor)
            {
                while (isFooPrinted)
                    monitor.wait();

                printFoo.run();
                isFooPrinted = true;
                monitor.notifyAll();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException
    {

        for (int i = 0; i < n; i++)
        {
            synchronized (monitor)
            {
                while (!isFooPrinted)
                    monitor.wait();

                printBar.run();
                isFooPrinted = false;
                monitor.notifyAll();
            }
        }
    }
}
