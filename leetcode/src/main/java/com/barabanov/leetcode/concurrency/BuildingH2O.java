package com.barabanov.leetcode.concurrency;

import java.util.concurrent.Semaphore;

/**
 * 1117
 */
public class BuildingH2O
{

    private final Semaphore hydrogenSemaphore = new Semaphore(0, true);
    private final Semaphore oxygenSemaphore = new Semaphore(2,true);

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException
    {
        hydrogenSemaphore.acquire();

        releaseHydrogen.run();

        oxygenSemaphore.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException
    {
        oxygenSemaphore.acquire(2);

        releaseOxygen.run();

        hydrogenSemaphore.release(2);
    }



    /////// solution 2
    private int hydrogenCount = 0;
    private final Object monitor = new Object();

    public void hydrogen2(Runnable releaseHydrogen) throws InterruptedException
    {
        synchronized (monitor)
        {
            while (hydrogenCount == 2)
                monitor.wait();

            releaseHydrogen.run();
            hydrogenCount++;

            if (hydrogenCount == 2) { // If two hydrogens are released, reset for oxygen
                monitor.notifyAll();
            }
        }
    }

    public void oxygen2(Runnable releaseOxygen) throws InterruptedException
    {
        synchronized (monitor)
        {
            while (hydrogenCount != 2)
                monitor.wait();

            releaseOxygen.run();
            hydrogenCount = 0;
            monitor.notifyAll();
        }

    }
}
