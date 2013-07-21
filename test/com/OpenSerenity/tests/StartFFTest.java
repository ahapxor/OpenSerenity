package com.OpenSerenity.tests;

import com.OpenSerenity.core.Browser;
import com.OpenSerenity.webDriver.WebDriverBrowser;
import org.junit.Test;

public class StartFFTest {
    @Test
    public void testStart() throws Exception {
        Browser browser = new WebDriverBrowser();
        browser.start();
        Thread.sleep(10000);
        browser.stop();

    }
}
