package com.OpenSerenity.dsl;

import com.OpenSerenity.elements.BaseElement;

public interface Assertion {
    boolean invoke(BaseElement element);
}
