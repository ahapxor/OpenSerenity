package com.OpenSerenity.configuration;

public interface Configuration {
    String getBaseDomain();
    long getTimeout();
    long getElementFindTimeout();
    long getRetryTimeout();
    String getBrowser();
}
