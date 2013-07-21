package com.OpenSerenity.elements;

import com.OpenSerenity.core.Browser;
import com.OpenSerenity.core.TestContext;
import com.OpenSerenity.functionalInterfaces.Action;
import com.OpenSerenity.functionalInterfaces.Func;
import com.OpenSerenity.functionalInterfaces.WaitCondition;
import com.OpenSerenity.utils.Waiter;

public class BasePage {
    protected BaseElement content;
    protected String windowHandle;

    public static <TPage extends BasePage> TPage open(Class<TPage> clz, Action action)
            throws Exception {
        TPage page = clz.newInstance();
        page.open(action);
        return page;
    }

    public static <TPage extends BasePage> TPage openWindow(Class<TPage> clz, Action action)
            throws IllegalAccessException, InstantiationException {
        TPage page = clz.newInstance();
        page.openWindow(action);
        return page;
    }

    protected void open(Action action) throws Exception {
        action.invoke(null);
        windowHandle = TestContext.browser.getCurrentWindowHandle();
        init();
        waitForLoad();
    }

    protected void openWindow(Action action)
    {
        throw new UnsupportedOperationException();
    }

    protected void init() throws Exception {
        content = TestContext.browser.findElement(BaseElement.class, "body", new Func<Browser>() {
            @Override
            public Browser invoke() {
                TestContext.browser.selectWindow(windowHandle);
                TestContext.browser.selectFrame(null);
                return null;
            }
        });
    }

    public boolean isStale() throws Exception {
        return isClosed();
    }

    public String getTitle() {
            selectWindow();
            return TestContext.browser.getTitle();
    }

    public void waitForLoad() throws Exception {
        Waiter.Default().waitFor(new WaitCondition() {
            @Override
            public boolean invoke() {
                try {
                    return !isStale();
                } catch (Exception ex) {
                    return false;
                }

            }
        });
    }

    public void selectWindow()
    {
        try
        {
            if (windowHandle == TestContext.browser.getCurrentWindowHandle())
            {
                return;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        TestContext.browser.selectWindow(windowHandle);
    }

    public void close()
    {
        if (isClosed())
        {
            return;
        }
        selectWindow();
        TestContext.browser.closeWindow(windowHandle);
    }

    public boolean isClosed() {
        return !TestContext.browser.getAllWindowsHandles().contains(windowHandle);
    }

    public BasePage acceptAlert()
    {
        TestContext.browser.acceptMessageBox();
        return this;
    }

    public BasePage acceptConfirmation()
    {
        TestContext.browser.acceptMessageBox();
        return this;
    }

    public BasePage dismissConfirmation()
    {
        TestContext.browser.dismissMessageBox();
        return this;
    }
}
