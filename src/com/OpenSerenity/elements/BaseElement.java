package com.OpenSerenity.elements;

import com.OpenSerenity.dsl.Assertion;
import org.openqa.selenium.WebElement;

public class BaseElement<TElement extends BaseElement> {

    NativeElement nativeElement;
    public void setNativeElement(NativeElement nativeElement) {
        this.nativeElement = nativeElement;
    }

    String locator;
    public void setLocator(String locator) {
        this.locator = locator;
    }

    public TElement should(Assertion condition) {
        throw new UnsupportedOperationException();
    }
}
