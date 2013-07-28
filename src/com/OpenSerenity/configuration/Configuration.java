package com.OpenSerenity.configuration;

public interface Configuration {
    String getBaseDomain();
    long getTimeout();
    long getRetryTimeout();
    String getBrowser();
}
