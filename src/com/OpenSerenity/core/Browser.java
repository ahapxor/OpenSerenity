package com.OpenSerenity.core;

import com.OpenSerenity.elements.BaseElement;
import com.OpenSerenity.functionalInterfaces.Func;

import java.util.Set;

public interface Browser {
    <TElement extends BaseElement> TElement findElement(Class<TElement> clz, String locator, Func<Browser> selectFrame) throws IllegalAccessException, InstantiationException;

    public void start();
    public void stop();
    public void open(String url);
    public void close();
    public void resizeTo(int width, int height);
    public void maximize();
    public void back();
    public void refresh();
    public void selectFrame(String locator);

    public void selectWindow(String handle);
    public void closeWindow(String handle);
    public String getCurrentWindowHandle();
    public Set<String> getAllWindowsHandles();

    public String getTitle();
    public String getLocation();
    public String getMessageBoxText();
    public void acceptMessageBox();
    public void dismissMessageBox();

    public void captureEntirePageScreenShot(String filename);
    public void createCookie(String name, String value);
    public String getCookie(String name);
    public void deleteCookie(String name, String path);
    public void acceptAnyAlert();
}
