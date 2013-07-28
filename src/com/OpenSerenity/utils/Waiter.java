package com.OpenSerenity.utils;

import com.OpenSerenity.core.TestContext;
import com.OpenSerenity.functionalInterfaces.WaitCondition;

import java.util.Date;

public class Waiter {
    public static Waiter Default() {
        return new Waiter();
    }

    private Waiter() {
    }

    long timeout = TestContext.configuration.getTimeout();
    long retryTimeout = TestContext.configuration.getRetryTimeout();

    public void waitFor(WaitCondition waitCondition) throws Exception {
        try {
            long start = new Date().getTime();
            do {
                try {
                    if (waitCondition.invoke()) {
                        return;
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
        throw new Exception("timeout!");
    }

    private boolean timeoutReached(long start) {
        return (new Date().getTime() - start + retryTimeout) > timeout;
    }

    private void sleepFor(long millis) throws InterruptedException {
        Thread.sleep(millis);
    }
}
