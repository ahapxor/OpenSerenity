package com.OpenSerenity.configuration;

@Deprecated
public class ConstConfiguration implements Configuration {
    @Override
    public String getBaseDomain() {
        return "https://localhost/";
    }

    @Override
    public long getTimeout() {
        return 30000L;
    }

    @Override
    public long getRetryTimeout() {
        return 250L;
    }

    @Override
    public String getBrowser() {
        return "FF";
    }
}
