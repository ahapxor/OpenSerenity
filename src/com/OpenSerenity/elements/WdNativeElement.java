package com.OpenSerenity.elements;

import com.OpenSerenity.functionalInterfaces.Func;
import com.OpenSerenity.functionalInterfaces.WaitCondition;
import com.OpenSerenity.utils.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

public class WdNativeElement implements NativeElement {
    private Func<WebElement> wdElementSelector;

    private WebElement wdElement;
    WebElement getWdElement() throws Exception {
        if(isStale()) {
            wdElement = wdElementSelector.invoke();
        }
        return wdElement;
    }

    public WdNativeElement(Func<WebElement> wdElementSelector) {
        this.wdElementSelector = wdElementSelector;
    }

    @Override
    public String getTagName() throws Exception {
        return getWdElement().getTagName();
    }

    @Override
    public String getText() throws Exception {
        return getWdElement().getText();
    }

    @Override
    public boolean isEnabled() throws Exception {
        return getWdElement().isEnabled();
    }

    @Override
    public boolean isSelected() throws Exception {
        return getWdElement().isSelected();
    }

    @Override
    public boolean isDisplayed() throws Exception {
        return getWdElement().isDisplayed();
    }

    @Override
    public boolean isExists() {
        try
        {
            return getWdElement() != null;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public void clear() throws Exception {
        getWdElement().clear();
    }

    @Override
    public void sendKeys(String text) throws Exception {
        getWdElement().sendKeys(text);
    }

    @Override
    public void submit() throws Exception {
        getWdElement().submit();
    }

    @Override
    public void click() throws Exception {
        getWdElement().click();
    }

    @Override
    public void altClick() {
        throw new UnsupportedOperationException(); //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getAttribute(String attributeName) throws Exception {
        return getWdElement().getAttribute(attributeName);
    }

    @Override
    public String getCssValue(String propertyName) throws Exception {
        return getWdElement().getCssValue(propertyName);
    }

    @Override
    public NativeElement findChild(final String locator) {
        return new WdNativeElement(new Func<WebElement>() {
            @Override
            public WebElement invoke() throws Exception {
                Waiter.Default().waitFor(new WaitCondition() {
                    @Override
                    public boolean invoke() throws Exception {
                        return getWdElement().findElement(By.cssSelector(locator)) != null;
                    }
                });
                return getWdElement().findElement(By.cssSelector(locator));
            }
        });
    }

    @Override
    public Iterable<NativeElement> findChildren(String locator) {
        throw new UnsupportedOperationException(); //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isStale() {
        try {
            if(wdElement == null) {
                return true;
            }
            wdElement.findElement(By.xpath("."));
            boolean res = wdElement.isDisplayed();
            return false;
        } catch (StaleElementReferenceException ex) {
            ex.printStackTrace();
            return true;
        }
    }
}
