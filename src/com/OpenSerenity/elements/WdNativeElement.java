package com.OpenSerenity.elements;

import com.OpenSerenity.functionalInterfaces.Func;
import org.openqa.selenium.WebElement;

public class WdNativeElement implements NativeElement {
    private Func<WebElement> wdElementSelector;

    public WdNativeElement(Func<WebElement> wdElementSelector) {
        this.wdElementSelector = wdElementSelector;
    }

    @Override
    public String getTagName() {
        throw new UnsupportedOperationException(); //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getText() {
        throw new UnsupportedOperationException(); //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isEnabled() {
        throw new UnsupportedOperationException(); //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isSelected() {
        throw new UnsupportedOperationException(); //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isDisplayed() {
        throw new UnsupportedOperationException(); //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isExists() {
        throw new UnsupportedOperationException(); //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException(); //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void sendKeys(String text) {
        throw new UnsupportedOperationException(); //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void submit() {
        throw new UnsupportedOperationException(); //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void click() {
        throw new UnsupportedOperationException(); //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void altClick() {
        throw new UnsupportedOperationException(); //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getAttribute(String attributeName) {
        throw new UnsupportedOperationException(); //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getCssValue(String propertyName) {
        throw new UnsupportedOperationException(); //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public NativeElement findChild(String locator) {
        throw new UnsupportedOperationException(); //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Iterable<NativeElement> findChildren(String locator) {
        throw new UnsupportedOperationException(); //To change body of implemented methods use File | Settings | File Templates.
    }
}
