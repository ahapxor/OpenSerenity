package com.OpenSerenity.webDriver;

import com.OpenSerenity.core.Browser;
import com.OpenSerenity.core.DriverFactory;
import com.OpenSerenity.core.LocalStorage;
import com.OpenSerenity.core.TestContext;
import com.OpenSerenity.elements.BaseElement;
import com.OpenSerenity.elements.NativeElement;
import com.OpenSerenity.elements.WdNativeElement;
import com.OpenSerenity.functionalInterfaces.Func;
import com.OpenSerenity.functionalInterfaces.WaitCondition;
import com.OpenSerenity.utils.Waiter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

public class WebDriverBrowser implements Browser {
    WebDriver driver;
    LocalStorage localStorage;

    public WebDriverBrowser() {
    }

    @Override
    public <TElement extends BaseElement> TElement findElement(Class<TElement> clz,
                                                               final String locator,
                                                               final Func<Browser> selectFrame)
            throws Exception {
        Waiter.Default().waitFor(new WaitCondition() {
            @Override
            public boolean invoke() {
                return driver.findElements(By.cssSelector(locator)).size() > 0;
            }
        });

        NativeElement nativeElement = new WdNativeElement(new Func<WebElement>() {
            @Override
            public WebElement invoke() throws Exception {
                selectFrame.invoke();
                return findWdNativeElement(locator);
            }
        });

        TElement element = clz.newInstance();
        element.setNativeElement(nativeElement);
        element.setLocator(locator);
        element.init();
        return element;
    }

    private WebElement findWdNativeElement(final String locator) throws Exception {
        Waiter.Default().waitFor(new WaitCondition() {
            @Override
            public boolean invoke() {
                return driver.findElements(By.cssSelector(locator)).size() > 0;
            }
        });
        return driver.findElement(By.cssSelector(locator));
    }

    @Override
    public void start() throws Exception {
        driver = DriverFactory.createBrowser();
        localStorage = new WebDriverLocalStorage(driver);
        maximize();
    }

    @Override
    public void stop() {
        driver.quit();
    }

    @Override
    public void open(String url) {
        driver.navigate().to(TestContext.configuration.getBaseDomain() + url);
    }

    @Override
    public void close() {
        driver.close();
    }

    @Override
    public void resizeTo(int width, int height) {
        driver.manage().window().setSize(new Dimension(width, height));
    }

    @Override
    public void maximize() {
        driver.manage().window().maximize();
    }

    @Override
    public void back() {
        driver.navigate().back();
    }

    @Override
    public void refresh() {
        driver.navigate().refresh();
    }

    @Override
    public void selectFrame(String locator) {
        if(StringUtils.isNotBlank(locator)) {
            driver.switchTo().frame(locator);
        } else {
            driver.switchTo().defaultContent();
        }
    }

    @Override
    public void selectWindow(String handle) {
        driver.switchTo().window(handle);
    }

    @Override
    public void closeWindow(String handle) {
        driver.switchTo().window(handle).close();
    }

    @Override
    public String getCurrentWindowHandle() {
        return driver.getWindowHandle();
    }

    @Override
    public Set<String> getAllWindowsHandles() {
        return driver.getWindowHandles();
    }

    @Override
    public String getTitle() {
        return driver.getTitle();
    }

    @Override
    public String getLocation() {
        return driver.getCurrentUrl();
    }

    @Override
    public String getMessageBoxText() {
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    @Override
    public void acceptMessageBox() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    @Override
    public void dismissMessageBox() {
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    @Override
    public void captureEntirePageScreenShot(String filename) {
        if (driver instanceof TakesScreenshot) {
            File tempFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(tempFile, new File("screenshots/" + new Date() + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void createCookie(String name, String value) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, 1); // add 1 month

        Cookie cookie = new Cookie(name, value, null, cal.getTime());
        driver.manage().addCookie(cookie);
    }

    @Override
    public String getCookie(String name) {
        Cookie cookie = driver.manage().getCookieNamed(name);
        if(cookie == null) {
            return null;
        }
        return cookie.getValue();
    }

    @Override
    public void deleteCookie(String name, String path) {
        driver.manage().deleteCookieNamed(name);
    }

    @Override
    public void acceptAnyAlert() {
        //throw new UnsupportedOperationException(); //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public LocalStorage getLocalStorage() {
        return localStorage;
    }
}
