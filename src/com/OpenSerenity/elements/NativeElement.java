package com.OpenSerenity.elements;

public interface NativeElement {
    String getTagName() throws Exception;
    String getText() throws Exception;
    boolean isEnabled() throws Exception;
    boolean isSelected() throws Exception;
    boolean isDisplayed() throws Exception;
    boolean isExists();
    void clear() throws Exception;
    void sendKeys(String text) throws Exception;
    void submit() throws Exception;
    void click() throws Exception;
    void altClick();
    String getAttribute(String attributeName) throws Exception;
    String getCssValue(String propertyName) throws Exception;
    NativeElement findChild(String locator);
    Iterable<NativeElement> findChildren(String locator);
}
