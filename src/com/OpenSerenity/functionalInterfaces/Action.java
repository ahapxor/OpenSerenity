package com.OpenSerenity.functionalInterfaces;

public interface Action<TValue> {
    public void invoke(TValue value);
}
