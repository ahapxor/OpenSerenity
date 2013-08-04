package com.OpenSerenity.webDriver;

import com.OpenSerenity.core.LocalStorage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class WebDriverLocalStorage implements LocalStorage {
    private JavascriptExecutor javascriptExecutor;

    public WebDriverLocalStorage(WebDriver webDriver) {
        javascriptExecutor = (JavascriptExecutor) webDriver;
    }

    @Override
    public int size() {
        return (Integer) javascriptExecutor.executeScript("return window.localStorage.length;");
    }

    @Override
    public <T> T get(String key) {
        return (T) javascriptExecutor.executeScript(String.format("return window.localStorage.getItem('%s');", key));

    }

    @Override
    public void put(String key, Object value) {
        javascriptExecutor.executeScript(String.format("window.localStorage.setItem('%s','%s');", key, value));

    }

    @Override
    public void remove(String key) {
        javascriptExecutor.executeScript(String.format("window.localStorage.removeItem('%s');", key));

    }
}
