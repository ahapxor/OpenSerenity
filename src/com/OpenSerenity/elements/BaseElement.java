package com.OpenSerenity.elements;

import com.OpenSerenity.dsl.Assertion;

public class BaseElement<TElement extends BaseElement> {

    public TElement should(Assertion condition) {
        throw new UnsupportedOperationException();
    }
}
