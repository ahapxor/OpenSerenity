package com.OpenSerenity.elements;

import com.OpenSerenity.core.TestContext;
import com.OpenSerenity.functionalInterfaces.Func;
import com.OpenSerenity.functionalInterfaces.WaitCondition;
import com.OpenSerenity.utils.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

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
        try {
            return wdElement.getTagName();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
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
        try {
            return wdElement.getAttribute(attributeName);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    @Override
    public String getCssValue(String propertyName) throws Exception {
        try {
            return wdElement.getCssValue(propertyName);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    @Override
    public NativeElement findChild(final String locator) {
        return new WdNativeElement(new Func<WebElement>() {
            @Override
            public WebElement invoke() throws Exception {
                Waiter.WithTimeout(TestContext.configuration.getElementFindTimeout()).waitFor(new WaitCondition() {
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
    public List<NativeElement> findChildren(final String locator) {
        List<NativeElement> result = new ArrayList<>();
        try {
            List<WebElement> wdElementsList = getWdElement().findElements(By.cssSelector(locator));

            for(final WebElement webElement : wdElementsList) {
                result.add(new WdNativeElement(new Func<WebElement>() {
                    @Override
                    public WebElement invoke() throws Exception {
                        return webElement;
                    }
                }));
            }

            return result;
        } catch (Exception e) {
            return new ArrayList<>();
        }
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
