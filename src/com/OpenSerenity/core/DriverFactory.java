package com.OpenSerenity.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverFactory {
    public static WebDriver createBrowser() throws Exception {
        String browserType = TestContext.configuration.getBrowser();
        if("FF".equals(browserType)) {
            return new FirefoxDriver();
        }
        if("chrome".equals(browserType)) {
            return new ChromeDriver();
        }
        if("IE".equals(browserType)) {
            return new InternetExplorerDriver();
        }

        throw new Exception("Browser not set in config");
    }
}
