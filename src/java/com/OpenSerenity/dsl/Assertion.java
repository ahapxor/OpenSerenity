package com.OpenSerenity.dsl;

public interface Assertion<T> {
    boolean invoke(T element) throws Exception;
}
