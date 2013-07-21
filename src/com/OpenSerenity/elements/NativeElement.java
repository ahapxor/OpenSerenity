package com.OpenSerenity.elements;

public interface NativeElement {
    String getTagName();
    String getText();
    boolean isEnabled();
    boolean isSelected();
    boolean isDisplayed();
    boolean isExists();
    void clear();
    void sendKeys(String text);
    void submit();
    void click();
    void altClick();
    String getAttribute(String attributeName);
    String getCssValue(String propertyName);
    NativeElement findChild(String locator);
    Iterable<NativeElement> findChildren(String locator);
}
