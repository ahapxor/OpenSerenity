package com.OpenSerenity.elements;

import com.OpenSerenity.dsl.Assertion;
import com.OpenSerenity.functionalInterfaces.WaitCondition;
import com.OpenSerenity.utils.Waiter;

public class BaseElement<TElement extends BaseElement> {

    NativeElement nativeElement;
    public void setNativeElement(NativeElement nativeElement) {
        this.nativeElement = nativeElement;
    }

    String locator;
    public void setLocator(String locator) {
        this.locator = locator;
    }

    public TElement should(final Assertion condition) throws Exception {
        boolean result = true;
        try {
            Waiter.Default().waitFor(new WaitCondition() {
                @Override
                public boolean invoke() throws Exception {
                    return condition.invoke(BaseElement.this);
                }
            });
        } catch (Exception ex) {
            result = false;
        }

        if (!result)
        {
            throw new Exception("Condition failed");
        }
        return (TElement) this;
    }

    public <T extends BaseElement> T getChild(Class<T> clz, String locator)
            throws IllegalAccessException, InstantiationException {
        if (locator == null)
        {
            throw new IllegalArgumentException("locator must be not null");
        }
        T result = clz.newInstance();
        result.setNativeElement(nativeElement.findChild(locator));
        result.setLocator(locator);
        return result;
    }

    public boolean isVisible() throws Exception {
        return nativeElement.isExists() && nativeElement.isDisplayed();
    }
}
