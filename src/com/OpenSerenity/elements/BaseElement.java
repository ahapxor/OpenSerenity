package com.OpenSerenity.elements;

import com.OpenSerenity.dsl.Assertion;
import com.OpenSerenity.dsl.predicates.Be;
import com.OpenSerenity.exceptions.AssertionException;
import com.OpenSerenity.functionalInterfaces.WaitCondition;
import com.OpenSerenity.utils.Waiter;

import java.util.ArrayList;
import java.util.List;

public class BaseElement<TElement extends BaseElement> {

    @Override
    public String toString() {
        try {
            return String.format("%s[locator=%s]", nativeElement.getTagName(), locator);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    public void init() throws Exception {}

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

        if (!result) {
            throw new AssertionException();
        }
        return (TElement) this;
    }

    public TElement waitFor(final Assertion condition) throws Exception {
        return should(condition);
    }

    public <T extends BaseElement> T getChild(Class<T> clz, String locator)
            throws Exception {
        if (locator == null) {
            throw new IllegalArgumentException("locator must be not null");
        }
        T element = clz.newInstance();
        element.setNativeElement(nativeElement.findChild(locator));
        element.setLocator(locator);
        element.init();
        return element;
    }

    public <T extends BaseElement> List<T> getChildren(Class<T> clz, String locator)
            throws Exception {
        if (locator == null) {
            throw new IllegalArgumentException("locator must be not null");
        }
        List<NativeElement> nativeElements = nativeElement.findChildren(locator);
        List<T> result = new ArrayList<>();
        for (NativeElement natElement : nativeElements) {
            T element = clz.newInstance();
            element.setNativeElement(natElement);
            element.setLocator(locator);
            element.init();
            result.add(element);
        }

        return result;
    }

    public boolean isVisible() throws Exception {
        return nativeElement.isDisplayed();
    }

    public boolean isInVisible() throws Exception {
        return !nativeElement.isExists() || !nativeElement.isDisplayed();
    }

    public TElement click() throws Exception {
        nativeElement.click();
        return (TElement) this;
    }

    public String getText() throws Exception {
        waitFor(Be.visible);
        return nativeElement.getText();
    }
}
