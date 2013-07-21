package com.OpenSerenity.utils;

import com.OpenSerenity.functionalInterfaces.WaitCondition;

import java.util.Date;

public class Waiter {
    public static Waiter Default() {
        return new Waiter();
    }

    private Waiter() {
    }

    long timeout = 0L;
    long retryTimeout = 1000L;

    public void waitFor(WaitCondition waitCondition) {
        try {
            long start = new Date().getTime();
            do {
                try {
                    if (waitCondition.invoke()) {
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    sleepFor(retryTimeout);
                }
            } while (!timeoutReached(start));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean timeoutReached(long start) {
        return (new Date().getTime() - start + retryTimeout) > timeout;
    }

    private void sleepFor(long millis) throws InterruptedException {
        Thread.sleep(millis);
    }
}
