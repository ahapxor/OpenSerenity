package com.OpenSerenity.core;

public interface LocalStorage  {
    public int size();
    public <T> T get(String key);
    public void put(String key, Object value);
    public void remove(String key);
}
